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

    X = file.iloc[:,[2]]
    Y = file.iloc[:,-1]

    # X2 = pd.DataFrame(file.iloc[:,-3])
    # Y2 = pd.DataFrame(file.iloc[:,-1])

    random_state = np.random.RandomState()

    X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.2, random_state=random_state)

    # X_train = X_train.iloc[:,0:1]
    # X_cost = X_test.iloc[:,1]
    # X_test = X_test.iloc[:,0:1]

    
    # clf = LinearRegression().fit(X_train, y_train)
    clf2 = LogisticRegressionCV(random_state=random_state,cv=KFold(10),n_jobs=12).fit(X_train,y_train)


    # predicts=np.ceil(clf.predict(X_test)).reshape(-1,1)
    # if debug == 1:print("Predicción Tokens:",list(predicts[:,0]))
    # if debug == 1:print("Score Primer Modelo:",clf.score(X_test, y_test))
    if debug == 1:print("Score Segundo Modelo:",clf2.score(X_test, y_test))
    if debug == 1:print("Predicción Coste:",clf2.predict(X_test))
    if debug == 1:print("Coste real:",list(y_test))


    # test = pd.DataFrame(X_cost)

    # if debug == 1:print(clf2.predict_proba(test))

    auc = roc_auc_score(y_test,clf2.predict_proba(X_test)[:, 1])

    fileauc.write(str(auc)+";")

    display = RocCurveDisplay.from_estimator(
            clf2,
            X_test,
            y_test,
            ax=axs,
            color = (0.2,0.2,0.5+(1-auc)/2)
        )


    _ = display.ax_.set(
            xlabel="False Positive Rate",
            ylabel="True Positive Rate",
            title="ROC Cost"
        )

    fileauc.close()
    
if __name__ == '__main__':
    boxplot.borra("Coste")
    graficas = "C://Users//Víctor//Desktop//TFM//Enviroment//Graficas//"
    _,ax = plt.subplots()
    for i in range(100):
        Coste(ax)
    ax.legend().remove()
    plt.savefig(graficas+"CosteNFNMLin.png")    
    boxplot.boxplot("Coste")    