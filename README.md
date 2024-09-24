Esto es el código perteneciente al Trabajo de Fin de Máster "Aplicación de Modelos Grandes de Lenguaje para la evaluación problemas de programación".

Existen dos programas, el modelo de regresión logística y el script para automatizar consultas a ChatGPT.

# Modelo de regresión logística
Para ejecutarlo, solo es necesario ejecutar el comando:
```
python main.py
```

Esto generará en la carpeta Gráficas las curvas ROC de todos los indicadores, así como sus diagramas de caja.

# Script para automatizar consultas a ChatGPT
Para ejecutarlo, primero es necesario cierto SETUP:
- ChatGPT abierto en la ventana superior izquierda.
- Texto con el input del indicador que queramos en la ventana inferior derecha.
- Excel en la ventana inferior izquierda.
- Carpeta y Visual Studio Code en la ventana superior derecha.
- Carpeta el primer icono de la barra de tareas.

Una vez hecho, hay que ejecutar el comando:
```
python scriptAutoguipy.py
```

Esto bloqueará el ordenador hasta que termine el proceso, al usar el ratón y teclado. Guardará los datos en el Excel abierto.

# Stack tecnológico
El stack tecnológico es el siguiente:
- Editor de texto:
  - Visual Studio Code 1.93
- Lenguaje:
  - Python 3.10.2
- Librerías de Python:
  - sklearn 1.5.0
  - pandas 1.5.1
  - numpy 1.23.4
  - matplotlib 3.9.2
