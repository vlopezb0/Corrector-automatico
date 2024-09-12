import pyautogui as pag
import random

pag.PAUSE = 0.2 #Pausa entre cada llamada por si acaso algo sale mal (Hay que moverse a una esquina con el raton para parar)
pag.FAILSAFE = True #Failsafe activado

# print(pag.KEYBOARD_KEYS)
"""
SETUP
ChatGPT abierto en la ventana superior izquierda
Texto con el input en la ventana inferior derecha
Excel en la ventana inferior izquierda
Carpeta y visual en la ventana superior derecha
Carpeta el primer icono de la barra de tareas
Visual el segundo icono de la barra de tareas
"""

def procesa_funcion(pos):
    #Nuevo chat (menu)
    pag.click(random.randint(10,80),random.randint(150,200),duration=random.random()+0.5) 
    #Nuevo chat
    pag.click(random.randint(310,340),random.randint(160,190),duration=random.random()+0.5)
    #Ventana input
    pag.click(1500,800) 
    #Seleccionar todo
    pag.hotkey('ctrl', 'e') 
    #Copia
    pag.hotkey('ctrl', 'c') 
    #Ir al nuevo chat
    pag.click(random.randint(80,800),random.randint(420,450),duration=random.random()+0.5)
    #Pega
    pag.hotkey('ctrl', 'v') 
    #Ir a donde a que pegar el codigo
    for i in range(8):
        pag.press('up')
    #Carpeta barra de tareas
    pag.click(200,1050) 
    # Selecciona archivo
    pag.click(1200,280) 
    for i in range(pos):
        pag.press('down')
    pag.press('enter')
    #Sleep
    pag.moveTo(random.randint(0,1919),random.randint(0,1079),duration=3)
    #Seleccionar todo
    pag.hotkey('ctrl', 'a') 
    #Copia
    pag.hotkey('ctrl', 'c') 
    #Ir al chat
    pag.click(random.randint(50,800),random.randint(380,450),duration=random.random()+0.5) 
    #Pegar
    pag.hotkey('ctrl', 'v') 
    #Darle al boton para iniciar
    pag.click(random.randint(900,925),random.randint(425,445),duration=random.random()+0.5) 
    #Sleep
    pag.moveTo(random.randint(0,1919),random.randint(0,1079),duration=5)
    #Clickar en la ventana del chat
    # pag.click(random.randint(200,700),random.randint(250,350),duration=random.random()+0.5) 
    #Ir al final
    pag.moveTo(random.randint(940,955),random.randint(240,255),duration=random.random()+0.5) 
    pag.dragTo(random.randint(940,955),random.randint(400,420),duration=random.random()+0.5)
    # pag.scroll(-10000)
    # pag.press('end')
    #Buscar boton y aleatorizar coords
    try:
        pag.scroll(100)
        x,y =pag.locateCenterOnScreen('Fotos\copy2.png')
        x += random.randint(-5,5)
        y += random.randint(-5,5)
    except:
        try:
            pag.scroll(-100)
            x,y =pag.locateCenterOnScreen('Fotos\copy.png')
            x += random.randint(-10,10)
            y += random.randint(-10,10)
        except:
            print("Ni idea")
    #Clickarlo
    pag.click(x,y)
    #Celda excel
    # pag.click(256,860) 
    # #Subir para pegar
    # pag.scroll(10000)
    #Celda excel again 
    pag.click(490,530) 
    #Busca donde pegarlo
    # for i in range(pos):
    #     pag.press('down')
    #Pega
    pag.hotkey('ctrl', 'v') 
    #Just in case
    pag.press('enter')
    # #Color (Algo me da que habra que hacerlo con reconocimiento)
    # pag.click(100,600) 
    # pag.click(156,635) 
    # pag.click(343,800) 
    # pag.click(400,777) 
    # pag.click(random.randint(390,590),random.randint(685,815)) 
    # pag.click(700,630) 





num_programas = 23

for i in range(num_programas):
    procesa_funcion(i)
