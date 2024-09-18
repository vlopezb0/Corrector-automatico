Esto es el código perteniente al Trabajo de Fin de Máster "Aplicación de Modelos Grandes de Lenguaje para la evaluación problemas de programación".

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

