import random
from pandas import read_csv
from sklearn.metrics import RocCurveDisplay, roc_auc_score
from sklearn.model_selection import KFold, LeaveOneOut, train_test_split
import numpy as np
from sklearn.linear_model import LogisticRegressionCV
import matplotlib.pyplot as plt
import time
import boxplot
from pathlib import Path
import warnings

def Accuracy(axs,debug=0):

    current_path = str(Path.cwd()).replace("\\","//")

    aucs = current_path + "//Accuracy//auc.txt"

    fileauc = open(aucs,"a")

    path= current_path + "//Accuracy//DataAccuracy.csv"

    file = read_csv(path,sep=";")

    X = file.iloc[:,[2,3,4]]
    Y = file.iloc[:,5]


    random_state = np.random.RandomState()

    X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.2, random_state=random_state)

    tries = 0

    while sum(y_test) == len(y_test) or sum(y_test) == 0 or sum(y_train) == len(y_train) or sum(y_test) == 0:
        random_state = np.random.RandomState()

        X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.2, random_state=random_state)

        tries +=1



    clf = LogisticRegressionCV(random_state=random_state,cv=KFold(10),n_jobs=12).fit(X_train, y_train)


    if debug == 1:print("Predicción:",list(clf.predict(X_test)))
    if debug == 1:print("Real:",list(y_test))

    pred = list(clf.predict(X_test))

    m = 0
    if sum(pred) == len(pred) or sum(pred) == 0:
        m = 1


    auc = roc_auc_score(y_test,clf.predict_proba(X_test)[:, 1])

    inv = 0

    if auc < 0.5:
        y_test = 1 - y_test
        auc = roc_auc_score(y_test,clf.predict_proba(X_test)[:, 1])
        inv = 1

    fileauc.write(str(auc)+";")

    
    display = RocCurveDisplay.from_estimator(
            clf,
            X_test,
            y_test,
            ax=axs,
            color = (0.2,0.5+(1-auc)/2,0.2),
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
            title="ROC Accuracy"
        )
    
    fileauc.close()
    
    return tpr,display.roc_auc,m,tries,inv

    
if __name__ == '__main__':
    boxplot.borra("Accuracy")
    current_path = str(Path.cwd()).replace("\\","//")
    graficas = current_path + "//Graficas//"
    _,ax = plt.subplots()
    ax.legend("Legend")
    warnings.filterwarnings("ignore")
    for i in range(100):
        Accuracy(ax)
    warnings.filterwarnings("default")
    plt.savefig(graficas+"Accuracy.png")   
    boxplot.boxplot("Accuracy") 