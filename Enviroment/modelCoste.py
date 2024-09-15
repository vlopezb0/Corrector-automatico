from pathlib import Path
from matplotlib import pyplot as plt
import pandas as pd
import numpy as np
from sklearn.metrics import RocCurveDisplay, roc_auc_score
from sklearn.model_selection import KFold, StratifiedKFold
from sklearn.linear_model import LinearRegression, LogisticRegressionCV
import warnings
import boxplot

def Coste(axs,debug=0):
    '''Modelo para predecir la medida de Cost a traves de los tokens

    Parametros:
        -axs : Subclase de la figura donde dibujar las curvas ROC.
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
    aucs = current_path + "//Coste//auc.txt"
    fileauc = open(aucs,"a")

    path = current_path + "//Coste//DataCosteTokens1.csv"
    file = pd.read_csv(path,sep=";")

    path2 = current_path + "//Coste//DataCosteTokens2.csv"
    file2 = pd.read_csv(path2,sep=";")

    # Creamos DataFrames con los datos, escogiendo X e Y en cada caso
    data = pd.DataFrame(file)
    X = data[['MomentoInt','Tokens']]
    Y = data['TokensFinal']

    data2 = pd.DataFrame(file2)
    X2 = data2[['MomentoInt','Tokens']]
    Y2 = data2['Cost']

    # Creamos columnas en el segundo DataFrame para los resultados
    data2["Predict_token"] = -1
    data2["Predict_Cost"] = -1

    # Iniciamos RNG
    random_state = np.random.RandomState()

    # Entrenamos primer modelo
    clf = LinearRegression().fit(X, Y)

    # Creamos la separación estratificada
    skf = StratifiedKFold(n_splits=5,shuffle=True,random_state=random_state)
    
    # Valores que se retornaran
    tprs = []
    displays = []
    m = 0
    inv = 0

    # Por cada fold
    for train,test in skf.split(X2,Y2):
        # Separamos los datos en train y test de X e Y
        X_train, X_test = X2.iloc[train], X2.iloc[test]
        y_train, y_test = Y2.iloc[train], Y2.iloc[test]

        # Entrenamos el modelo de Regresión Logística con un KFold(10), usando todos los cores
        clf2 = LogisticRegressionCV(random_state=random_state,cv=KFold(10),n_jobs=12).fit(pd.DataFrame(X_train.iloc[:,1]),y_train.values.ravel())
        
        # Predecimos los tokens y los guardamos en el DataFrame
        predicts_tokens=np.ceil(clf.predict(X_test)).reshape(-1,1)

        data2.loc[test,"Predict_Token"] = predicts_tokens

        X_test_predicts = pd.DataFrame({"Tokens":data2.loc[test,"Predict_Token"] })

        # Predecimos Cost y lo guardamos en el DataFrame
        pred_cost = list(clf2.predict(X_test_predicts))

        data2.loc[test,"Predict_Cost"] = pred_cost

        # En caso de querer debugear, imprimimos información relevante
        if debug == 1:
            print("Score Primer Modelo:",clf.score(X_test, y_test))
            print("Score Segundo Modelo:",clf2.score(X_test_predicts,y_test))
            print("Predicción Tokens:",list(predicts_tokens[:,0]))
            print("Predicción Coste:",clf2.predict(X_test_predicts))
            print("Coste real:",list(y_test))

        # En caso de hacer una prediccion vacia la guardamos
        if sum(pred_cost) == len(pred_cost) or sum(pred_cost) == 0:
            m += 1

        # Calculamos el auc
        auc = roc_auc_score(y_test,clf2.predict_proba(X_test_predicts)[:, 1])

        # En caso de ser un auc menor de 0,5, invertimos
        if auc < 0.5:
            y_test = 1 - y_test
            auc = roc_auc_score(y_test,clf2.predict_proba(X_test_predicts)[:, 1])
            inv += 1

        # Guardamos el auc
        fileauc.write(str(auc)+";")

        # Creamos la curva ROC
        display = RocCurveDisplay.from_estimator(
                clf2,
                X_test_predicts,
                y_test,
                ax=axs,
                color = (0.2,0.2,0.5+(1-auc)/2),
                label="_no",
                alpha = 1/510
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
    data_predict=pd.DataFrame({"Nombre" : data2["Nombre"],
                                "Predict_Cost" : data2["Predict_Cost"]})

    return tprs,displays,m,inv,data_predict
    
# Main en caso de ejecutarlo desde este fichero para centrarse en una métrica 
if __name__ == '__main__':
    boxplot.borra("Coste")
    current_path = str(Path.cwd()).replace("\\","//")
    graficas = current_path + "//Graficas//"
    _,ax = plt.subplots()
    rango = 100
    warnings.filterwarnings("ignore")
    for i in range(rango):
        porcentaje = (int) (i/rango * 100)
        rayitas = (int) (porcentaje/2)
        numIter = "Iteración número: " + str(i).rjust(3) + " de " + str(rango) + " ["+"#"*rayitas+"-"*(50-rayitas)+"] " + str(porcentaje) + "%"
        print(numIter, end='')
        print('\b' * len(numIter), end='', flush=True)
        Coste(ax)
    warnings.filterwarnings("default")
    ax.legend().remove()
    plt.savefig(graficas+"Coste.png")    
    boxplot.boxplot("Coste")
