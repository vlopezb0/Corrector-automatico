import collections
import time

from sklearn.metrics import auc
import Accuracy.modelAccuracy 
import Coste.modelCoste 
import Well_Formed.modelWellFormed 
import Validity.modelValidity 
import Testability.modelTestability 
import Quality.modelQuality 
import boxplot
import matplotlib.pyplot as plt
import warnings
import numpy as np

graficas = "C://Users//Víctor//Desktop//TFM//Enviroment//Graficas//"

tipos = ["Accuracy","Coste","Well_Formed","Validity","Testability","Quality"]

tipos = ["Coste"]

fig1, axs1 = plt.subplots()
fig2, axs2 = plt.subplots()
fig3, axs3 = plt.subplots()
fig4, axs4 = plt.subplots()
fig5, axs5 = plt.subplots()
fig6, axs6 = plt.subplots()

figs = [fig1,fig2,fig3,fig4,fig5,fig6]
axs = [axs1,axs2,axs3,axs4,axs5,axs6]


for ax in axs:
    ax.plot(
                    (0,1),
                    (0,1),
                    label = "Al azar (AUC = 0.5)",
                    color = "black",
                    linestyle = "--"
                )

DEBUG = 0

boxplot.borra() 

rango = 500

tprs = collections.defaultdict(lambda : [])
aucs = collections.defaultdict(lambda : [])
meaningless = collections.defaultdict(lambda : 0)
triestotales = collections.defaultdict(lambda : 0)
inversiones = collections.defaultdict(lambda : 0)

for i in range(rango):
    if DEBUG == 0:
        porcentaje = (int) (i/rango * 100)
        rayitas = (int) (porcentaje/2)
        numIter = "Iteración número: " + str(i).rjust(3) + " de " + str(rango) + " ["+"#"*rayitas+"-"*(50-rayitas)+"] " + str(porcentaje) + "%"
        print(numIter, end='')
        print('\b' * len(numIter), end='', flush=True)

    tpr, auc_roc, m, tries, inv  = Accuracy.modelAccuracy.Accuracy(axs[0],DEBUG)

    tprs["Accuracy"].append(tpr)
    aucs["Accuracy"].append(auc_roc)
    meaningless["Accuracy"]+=m
    triestotales["Accuracy"]+=tries
    inversiones["Accuracy"]+=inv    


    warnings.filterwarnings("ignore")
    tpr, auc_roc, m, tries, inv  = Coste.modelCoste2.Coste(axs[1],DEBUG)
    warnings.filterwarnings("default")

    tprs["Coste"].append(tpr)
    aucs["Coste"].append(auc_roc)
    meaningless["Coste"]+=m
    triestotales["Coste"]+=tries
    inversiones["Coste"]+=inv

    tpr, auc_roc, m, tries = Well_Formed.modelWellFormed.WellFormed(axs[2],DEBUG)

    tprs["Well_Formed"].append(tpr)
    aucs["Well_Formed"].append(auc_roc)
    meaningless["Well_Formed"]+=m
    triestotales["Well_Formed"]+=tries


    tpr, auc_roc ,m, tries  = Validity.modelValidity.Validity(axs[3],DEBUG)

    tprs["Validity"].append(tpr)
    aucs["Validity"].append(auc_roc)
    meaningless["Validity"]+=m
    triestotales["Validity"]+=tries


    tpr, auc_roc, m, tries, inv  = Testability.modelTestability.Testability(axs[4],DEBUG) 

    tprs["Testability"].append(tpr)
    aucs["Testability"].append(auc_roc)
    meaningless["Testability"]+=m
    triestotales["Testability"]+=tries
    inversiones["Testability"]+=inv



    tpr, auc_roc, m, tries  = Quality.modelQuality.Quality(axs[5],[2,3,4,5,6,7],DEBUG) 

    tprs["Quality"].append(tpr)
    aucs["Quality"].append(auc_roc)
    meaningless["Quality"]+=m
    triestotales["Quality"]+=tries

i = 0
j = 0


for num,tipo in enumerate(tipos):

    tpr_medio = np.mean(tprs[tipo], axis=0)
    tpr_medio[-1] = 1.0
    auc_medio = auc(np.linspace(0, 1, 100),tpr_medio)

    # print(tipo,tprs[tipo])
    # print(tipo,auc_medio)

    axs[num].plot(
            np.linspace(0, 1, 100),
            tpr_medio,
            lw=3,
            label="Curva ROC media (AUC:  " + str(auc_medio)[0:5] + ")" 
    )

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


    # axs[num].legend().get_han
    axs[num].legend()


    # i += 1
    # if i == 3:
    #     j += 1
    #     i = 0

    print("La medida ",tipo," tiene ",meaningless[tipo], " predicciones vacias y fueron necesarios", triestotales[tipo], " reintentos.")
    if tipo == "Coste" or tipo == "Testability" or tipo == "Accuracy" : print("Además, se hicieron ", inversiones[tipo], " inversiones.")

    figs[num].savefig(graficas + "Medida " + str(tipo[0:3]) + ".png")

# axs.legend_ = None
# for ax in axs.flatten():
#     ax.legend().remove()

# plt.savefig(graficas+(time.strftime("%x,%X")).replace(r'/',"-").replace(r':',"-")+".png")

boxplot.boxplot()

# print("Ejecución completada")