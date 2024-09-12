from pandas import read_csv
import collections
import os


def compara_fechas(f1,f2):
    h1 = f1.split(":")
    h2 = f2.split(":")

    h1 = int(h1[0])*3600+int(h1[1])*60+(int(h1[2][:2]))
    h2 = int(h2[0])*3600+int(h2[1])*60+(int(h2[2][:2]))

    return h1 < h2



path = 'C:\\Users\\Víctor\\Desktop\\TFM\\Excels\\'

path2 = 'C:\\Users\\Víctor\\Desktop\\TFM\\Archivos\\Todos\\'

file = read_csv(path+'TestedCases.csv',sep=";")

dato = file.iloc[0]
excel = open(path+'test_distintos.csv','w')
# escritura = open(path2 + str(dato[0])+'.txt','w')
lista = []
esc_act = dato[0]

tests = collections.defaultdict(lambda : [])

# tests = set()

for i in range(len(file)):
    dato = file.iloc[i]
    tests[dato[0]].append((dato[1],dato[2]))

# print(tests)

archivos = os.listdir(path2)

for archivo in archivos:
    print(archivo)
    nombre,fecha = archivo.split("%")
    fecha = fecha.replace("_",":")
    test_archivo = tests[nombre]
    distintos = set()
    for test in test_archivo:
        if compara_fechas(test[0],fecha):
            distintos.add(test[1])
    excel.writelines(str(archivo)+','+str(len(distintos))+'\n')



# for i in range(len(file)):
#     dato = file.iloc[i]
    
#     if dato[0] != esc_act:
#         escritura = open(path2 + str(esc_act)+'.txt','w')
#         # excel.writelines(str(esc_act)+','+str(len(tests))+'\n')
#         esc_act = dato[0]
#         # tests.clear()
#         lista[-1] = lista[-1][:-1]
#         escritura.writelines(lista)
#         escritura.close()
#         lista.clear()
#         # escritura = open(path2 + str(dato[0])+'.txt','w')
    
#     # tests.add(dato[2])
#     lista.append(str(dato[2])+",")

excel.close()


