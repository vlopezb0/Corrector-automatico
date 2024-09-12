import os

path = "C:\\Users\\Víctor\\Desktop\\TFM\\Archivos\\sc\\c"

archivos = os.listdir(path)

# archivos = archivos[:-1]

for archivo in archivos:
    nuevo_nombre = archivo.split("%",1)[1]
    print(archivo)
    # print(nuevo_nombre)
    os.rename(path+"\\"+archivo,path+"\\"+nuevo_nombre)