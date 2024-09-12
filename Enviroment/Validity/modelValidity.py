import random
import time
from pandas import read_csv
from sklearn.metrics import RocCurveDisplay, roc_auc_score
from sklearn.model_selection import KFold, LeaveOneOut, train_test_split
import numpy as np
from sklearn.linear_model import LogisticRegressionCV
import matplotlib.pyplot as plt
import boxplot

def Validity(axs,debug=0):

    aucs = "C://Users//Víctor//Desktop//TFM//Enviroment//Validity//auc.txt"

    fileauc = open(aucs,"a")

    graficas = "C://Users//Víctor//Desktop//TFM//Enviroment//Graficas//"

    path="C://Users//Víctor//Desktop//TFM//Enviroment//Validity//DataValidity2.csv"

    file = read_csv(path,sep=";")

    X = file.iloc[:,[2,4]]
    Y = file.iloc[:,5]

    random_state = np.random.RandomState()

    X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.2, random_state=random_state)

    tries=0

    while sum(y_test) == len(y_test) or sum(y_test) == 0 or sum(y_train) == len(y_train) or sum(y_test) == 0:
        random_state = np.random.RandomState()

        X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.2, random_state=random_state)

        tries +=1

    clf = LogisticRegressionCV(random_state=random_state,cv=KFold(10),n_jobs=12).fit(X_train, y_train)


    if debug == 1:print("Predicción:",list(clf.predict(X_test)))
    if debug == 1:print("Real:",list(y_test))

    try:
        auc = roc_auc_score(y_test,clf.predict_proba(X_test)[:, 1])
    except:
        auc = 1

    pred = list(clf.predict(X_test))

    m = 0
    if sum(pred) == len(pred) or sum(pred) == 0:
        m = 1


    fileauc.write(str(auc)+";")

    display = RocCurveDisplay.from_estimator(
            clf,
            X_test,
            y_test,
            ax=axs,
            color = (0.5+(1-auc)/2,0.2,0.5+(1-auc)/2),
            label="_no",
            alpha = 0.01
        )
    
    tpr = np.interp(np.linspace(0,1,100),display.fpr, display.tpr)
    tpr[0] = 0.0
    
    if debug == 1:print("Score:",clf.score(X_test, y_test))
    if debug == 1:print("Diferencia:",list(2*clf.predict(X_test)-y_test))

    _ = display.ax_.set(
            xlabel="False Positive Rate",
            ylabel="True Positive Rate",
            title="ROC Validity"
        )

    fileauc.close()

    return tpr,display.roc_auc,m,tries
    

if __name__ == '__main__':
    boxplot.borra("Validity")
    graficas = "C://Users//Víctor//Desktop//TFM//Enviroment//Graficas//"
    _,ax = plt.subplots()
    for i in range(100):
        Validity(ax)
    ax.legend().remove()
    plt.savefig(graficas+"Validity.png")
    boxplot.boxplot("Validity")