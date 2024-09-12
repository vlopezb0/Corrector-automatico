from cuenta_lineas import count_tokens, count_lines
import os


directory = 'C:\\Users\\Víctor\\Desktop\\TFM\\Archivos\\Todos'

file_names = os.listdir(directory)

output_file = open(directory[:-6]+'\\output.txt', 'w')


for file in file_names:
    if not file.endswith(".java"):
        continue
    path = directory+"\\"+file
    print(file)
    name = file.split("%",1)
    hora = name[1].split("_")
    hora = hora[0] + ":" + hora[1] + ":" + hora[2]
    tokens = count_lines(path)
    output_file.write(name[0] + "," + hora[:-5] + "," + str(tokens) + '\n')

output_file.close()
# print(file_names)
