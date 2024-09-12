import os

directory = 'C:\\Users\\Víctor\\Desktop\\TFM\\Archivos\\sc\\c'

file = os.listdir(directory)

out = open(directory+'\\output.txt', 'w')

for name in file:
    out.write(name + '\n')

out.close()