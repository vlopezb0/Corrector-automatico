import random
from pandas import concat
from pandas import read_csv
from sklearn.linear_model import LogisticRegressionCV
from sklearn.model_selection import KFold, train_test_split
from sklearn.model_selection import LeaveOneOut
import matplotlib.pyplot as plt
from sklearn.metrics import RocCurveDisplay, roc_auc_score
import numpy as np
import time

def WellFormed(axs,debug = 0):

    aucs = "C://Users//Víctor//Desktop//TFM//Enviroment//Well_Formed//auc.txt"

    fileauc = open(aucs,"a")

    archivos = "C://Users//Víctor//Desktop//TFM//Enviroment//Well_Formed//Data/"
    graficas = "C://Users//Víctor//Desktop//TFM//Enviroment//Graficas//"

    names = ["mtpga","mtpgb","mtpgc","iua","iub"]
    term = ".csv"
    files = [] 
    for name in names:
        files.append(read_csv(archivos + name + term, sep=";"))

    file = concat(files)



    label = file.iloc[:,1]
    X = file.iloc[:,1:-7]
    Y = file.iloc[:,-4]


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

    try:
        auc = roc_auc_score(y_test,clf.predict_proba(X_test)[:, 1])
    except:
        auc = 1

    fileauc.write(str(auc)+";")

    display = RocCurveDisplay.from_estimator(
            clf,
            X_test,
            y_test,
            ax=axs,
            color = (0.5+(1-auc)/2,0.2,0.2),
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
            title="ROC WellFormed"
        )

    fileauc.close()

    return tpr,display.roc_auc,m,tries


if __name__ == '__main__':
    graficas = "C://Users//Víctor//Desktop//TFM//Enviroment//Graficas//"
    _,ax = plt.subplots()
    for i in range(100):
        WellFormed(ax)
    ax.legend().remove()
    plt.savefig(graficas+"WellFormed.png")