import time
from pandas import read_csv
from sklearn.metrics import RocCurveDisplay, auc, roc_auc_score
from sklearn.model_selection import KFold, LeaveOneOut, train_test_split
import numpy as np
from sklearn.linear_model import LogisticRegressionCV
import matplotlib.pyplot as plt
import itertools
import boxplot

def Quality(axs,comb,debug=0):

    aucs = "C://Users//Víctor//Desktop//TFM//Enviroment//Quality//auc.txt"

    fileauc = open(aucs,"a")

    path="C://Users//Víctor//Desktop//TFM//Enviroment//Quality//TodoslosDatos2.csv"

    file = read_csv(path,sep=";")

    X = file.iloc[:,comb]
    Y = file.iloc[:,9]

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

    if debug == 1:print("Score:",clf.score(X_test, y_test))
    if debug == 1:print("Diferencia:",list(2*clf.predict(X_test)-y_test))
    auc = roc_auc_score(y_test,clf.predict_proba(X_test)[:,1])

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
                color = (0.5+(1-auc)/2,0.5+(1-auc)/2,0.5+(1-auc)/2),
                lw=1,
                alpha=0.01,
                label="_no"
            )

    # axs.get_legend().remove()

    tpr = np.interp(np.linspace(0,1,100),display.fpr, display.tpr)
    tpr[0] = 0.0


    _ = display.ax_.set(
                xlabel="False Positive Rate",
                ylabel="True Positive Rate",
                title="ROC Quality"
            )

    fileauc.close()

    return tpr,display.roc_auc,m,tries

if __name__ == '__main__':
    graficas = "C://Users//Víctor//Desktop//TFM//Enviroment//Graficas//"
    _,ax = plt.subplots()
    
    lista = [(2,3,4,5,6,7)]
    # for i in range(2,6):
    #     lista.extend(list(itertools.combinations([2,3,4,5,6,7,8],i+1)))

    for j,comb in enumerate(lista):
        porcentaje = (int) (j/len(lista) * 100)
        rayitas = (int) (porcentaje/2)
        numIter = "Iteración número: " + str(j).rjust(3) + " de " + str(len(lista)) + " ["+"#"*rayitas+"-"*(50-rayitas)+"] " + str(porcentaje) + "%"
        print(numIter, end='')
        print('\b' * len(numIter), end='', flush=True)
        _,ax = plt.subplots()
        ax.plot(
            (0,1),
            (0,1),
            label = "Al azar (AUC = 0.5)",
            color = "black",
            linestyle = "--"
        )
        # boxplot.borra("Quality")
        tprs = []
        aucs = []
        for i in range(100):
            tpr, aucroc = Quality(ax,list(comb))
            tprs.append(tpr)
            aucs.append(aucroc)

        tpr_medio = np.mean(tprs, axis=0)
        tpr_medio[-1] = 1.0
        auc_medio = auc(np.linspace(0, 1, 100),tpr_medio)

    
        # ax.legend()

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

        ax.legend()

        plt.savefig(graficas+"Quality"+str(comb)+".png")    
        boxplot.boxplot("Quality",str(comb))
    # ax.legend().remove()
    # plt.savefig(graficas+"Quality.png")
