
rango = 500
for i in range(500):
    porcentaje = (int) (i/rango * 100)
    rayitas = (int) (porcentaje/2)
    numIter = "Iteración número: " + str(i).rjust(3) + " de " + str(rango) + " ["+"#"*rayitas+"-"*(50-rayitas)+"] " + str(porcentaje) + "%"
    print(numIter, end='')
    print('\b' * len(numIter), end='', flush=True)

print('\b' * len(numIter),flush=True)
print("Ejecución terminada")
