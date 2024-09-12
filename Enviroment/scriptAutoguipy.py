import pyautogui as pag
import pyperclip
import random


pag.PAUSE = 0.2 # Pausa entre cada llamada por si acaso algo sale mal (Hay que moverse a una esquina con el raton para parar)
pag.FAILSAFE = True # Failsafe activado


num_programas = 14 # Número de programas de un problema en concreto
"""
SETUP
ChatGPT abierto en la ventana superior izquierda
Texto con el input de la métrica que queramos en la ventana inferior derecha
Excel en la ventana inferior izquierda
Carpeta y visual en la ventana superior derecha
Carpeta el primer icono de la barra de tareas
"""

def procesa_funcion(pos):
    # Nuevo chat (menu)
    pag.click(760,25,duration=random.random()+0.5) 
    # Nuevo chat
    pag.hotkey('ctrl','shift','o')
    #  Cerrar ventana 
    pag.click(random.randint(910,920),random.randint(320,325),duration=random.random()+0.5) 
    # Ventana input
    pag.click(1500,800) 
    # Seleccionar todo
    pag.hotkey('ctrl', 'e') 
    # Copia
    pag.hotkey('ctrl', 'c') 
    # Ir al nuevo chat
    pag.click(random.randint(200,700),random.randint(420,450),duration=random.random()+0.5)
    # Pega
    pag.hotkey('ctrl', 'v') 
    # Ir a donde a que pegar el codigo
    for i in range(8):
        pag.press('up')
    # Carpeta barra de tareas
    pag.click(200,1050) 
    #  Selecciona archivo
    for _ in range(pos+1):
        pag.press('up')
    pag.click(1200,280) 
    for _ in range(pos):
        pag.press('down')
    pag.press('enter')
    # Sleep
    sleep(2)
    # Seleccionar todo
    pag.hotkey('ctrl', 'a') 
    # Copia
    pag.hotkey('ctrl', 'c') 
    # Ir al chat
    pag.click(random.randint(200,700),random.randint(380,450),duration=random.random()+0.5) 
    # Pegar
    pag.hotkey('ctrl', 'v') 
    # Darle al boton para iniciar
    pag.click(random.randint(900,925),random.randint(425,445),duration=random.random()+0.5) 
    # Sleep
    esperar()
    # Cerrar ventana
    pag.click(random.randint(400,410),random.randint(230,240),duration=random.random()+0.5) 
    # Volver a hacer consulta
    pag.click(random.randint(80,800),random.randint(420,450),duration=random.random()+0.5)
    # Escribir (Por probar algo nuevo xd)
    pag.typewrite("Pon la nota en un bloque de codigo, solo el numero")
    # Darle al boton para iniciar
    pag.click(random.randint(900,925),random.randint(425,445),duration=random.random()+0.5) 
    # Sleep
    sleep(2)
    # Ir al chat
    pag.click(random.randint(50,800),random.randint(380,450),duration=random.random()+0.5) 
    
    try:
        # Hacer comb para copiar codigo
        pag.hotkey('ctrl','shift',';')
        if len(pyperclip.paste()) > 4:
            print("Salté")
            raise Exception
    except:
        # Sleep
        pag.moveTo(random.randint(50,800),random.randint(300,350),duration=1)
        pag.scroll(-100)
        x,y =pag.locateCenterOnScreen('Fotos\\copy1.png',confidence=0.8)
        x += random.randint(-5,5)
        y += random.randint(-5,5)
        pag.click(x,y)
        pass
    
    # Celda excel
    pag.click(490,530) 
    
    # Celda excel again 
    pag.click(356,860) 
    #  Busca donde pegarlo
    for _ in range(pos+1):
        pag.press('up')
    
    pag.press('down')
    #  Busca donde pegarlo
    for _ in range(pos):
        pag.press('down')
    # Pega
    pag.hotkey('ctrl', 'v') 
    # Just in case
    pag.press('enter')
    


def esperar():
    acabado = False
    while not acabado:
        # Sleep
        pag.moveTo(random.randint(0,1919),random.randint(0,1079),duration=1)
        try:
            pag.locateCenterOnScreen('Fotos\\send2.png')
            acabado = True
        except:
            try:
                x,y = pag.locateCenterOnScreen('Fotos\\fin.png')
                pag.click(x,y)
            except:
                pass

def sleep(x):
    pag.moveTo(random.randint(1,1918),random.randint(1,1078),duration=x)


for i in range(num_programas):
    procesa_funcion(i)
