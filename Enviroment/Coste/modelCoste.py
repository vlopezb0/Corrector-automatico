import random
from matplotlib import pyplot as plt
from pandas import read_csv
import pandas as pd
from sklearn.metrics import RocCurveDisplay, roc_auc_score
from sklearn.model_selection import KFold, LeaveOneOut, train_test_split
import numpy as np
from sklearn.linear_model import LinearRegression, LogisticRegressionCV
import time
import boxplot

def Coste(axs,debug=0):

    aucs = "C://Users//Víctor//Desktop//TFM//Enviroment//Coste//auc.txt"

    fileauc = open(aucs,"a")

    path="C://Users//Víctor//Desktop//TFM//Enviroment//Coste//ProblemasCoste4.csv"

    file = read_csv(path,sep=";")

    X = file.iloc[:,[3,5]]
    Y = file.iloc[:,-2]

    X2 = pd.DataFrame(file.iloc[:,-3])
    Y2 = pd.DataFrame(file.iloc[:,-1])

    random_state = np.random.RandomState()

    X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.2, random_state=random_state)

    

    X_train = X_train.iloc[:,0:1]
    X_cost = X_test.iloc[:,1]
    X_test = X_test.iloc[:,0:1]

    tries = 0

    while sum(X_cost) == len(X_cost) or sum(X_cost) == 0 :
        random_state = np.random.RandomState()

        X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.2, random_state=random_state)

        tries +=1


    
    clf = LinearRegression().fit(X_train, y_train)
    clf2 = LogisticRegressionCV(random_state=random_state,cv=KFold(10),n_jobs=12).fit(X2,Y2)


    predicts=np.ceil(clf.predict(X_test)).reshape(-1,1)
    if debug == 1:print("Predicción Tokens:",list(predicts[:,0]))
    if debug == 1:print("Score Primer Modelo:",clf.score(X_test, y_test))
    if debug == 1:print("Score Segundo Modelo:",clf2.score(predicts,X_cost))
    if debug == 1:print("Predicción Coste:",clf2.predict(predicts))
    if debug == 1:print("Coste real:",list(X_cost))

    pred = list(clf2.predict(predicts))

    m = 0
    if sum(pred) == len(pred) or sum(pred) == 0:
        m = 1


    # test = pd.DataFrame(X_cost)

    # if debug == 1:print(clf2.predict_proba(test))

    auc = roc_auc_score(X_cost,clf2.predict_proba(predicts)[:, 1])

    colour = (0.2,0.2,0.5+(1-auc)/2)

    inv = 0

    if auc < 0.5:
        X_cost = 1 - X_cost
        auc = roc_auc_score(X_cost,clf2.predict_proba(predicts)[:, 1])
        inv = 1
        # colour = (1-0.2,1-0.2,1-(0.5+(1-auc)/2))


    fileauc.write(str(auc)+";")

    display = RocCurveDisplay.from_estimator(
            clf2,
            predicts,
            X_cost,
            ax=axs,
            color = colour,
            label="_no",
            alpha = 0.01
        )

    tpr = np.interp(np.linspace(0,1,100),display.fpr, display.tpr)
    tpr[0] = 0.0


    _ = display.ax_.set(
            xlabel="False Positive Rate",
            ylabel="True Positive Rate",
            title="ROC Cost (Líneas)"
        )

    fileauc.close()

    return tpr,display.roc_auc,m,tries,inv
    
if __name__ == '__main__':
    boxplot.borra("Coste")
    graficas = "C://Users//Víctor//Desktop//TFM//Enviroment//Graficas//"
    _,ax = plt.subplots()
    for i in range(100):
        Coste(ax)
    ax.legend().remove()
    plt.savefig(graficas+"Coste.png")    
    boxplot.boxplot("Coste")
