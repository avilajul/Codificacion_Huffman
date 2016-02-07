# Codificacion_Huffman
Compresion de archivos planos utilizando la codificación Huffman construido en Java.

##Licencia: MIT 

###Usos permitidos:

Compresor se puede utilizar para cargar un archivo y comprirlo, segun
el algoritmo de codificacion definido. Para que el archivo se comprima correctamente,
este puede contener caracteres de la a - z en minusculas, tambien se aceptan los
caracteres: .(punto)  (espacio) y , (coma)

###Dirección de los Autores

Julian Eduardo Avila
Diana Lucia Avila


e-mail: avilatas@gmail.com
	diana.avila@correounivalle.edu.co

###Versión 

Versión 1.0 

Contiene las siguientes Clases:

Archivo.java			Clase para manejo de archivos, lectura y escritura sobre ellos.

ColaCaracter.java		Clase para manejo de cola de caracteres del texto leido.

Compresor.java			Clase principal que instancia la clase GuiCompresor
				para la seleccion de un archivo a ser comprimido
				a traves del algoritmo de codificación Huffman.

GuiCompresor.java		Clase de interfaz gráfica de usuario
				para la seleccion de un archivo a ser comprimido
				a traves del algoritmo de codificación Huffman.

CodificacionHuffman.java	Esta clase se encarga de:
				- Crear arbol huffman
				- Hacer recorrido en el arbol huffman
				- Obtener codificacion huffman

Simbolo.java			Clase que permite acceder, modificar y crear Simbolos
  				los cuales serán necesarios para almacenar una frecuencia de aparición
  				y el caracter correspondiente.

Lista.java			Clase encargada de la organización y acceso de nodos, permitiendo crear una lista para
 				su recorrido. 

Nodo.java			Contiene y maneja los datos del nodo. 
 				Simbolo, siguiente, izquierdo, derecho
 				arista y padre.

ListaCodigo.java		Contiene y maneja la listaCodigo. Crea la lista
 				que almacenara el caracter y su codigo huffman correspondiente.

NodoCodigo.java			Contiene y maneja los datos del nodo. 
 				Caracter,codigo y siguiente.



###Instalación 

- Descomprimir carpeta Compresor
- Copiar la carpeta Compresor al espacio de trabajo de Eclipse.
- Dirigirse al paquete Gui, localizar el archivo Compresor.java 
- Ejecutar proyecto
- Aparece una ventana para seleccionar el archivo a comprimir (debe ser un archivo .txt)
- Se selecciona el archvio y presiona el boton comprimir.
- Dirigirse a la carpeta Compresor del espacio de trabajo de eclipse, luego
  dirigirse a src/Archivos/ 
- Una vez en esta ruta, se pueden ver como los archivos ascii.txt, huffman.txt,
  y tabla.txt se han creado satisfactoriamente.