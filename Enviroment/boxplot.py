from pathlib import Path 
from matplotlib import pyplot as plt
import collections
import os


def boxplot(tipos = None):
    ''' Función para realizar diagramas de cajas de los auc de las distintas medidas

    Parametros:
        -tipos = Medidas a realizar, si es None se harán todas
    '''
    # Path relativo
    current_path = str(Path.cwd()).replace("\\","//")

    # Apertura de ficheros
    graficas = current_path + "//Graficas//"
    path = current_path + "//"

    # Elección de tipos
    if tipos is None: 
        tipos = ["Accuracy","Coste","Well_Formed","Validity","Testability","Quality"]
    else:
        tipos = [tipos]
    auc = "//auc.txt"

    # Para cada tipo
    for tipo in tipos:
        # Leemos archivo auc
        dir = path + tipo + auc
        file = open(dir,"r")
        datos = file.readlines()[0].split(";")
        datos = list(map(lambda x:float(x),datos[:-1]))
        # Creamos grafica y ploteamos
        _,axact = plt.subplots()
        plt.ylim(0.45,1.05)
        bp = axact.boxplot(datos)
        # Ponemos labels y guardamos
        tipoa = tipo
        if tipoa == "Coste": tipoa = "Cost (Tokens)"
        axact.set(ylabel = "Area under the curve (AUC)", title= "AUC " + tipoa)
        file.close()
        plt.savefig(graficas+tipo+"Box"+".png")


def borra(tipos = None):
    ''' Función para borrar los archivos de auc de las distintas medidas

    Parametros:
        -tipos = Medidas a borrar, si es None se harán todas
    '''
    # Path relativo
    current_path = str(Path.cwd()).replace("\\","//")
    path = current_path + "//"
    auc = "//auc.txt"

    # Selección de tipos
    if tipos is None: 
        tipos = ["Accuracy","Coste","Well_Formed","Validity","Testability","Quality"]
    else:
        tipos = [tipos]  
    
    # Para cada tipo borramos
    for tipo in tipos:
        dir = path + tipo + auc

        if os.path.isfile(dir):
            os.remove(dir)

        
if __name__ == '__main__':
    borra()
