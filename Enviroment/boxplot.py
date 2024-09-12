
import os
import time
from matplotlib import pyplot as plt
from pathlib import Path 

def boxplot(tipos = None,name= ''):
    current_path = str(Path.cwd()).replace("\\","//")

    graficas = current_path + "//Graficas//"

    path = current_path + "//"
    if tipos is None: 
        tipos = ["Accuracy","Coste","Well_Formed","Validity","Testability","Quality"]
    else:
        tipos = [tipos]
    auc = "//auc.txt"

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
        file.close()
        plt.savefig(graficas+tipo+name+"Box"+".png")

def borra(tipos = None):
    if tipos is None: 
        tipos = ["Accuracy","Coste","Well_Formed","Validity","Testability","Quality"]
    else:
        tipos = [tipos]    

    current_path = str(Path.cwd()).replace("\\","//")

    path = current_path + "//"
    auc = "//auc.txt"

    for tipo in tipos:
        dir = path + tipo + auc

        if os.path.isfile(dir):
            os.remove(dir)

        

if __name__ == '__main__':
    borra()
    # boxplot("Coste")
    pass
