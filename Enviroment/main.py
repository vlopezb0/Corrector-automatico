from pathlib import Path
import matplotlib.pyplot as plt
from sklearn.metrics import auc
import numpy as np
import collections
import modelAccuracy 
import modelCoste
import modelWellFormed 
import modelValidity 
import modelTestability 
import modelQuality 
import boxplot

if __name__ == '__main__':
    # Path relativo
    current_path = str(Path.cwd()).replace("\\","//")

    # Apertura de ficheros
    graficas = current_path + "//Graficas//"

    # Medidas
    tipos = ["Accuracy","Cost","Well_Formed","Validity","Testability","Quality"]

    # Crear una figura para cada medida
    fig1, axs1 = plt.subplots()
    fig2, axs2 = plt.subplots()
    fig3, axs3 = plt.subplots()
    fig4, axs4 = plt.subplots()
    fig5, axs5 = plt.subplots()
    fig6, axs6 = plt.subplots()

    figs = [fig1,fig2,fig3,fig4,fig5,fig6]
    axs = [axs1,axs2,axs3,axs4,axs5,axs6]

    # Crear la linea "Al azar" de cada grafica
    for ax in axs:
        ax.plot(
                        (0,1),
                        (0,1),
                        label = "Al azar (AUC = 0.5)",
                        color = "black",
                        linestyle = "--"
                    )

    # Opción de DEBUG: 0 -> Silencioso, 1 -> Verbose
    DEBUG = 0

    # Borramos anteriores aucs
    boxplot.borra() 

    # Numero de simulaciones
    rango = 500

    # Creamos los diccionarios para poder guardar datos
    tprs = collections.defaultdict(lambda : [])
    aucs = collections.defaultdict(lambda : [])
    meaningless = collections.defaultdict(lambda : 0)
    inversiones = collections.defaultdict(lambda : 0)
    dps = collections.defaultdict(lambda : 0)

    # Bucle principal
    for i in range(rango):
        # Pretty output por pantalla para saber iteración actual
        if DEBUG == 0:
            porcentaje = (int) ((i+1)/rango * 100)
            rayitas = (int) (porcentaje/2)
            numIter = "Iteración número: " + str(i+1).rjust(3) + " de " + str(rango) + " ["+"#"*rayitas+"-"*(50-rayitas)+"] " + str(porcentaje) + "%"
            print(numIter, end='')
            print('\b' * len(numIter), end='', flush=True)

        # Calculamos medida de Accuracy y guardamos datos
        tpr, auc_roc, m, inv, dp  = modelAccuracy.Accuracy(axs[0],DEBUG)

        tprs["Accuracy"].extend(tpr)
        aucs["Accuracy"].extend(auc_roc)
        meaningless["Accuracy"]+=m
        inversiones["Accuracy"]+=inv    
        dps["Accuracy"] = dp

        # Calculamos medida de Cost y guardamos datos
        tpr, auc_roc, m, inv, dp  = modelCoste.Coste(axs[1],DEBUG)
        
        tprs["Cost"].extend(tpr)
        aucs["Cost"].extend(auc_roc)
        meaningless["Cost"]+=m
        inversiones["Cost"]+=inv
        dps["Cost"] = dp

        # Calculamos medida de Well_Formed y guardamos datos
        tpr, auc_roc, m, inv, dp = modelWellFormed.WellFormed(axs[2],DEBUG)

        tprs["Well_Formed"].extend(tpr)
        aucs["Well_Formed"].extend(auc_roc)
        meaningless["Well_Formed"]+=m
        inversiones["Well_Formed"]+=inv
        dps["Well_Formed"] = dp

        # Calculamos medida de Validity y guardamos datos
        tpr, auc_roc ,m, inv, dp  = modelValidity.Validity(axs[3],dp,DEBUG)

        tprs["Validity"].extend(tpr)
        aucs["Validity"].extend(auc_roc)
        meaningless["Validity"]+=m
        inversiones["Validity"]+=inv
        dps["Validity"] = dp

        # Calculamos medida de Testability y guardamos datos
        tpr, auc_roc, m, inv, dp  = modelTestability.Testability(axs[4],DEBUG) 

        tprs["Testability"].extend(tpr)
        aucs["Testability"].extend(auc_roc)
        meaningless["Testability"]+=m
        inversiones["Testability"]+=inv
        dps["Testability"] = dp

        # Datos a pasar al modelo de Quality
        dp_q = [dps["Accuracy"],dps["Cost"],dps["Well_Formed"],dps["Validity"],dps["Testability"]]

        # Calculamos medida de Quality y guardamos datos
        tpr, auc_roc, m, inv, dp  = modelQuality.Quality(axs[5],[2,3,4,5,6,7],dp_q,DEBUG) 

        tprs["Quality"].extend(tpr)
        aucs["Quality"].extend(auc_roc)
        meaningless["Quality"]+=m
        inversiones["Quality"]+=inv
        dps["Quality"] = dp

    # Para cada medida
    for num,tipo in enumerate(tipos):

        # Calcular curva ROC media
        tpr_medio = np.mean(tprs[tipo], axis=0)
        tpr_medio[-1] = 1.0
        auc_medio = auc(np.linspace(0, 1, 100),tpr_medio)

        axs[num].plot(
                np.linspace(0, 1, 100),
                tpr_medio,
                lw=3,
                label="Curva ROC media (AUC:  " + str(auc_medio)[0:5] + ")"
        )

        # En caso de ser el Coste indicar como fue obtenido
        tipoa = tipo
        if tipoa == "Cost": tipoa = "Cost (Tokens)"

        # Poner titulo y axis a la gráfica
        axs[num].set(
                    xlabel="False Positive Rate",
                    ylabel="True Positive Rate",
                    title="ROC " + tipoa
                )

        # Poner desviación típica a la tabla
        std = np.std(tprs[tipo], axis=0)
        tprs_upper = np.minimum(tpr_medio + std, 1)
        tprs_lower = np.maximum(tpr_medio - std, 0)
        axs[num].fill_between(
                np.linspace(0, 1, 100),
                tprs_lower,
                tprs_upper,
                color="grey",
                alpha=0.2,
                label="Desviación estandar"
                )

        # Poner leyenda
        axs[num].legend(loc='lower right')

        # Indicar estadísticas de ejecución
        print("La medida ",tipo," tiene ",meaningless[tipo], " predicciones vacias.")
        print("Además, se hicieron ", inversiones[tipo], " inversiones.")

        # Guardar la imagen
        figs[num].savefig(graficas + "Medida " + str(tipo[0:3]) + ".png")

    # Generar todos los diagramas de cajas
    boxplot.boxplot()
