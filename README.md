# **Juego de la Vida en Java**

Este proyecto implementa el **Juego de la Vida** de Conway utilizando Java. El juego simula la evolución de células vivas en un tablero bidimensional basado en reglas simples de vecindarios. Las células pueden estar vivas o muertas, y su estado futuro depende de la cantidad de vecinos vivos en su vecindad.

## **Descripción General**

El proyecto está diseñado para simular el **Juego de la Vida** en una matriz de tamaño configurable. Cada celda puede estar en uno de dos estados: viva o muerta. A lo largo de múltiples generaciones, las células viven, mueren o permanecen en su estado dependiendo de sus vecinos. Este comportamiento se simula según distintas configuraciones de vecindarios (Moore, Von Neumann, etc.).

### **Reglas del Juego**
1. **Reglas Básicas**:
    - Una célula viva con 2 o 3 vecinos vivos sigue viva en la siguiente generación.
    - Una célula muerta con exactamente 3 vecinos vivos se vuelve viva.
    - En cualquier otro caso, la célula muere o permanece muerta.

2. **Vecindarios**:
   Se pueden seleccionar diferentes tipos de vecindarios para contar los vecinos vivos de cada celda:
    - **Vecindario Moore**: Todos los vecinos adyacentes (8 direcciones).
    - **Vecindario Von Neumann (o Programming 1)**: Solo los 4 vecinos ortogonales (arriba, abajo, izquierda, derecha).
    - **Vecindario Reverse**: Solo las diagonales.
    - **Vecindario Custom**: Un conjunto específico de vecinos.
    - **Vecindario Hexágonos**: Se utiliza un patrón hexagonal para contar los vecinos.

## **Estructura del Proyecto**

Este proyecto consta de las siguientes clases principales:

### 1. **Main**
La clase principal donde se inicializa el juego y se configuran los parámetros del tablero. Recibe argumentos de entrada para definir el tamaño de la matriz, el número de generaciones, y otros parámetros como el tipo de vecindario.

### 2. **Game**
La clase que controla la lógica del juego, gestionando el avance de las generaciones y aplicando las reglas del juego. Interactúa con la clase `Matrix` para obtener y modificar el estado del tablero.

- **Métodos principales**:
    - `runGenerations()`: Ejecuta múltiples generaciones con una pausa configurable entre cada una.
    - `resetBoard()`: Reinicia el tablero a un estado inicial.

### 3. **Matrix**
Esta clase gestiona el tablero de juego. Define el tamaño de la matriz, y proporciona métodos para obtener, establecer y mostrar el valor de las celdas. Además, se encarga de contar los vecinos vivos de cada celda en función de la configuración de vecindario seleccionada.

- **Propiedades principales**:
    - `board`: La matriz que contiene el estado de cada célula (1 para viva, 0 para muerta).
    - `directions`: Dirección de los vecinos según el vecindario seleccionado.

- **Métodos principales**:
    - `getValue(row, col)`: Devuelve el valor de la célula en las coordenadas especificadas.
    - `setValue(row, col, value)`: Establece el valor de la célula en las coordenadas especificadas.
    - `showResult()`: Muestra el tablero en consola.
    - `countNeighbors(i, j, neighborOption)`: Cuenta los vecinos vivos de una celda según el vecindario elegido.
    - `neighborSelected(option)`: Configura las direcciones de los vecinos de acuerdo con el tipo de vecindario.

### 4. **GameRulesLogic**
Esta clase contiene las reglas que definen cómo las células deben evolucionar de una generación a la siguiente. Usa la información de la clase `Matrix` para determinar el estado de cada célula.

- **Métodos principales**:
    - `applyRules()`: Aplica las reglas del juego a cada célula del tablero.

### 5. **OptionExecuteGOL**
Esta clase maneja las opciones de configuración para el juego, incluyendo el tamaño del tablero, el número de generaciones y la velocidad de avance. Permite a los usuarios ingresar parámetros por consola o mediante línea de comandos.

- **Métodos principales**:
    - `createMatrixWithParams()`: Crea la matriz con los parámetros definidos.
    - `startGameWithParams()`: Inicia el juego con los parámetros seleccionados.

## **Instrucciones de Uso**

### **Requisitos**
- Java 8 o superior.

### **Compilación y Ejecución**
Para ejecutar el juego, sigue estos pasos:

1. **Compilar el código**:
   Abre una terminal en el directorio donde tienes los archivos del proyecto y ejecuta:

2. **Ejecutar el juego**:
   Ejecuta el programa con los parámetros deseados, por ejemplo:

javac Main.java Game.java Matrix.java GameRulesLogic.java OptionExecuteGOL.java

Donde:
- `w=10`: Define el ancho del tablero (10 celdas).
- `h=10`: Define el alto del tablero (10 celdas).
- `g=50`: Número de generaciones a ejecutar.
- `s=300`: Pausa entre generaciones (en milisegundos).
- `p="101#010#111"`: Configura el estado inicial del tablero.
- `n=1`: Selecciona el vecindario (1 para Moore).

### **Configuración de Vecindarios**
Puedes elegir entre varios tipos de vecindarios mediante el parámetro `n`:
- `n=1`: Vecindario Moore.
- `n=2`: Vecindario Von Neumann.
- `n=3`: Vecindario Reverse.
- `n=4`: Vecindario Custom.
- `n=5`: Vecindario Hexágonos.

### **Visualización del Tablero**
El tablero se imprime en la consola usando `*` para las células vivas y `.` para las células muertas. El estado del tablero se muestra después de cada generación.

## **Contribuciones**

Si deseas contribuir al proyecto, por favor sigue estos pasos:
1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature-xyz`).
3. Realiza los cambios y haz un commit (`git commit -am 'Añadir nueva característica'`).
4. Haz push a la rama (`git push origin feature-xyz`).
5. Crea un pull request.