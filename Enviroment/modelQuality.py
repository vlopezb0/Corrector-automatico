from pathlib import Path
from matplotlib import pyplot as plt
import pandas as pd
import numpy as np
from sklearn.metrics import RocCurveDisplay, roc_auc_score
from sklearn.metrics._plot.roc_curve import auc
from sklearn.model_selection import KFold, StratifiedKFold
from sklearn.linear_model import LogisticRegressionCV
import warnings
import boxplot


def Quality(axs,comb,dp=None,debug=0):
    '''Modelo para predecir la medida de Quality

    Parametros:
        -axs : Subclase de la figura donde dibujar las curvas ROC.
        -comb : Array con las medidas que quiere probarse el modelo
        -dp : Array de DataFrames con las predicciones del resto de medidas
        -debug : Opcional, para ver resultados.

    Devuelve:
        -tprs: Array con los TPR obtenidos.
        -displays: Array con las curvas ROC obtenidas.
        -m: Número de predicciones vacias hechas.
        -inv: Número de inversiones realizadas.
        -data_predict: DataFrame minimal con los nombres y predicciones de la medida
    '''
    # Path relativo
    current_path = str(Path.cwd()).replace("\\","//")

    # Apertura de ficheros
    aucs = current_path + "//Quality//auc.txt"
    fileauc = open(aucs,"a")

    path = current_path + "//Quality//DataQuality.csv"
    file = pd.read_csv(path,sep=";")

    # Creamos DataFrames con los datos
    data = pd.DataFrame(file) 

    

    # Escogemos X e Y (Si se ha ejecutado desde este archivo, se usaran medidas calculadas de COLLECE, provocará resultados optimistas)
    if dp is None:
        X = data[["Accuracy","Cost","WellFormed","Validity","Testability","Precision Chat"]]
    else:
        # Pasamos datos inferidos al dataset
        for i in range(5):
            data = pd.merge(data,dp[i],on="Nombre")
        X = data[["Predict_Accuracy","Predict_Cost","Predict_Well_Formed","Predict_Validity","Predict_Testability","Precision Chat"]]
    Y = data["Quality"]

    # Creamos columnas en el DataFrame para los resultados
    data["Predict_Quality"] = -1

    # Iniciamos RNG
    random_state = np.random.RandomState()

    # Creamos la separación estratificada
    skf = StratifiedKFold(n_splits=5,shuffle=True,random_state=random_state)
    
    # Valores que se retornaran
    tprs = []
    displays = []
    m = 0
    inv = 0

    # Por cada fold
    for train,test in skf.split(X,Y):
        # Separamos los datos en train y test de X e Y
        X_train, X_test = X.iloc[train], X.iloc[test]
        y_train, y_test = Y.iloc[train], Y.iloc[test]

        # Entrenamos el modelo de Regresión Logística con un KFold(10), usando todos los cores
        clf = LogisticRegressionCV(random_state=random_state,cv=KFold(10),n_jobs=12).fit(X_train, y_train)
    
        # Predecimos Quality lo guardamos en el DataFrame
        pred = list(clf.predict(X_test))

        data.loc[test,"Predict_Quality"] = pred

        # En caso de querer debugear, imprimimos información relevante
        if debug == 1:
            print("Score:",clf.score(X_test, y_test))
            print("Predicción:",list(clf.predict(X_test)))
            print("Real:",list(y_test))
            print("Diferencia:",list(2*clf.predict(X_test)-y_test))

        # En caso de hacer una prediccion vacia la guardamos
        if sum(pred) == len(pred) or sum(pred) == 0:
            m += 1

        # Calculamos el auc
        auc = roc_auc_score(y_test,clf.predict_proba(X_test)[:, 1])
    
        # En caso de ser un auc menor de 0,5, invertimos
        if auc < 0.5:
            y_test = 1 - y_test
            auc = roc_auc_score(y_test,clf.predict_proba(X_test)[:, 1])
            inv = 1

        # Guardamos el auc
        fileauc.write(str(auc)+";")

        # Creamos la curva ROC
        display = RocCurveDisplay.from_estimator(
                    clf,
                    X_test,
                    y_test,
                    ax=axs,
                    color = (0.5+(1-auc)/2,0.5+(1-auc)/2,0.5+(1-auc)/2),
                    lw=1,
                    alpha=1/510,
                    label="_no"
                )

        # Hacemos el TPR
        tpr = np.interp(np.linspace(0,1,100),display.fpr, display.tpr)
        tpr[0] = 0.0

        # Guardamos tanto el TPR como la curva ROC
        tprs.append(tpr)
        displays.append(display.roc_auc)

    # Cerramos el fichero
    fileauc.close()

    # Creamos un DataFrame para guardar los resultados
    data_predict=pd.DataFrame({"Nombre" : data["Nombre"],
                                "Predict_Quality" : data["Predict_Quality"]})

    return tprs,displays,m,inv,data_predict


# Main en caso de ejecutarlo desde este fichero para centrarse en una métrica 
if __name__ == '__main__':
    # Borra aucs
    boxplot.borra("Quality")

    # Path relativo
    current_path = str(Path.cwd()).replace("\\","//")

    # Apertura de ficheros
    graficas = current_path + "//Graficas//"    
    _,ax = plt.subplots()
    rango=500
    lista = [(2,3,4,5,6,7)]

    # Bucle principal
    for j,comb in enumerate(lista):
        _,ax = plt.subplots()
        ax.plot(
            (0,1),
            (0,1),
            label = "Al azar (AUC = 0.5)",
            color = "black",
            linestyle = "--"
        )
        tprs = []
        aucs = []
        for i in range(rango):
            porcentaje = (int) (i/rango * 100)
            rayitas = (int) (porcentaje/2)
            numIter = "Iteración número: " + str(i).rjust(3) + " de " + str(rango) + " ["+"#"*rayitas+"-"*(50-rayitas)+"] " + str(porcentaje) + "%"
            print(numIter, end='')
            print('\b' * len(numIter), end='', flush=True)
            tpr, aucroc,_,_,_ = Quality(ax,list(comb))
            tprs.extend(tpr)
            aucs.extend(aucroc)

        tpr_medio = np.mean(tprs, axis=0)
        tpr_medio[-1] = 1.0
        auc_medio = auc(np.linspace(0, 1, 100),tpr_medio)

        ax.plot(
            np.linspace(0, 1, 100),
            tpr_medio,
            lw=2,
            label="ROC medio (AUC:  " + str(auc_medio)[0:4] + ")" 
        )

        std = np.std(tprs, axis=0)
        tprs_upper = np.minimum(tpr_medio + std, 1)
        tprs_lower = np.maximum(tpr_medio - std, 0)
        ax.fill_between(
            np.linspace(0, 1, 100),
            tprs_lower,
            tprs_upper,
            color="grey",
            alpha=0.1,
            label="Desviación estandar"
            )
        
        ax.set(
                xlabel="False Positive Rate",
                ylabel="True Positive Rate",
                title="ROC Quality"
            )

        ax.legend()

        plt.savefig(graficas+"Quality"+str(comb)+".png")    
        boxplot.boxplot("Quality",str(comb))