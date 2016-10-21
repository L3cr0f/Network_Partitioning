# Network_Partitioning
This program provides you the chance to calculate the partitions and their size of your network

The following explanation is spanish, I will try to translate it as soon as I can, but for now if you have some doubts, please, ask me.

Voy a realizar una breve explicación acerca de la ejecución del programa sobre particionamiento IP que he realizado. Para empezar voy a explicar como llevar a cabo su compilación, para ello sitúese con su terminal en el directorio CN en el que se encuentran las carpetas src y bin, una vez allí ejecute el comando : javac -d bin -cp src src/CNPro/*.java . Ahora, para ejecutarlo escriba el siguiente comando en su terminal: java -cp bin CNPro.NetworkPartitioning .
Una vez el programa esta ejecutándose lo primero que le pregunta es por una dirección IP relacionada con la red que quieres montar. Con esta IP el programa va a hacer los cálculos necesarios para devolverle el numero de red y la dirección broadcast, tanto de la red global, como de cada una de las subredes.
El siguiente dato que nos piden es el número de subredes queremos, una vez introducimos el valor nos pide los datos de las subredes, el nombre (profesores, estudiantes, ...) y el tamaño de esta, este valor se cambia a la potencia de dos inmediatamente superior (23 → 32), salvo que no haya mucho margen, un 12%, (29 → 32 → 64), con este valor calcula su mascara de red con la que calculará posteriormente el prefijo de la subred y su dirección de broadcast. Una vez el programa posee todos los tamaños, calcula el tamaño global de la red y, al igual que a las particiones, adecua su tamaño a la potencia de 2 correspondiente. Luego calcula la máscara de red para calcular el el prefijo de la red y la dirección broadcast de esta.
Lo último que le pide el programa es el modo de particionamiento, tenemos tres para elegir. El primero es el modo normal, es decir, asigna los números de red y las direcciones de broadcast en función del orden en el que introdujiste los valores. El siguiente modo es el modo de orden decreciente, es decir, ordena las subredes de mayor a menor y asigna en ese orden los números de red y las direcciones de broadcast. El último modo es el de orden creciente, similar al anterior pero en orden inverso, de menor a mayor. Cabe destacar que una vez se selecciona un método se realiza una repartición de la red (como la vista en clase) para comprobar que todos los nodos han sido asignados y para conocer que particiones quedan sin asignar (espacio libre de la red).
Al finalizar el programa se nos muestra en primer lugar los datos de la red global, seguidos de los de las subredes.</br>

TESTS:</br>
En este primer test introduzco la siguiente informacion:</br>
Direccion IP inicial = 212.1.1.0</br>
A = 75 hosts</br>
B = 35 hosts</br>
C = 20 hosts</br>
D = 18 hosts</br>
También selecciono el primer modo, si seleccionase el segundo modo la salida sería igual debido a que introduje los datos en orden decreciente.</br>

![alt tag](https://github.com/ernsferrari/Network_Partitioning/blob/master/Images/Network_Partitioning_1.png)

Si selecciono el tercer modo la salida es la siguiente:</br>

![alt tag](https://github.com/ernsferrari/Network_Partitioning/blob/master/Images/Network_Partitioning_2.png)

El próximo test que voy a realizar es el visto en el examen 4 de este semestre con los siguientes datos:</br>
Dirección IP = 193.146.112.10</br>
A = 20</br>
B = 30</br>
C = 40</br>
D = 50</br>

La salida obtenida es la siguiente:</br>

![alt tag](https://github.com/ernsferrari/Network_Partitioning/blob/master/Images/Network_Partitioning_3.png)

Cabe destacar que el particionamiento se llevo a cabo con el modo 2, en el cual se ordenan de mayor a menor, esto se sabe debido a que los valores fueron introducidos en el orden mencionado en anterioridad. Además se puede observar que sobra una partición de 32 hosts.</br>
Voy a hacer un último test en el que se ponga a prueba esto último mencionado: IP = 190.1.12.0</br>
A = 650</br>
B = 50</br>
La salida será:</br>

![alt tag](https://github.com/ernsferrari/Network_Partitioning/blob/master/Images/Network_Partitioning_4.png)

Podemos comprobar que sobran cuatro particiones, de 512, de 256, de 128 y de 64 hosts.</br>
