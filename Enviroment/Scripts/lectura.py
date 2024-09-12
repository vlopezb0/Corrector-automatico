from openpyxl import load_workbook
wb = load_workbook(filename = 'Programas.xlsx')

sheet_ranges = wb['Hoja1']
# print(sheet_ranges['D18'].value)
inicio = -1
nombre = str(sheet_ranges['D1'].value) + "%" + str(sheet_ranges['A1'].value) + "%" + str(sheet_ranges['C1'].value).replace(":","_") + ".java"
fich = open(nombre,"w")

for i in range(10047):
    
    if inicio != sheet_ranges['E'+str(i+2)].value:
        fich.close()

        nombre = str(sheet_ranges['D'+str(i+2)].value) + "%" + str(sheet_ranges['A'+str(i+2)].value) + "%" + str(sheet_ranges['C'+str(i+2)].value).replace(":","_") + ".java"

        fich = open(nombre,"w")

        inicio = 1

        print("Operando con fichero", nombre)

    linea = sheet_ranges['F'+str(i+2)].value 

    if linea == None:
        linea = "\n"
    else:
        linea+= "\n"

    fich.write(linea)
        
    inicio += 1
   