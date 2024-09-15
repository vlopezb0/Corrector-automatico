from pathlib import Path
import re

"""
Función para contar lineas de un fichero

Args:
    -path: Directorio donde se encuentra el fichero

Returns:
    -num_lineas: Número de lineas del fichero    

"""
def count_lines(path):

    fich = open(path,'r')

    num_lineas = len(fich.readlines())

    return num_lineas
# Problema: Se puede escribir todo en una linea para evitar coste

"""
Función para contar palabras de un fichero

Args:
    -path: Directorio donde se encuentra el fichero

Returns:
    -num_palabras: Número de palabras del fichero    

"""
def count_palabras(path):

    fich = open(path,'r')

    num_palabras = sum(list(map(len,(map(str.split,fich.readlines()))))) 

    return num_palabras
# Problema: Si no se usan espacios pueden pasarse lineas enteras por una palabra


"""
Función para contar tokens de un fichero

Args:
    -path: Directorio donde se encuentra el fichero

Returns:
    -num_tokens: Número de tokens del fichero    

"""
def count_tokens(path):

    fich = open(path,'r')
    
    lineas = fich.read()
    
    tokens = re.split('(;|{|}|\)|\(|\.|\+|\-|\=|\n|\s|\"|\,|\[|\])+',lineas)

    tokens = list(filter(lambda x: x != " " and x != "\n",filter(None,tokens)))

    num_tokens = len(tokens)

    return num_tokens
## Problema: Los comentarios computan en el Coste

"""
Función para contar tokens de un fichero sin los comentarios

Args:
    -path: Directorio donde se encuentra el fichero

Returns:
    -num_tokens: Número de tokens del fichero    

"""
def count_tokens_comment(path):

    fich = open(path,'r')
    
    lineas = fich.readlines()

    tokens = list(map(lambda x : x.split("//")[0],lineas))

    tokens = list(map(lambda x:re.sub(r'(^\*.*|^\\\*.*|^\/\*.*)',"",x),tokens))

    tokens = list(map(lambda x: re.split('(\;|{|}|\)|\(|\.|\+|\-|\=|\n|\s|\"|\,|\[|\])+',x),tokens))

    tokens = list(map(lambda y:list(filter(lambda x: x != " " and x != "\n",filter(None,y))),tokens))

    num_tokens = sum(map(len,tokens))

    return num_tokens

# Main en caso de ejecutarlo desde este fichero
if __name__ == '__main__':
    current_path = str(Path.cwd().parent.parent).replace("\\","//")
    path = current_path + "//Archivos//mtp//a//mtpg1a-01%13_48_17.java"
    print(count_tokens_comment(path))
