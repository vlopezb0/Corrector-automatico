
import os
import time
from matplotlib import pyplot as plt

def boxplot(tipos = None,name= ''):
    graficas = "C://Users//Víctor//Desktop//TFM//Enviroment//Graficas//"
    path = "C://Users//Víctor//Desktop//TFM//Enviroment//"
    if tipos is None: 
        tipos = ["Accuracy","Coste","Well_Formed","Validity","Testability","Quality"]
    else:
        tipos = [tipos]
    auc = "//auc.txt"
    # _, axs = plt.subplots(2, 3,figsize = (15,15))

    # i=0
    # j=0

    for tipo in tipos:
        dir = path + tipo + auc
        file = open(dir,"r")
        datos = file.readlines()[0].split(";")
        datos = list(map(lambda x:float(x),datos[:-1]))
        _,axact = plt.subplots()
        plt.ylim(0.45,1.05)
        axact.boxplot(datos,)
        tipoa = tipo
        if tipoa == "Coste": tipoa += " (Líneas)"
        axact.set(ylabel = "Area under the curve (AUC)", title= "AUC " + tipoa)
        # plt.show()
        # i += 1
        # if i == 3:
        #     j += 1
        #     i = 0
        # for l in lines:
        # plt.savefig(graficas+(time.strftime("%x,%X")).replace(r'/',"-").replace(r':',"-")+".png")
        file.close()
        plt.savefig(graficas+tipo+name+"Box"+".png")

def borra(tipos = None):
    if tipos is None: 
        tipos = ["Accuracy","Coste","Well_Formed","Validity","Testability","Quality"]
    else:
        tipos = [tipos]    
    path = "C://Users//Víctor//Desktop//TFM//Enviroment//"
    auc = "//auc.txt"

    for tipo in tipos:
        dir = path + tipo + auc
        os.remove(dir)

if __name__ == '__main__':
    # borra()
    boxplot("Coste")
    pass
