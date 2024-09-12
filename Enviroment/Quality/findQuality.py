import collections
import os
import time
from pandas import read_csv
from sklearn.metrics import RocCurveDisplay, roc_auc_score
from sklearn.model_selection import train_test_split
import numpy as np
from sklearn.linear_model import LogisticRegressionCV
import matplotlib.pyplot as plt
import itertools

# def Quality(axs):
graficas = "C://Users//Víctor//Desktop//TFM//Enviroment//Graficas//"

path="C://Users//Víctor//Desktop//TFM//Enviroment//Quality//TodoslosDatos.csv"

file = read_csv(path,sep=";")

X = file.iloc[:,[2,3,4,5,6,7,8]]
Y = file.iloc[:,9]

mejores = collections.defaultdict(lambda : 0)
puntuacion_media = collections.defaultdict(lambda : 0)

lista = []
for i in range(2,6):
    lista.extend(list(itertools.combinations([2,3,4,5,6,7],i+1)))


for j in range(500):
    random_state = np.random.RandomState()

    # _,ax = plt.subplots()

    best = 0
    bests = []

    for i in lista:
        i = list(i)


        print(i)

        X = file.iloc[:,i]

        X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.2, random_state=random_state)


        clf = LogisticRegressionCV().fit(X_train, y_train)


        print("Predicción:",list(clf.predict(X_test)))
        print("Real:",list(y_test))

        # display = RocCurveDisplay.from_estimator(
        #         clf,
        #         X_test,
        #         y_test,
        #         ax=ax,
        #     )


        print("Score:",clf.score(X_test, y_test))
        print("Diferencia:",list(2*clf.predict(X_test)-y_test))
        a = roc_auc_score(y_test,clf.predict_proba(X_test)[:,1])
        print("AUC:", a)
        if a > best:
            best = a
            mejori = i
            bests.append((best,i))


    # _ = display.ax_.set(
    #             xlabel="False Positive Rate",
    #             ylabel="True Positive Rate",
    #             title="ROC curves first model"
    #         )



    # plt.show()

    # _,ax = plt.subplots()

    print(mejori)
    print(bests)

    best = 0
    bests2 =[]

    for i in bests:
        print(i)
        ant = i[0]
        i = i[1]

        print(i)

        X = file.iloc[:,i]

        X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.2, random_state=random_state)


        clf = LogisticRegressionCV().fit(X_train, y_train)


        print("Predicción:",list(clf.predict(X_test)))
        print("Real:",list(y_test))




        print("Score:",clf.score(X_test, y_test))
        print("Diferencia:",list(2*clf.predict(X_test)-y_test))
        a = roc_auc_score(y_test,clf.predict_proba(X_test)[:,1])
        print("AUC:", a)
        if a > best:
            best = a
            mejori = i
        bests2.append((a,i))

    #     display = RocCurveDisplay.from_estimator(
    #             clf,
    #             X_test,
    #             y_test,
    #             ax=axs[1][2],
    #             label=(a,i,ant)
    #         )


    # _ = display.ax_.set(
    #             xlabel="False Positive Rate",
    #             ylabel="True Positive Rate",
    #             title="ROC Quality"
    #         )

    # plt.savefig(graficas+(time.strftime("%x,%X")).replace(r'/',"-").replace(r':',"-")+".png")

    

    mejores[str(bests[-1][1])] += 1
    puntuacion_media[str(bests[-1][1])] = (puntuacion_media[str(bests[-1][1])] * (mejores[str(bests[-1][1])]-1) + bests[-1][0]) / mejores[str(bests[-1][1])]

    # print("Aqui",bests[-1])
    # print(bests2[-1])
#     file2 = open("xd.txt","a")
#     print(bests)
#     print(bests2)
#     file2.writelines(str(bests[-1])+'\n')
#     file2.writelines(str(bests2[-1])+'\n'+'\n')

# file2.close()
print(mejores)
print(puntuacion_media)