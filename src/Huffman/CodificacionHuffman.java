package Huffman;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import javax.swing.JOptionPane;

import Archivo.Archivo;
import Cola.ColaCaracter;
import Lista.Lista;
import Lista.Nodo;
import ListaCodificacion.ListaCodigo;
import ListaCodificacion.NodoCodigo;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: ABRIL 18 DE 2015
 * DESCRIPCION: Esta clase se encarga de:
 * 				- Crear arbol huffman
 * 				- Hacer recorrido en el arbol huffman
 * 				- Obtener codificacion huffman
 * UNIVERSIDAD DEL VALLE
 * */


public class CodificacionHuffman 
{	
	private int longitud;
	private String texto;
	private int[] frecuenciaSimbolos;	
	private Lista listaPesos;
	private ColaCaracter colaCaracter;
	private Nodo raiz;
	private ListaCodigo listaCodigo;
	private NodoCodigo raizCodigo;
	private String codigo;
	private Archivo archivoHuffman;
	private int contadorNodos;
	private int contadorProfundidad;
	private boolean caracterValido;
	
	public CodificacionHuffman()
	{
	}
	
	/*Descripci�n: Constructor*/
	
	public CodificacionHuffman(String texto) 
	{
		this.texto				= texto;
		this.longitud 			= texto.length();
		this.frecuenciaSimbolos = new int [128];	//se construye de tama�o 128 de acuerdo con el universo planteado en el problema.	
		this.listaPesos 		= new Lista();
		this.listaCodigo		= new ListaCodigo();		
		this.codigo				= "";
		this.archivoHuffman		= new Archivo();
		this.contadorNodos		= 0;
		this.caracterValido		= false;
	}
	
	/*Descripcion: Metodos de acceso get*/
	
	public int getContadorNodos()
	{
		return contadorNodos;
	}
	
	public int getProfundidad()
	{
		return contadorProfundidad;
	}
	
	public boolean getCaracterValido()
	{
		return this.caracterValido;
	}
	
	public String getTexto()
	{
		return this.texto;
	}
	
	/*------------------------------------------------------------------------------------------------------------------------*/
	
	/*Descripci�n: Utiliza la longitud del texto(archivo) para recorrerlo y obtener caracter
	 * 			   por caracter, al mismo tiempo utiliza una auxiliar para el conteo de la frecuencia
	 * 			   de dicho caracter.*/
	
	public void frecuencia()
	{
		char auxCaracter;
		int numAscii;

		for(int i = 0; i < longitud; i++)
		{
			auxCaracter = texto.charAt(i);	
			numAscii =(int)auxCaracter;
			frecuenciaSimbolos[numAscii] = frecuenciaSimbolos[(int)auxCaracter]+1;
			
			if( (numAscii==32)  || (numAscii >= 97 && numAscii <= 122) || 
					((numAscii >=48) && (numAscii<= 57)) || (numAscii == 44) || (numAscii == 46) )
			{
				caracterValido = true;
			}
			else 
			{
				caracterValido = false;
				break;
			}
		}		
	}
	
	/*Descripcion: Metodo que retorna la cantidad de simbolos distintos en el archivo leido*/
	
	public int getCantSimbolos()
	{
		int contaSimbolo=0;
		
		for(int i= 0; i < frecuenciaSimbolos.length;i++)
		{
			if(frecuenciaSimbolos[i] > 0)
			{
				contaSimbolo++;
			}
		}	
		return contaSimbolo;
	}
	
	/*Descripci�n: Utiliza la longitud del arreglo frecuenciaSimbolos para:
	 * 			   1. Instanciar simbolos que tendran asociados una frecuencia y un caracter.
	 *             2. Insertar el simbolo como nodo a la lista listaPesos
	 *             3. Encola los caracteres que se encuentran en el archivo*/
	
	public void ordenarFrecuencias()
	{
		colaCaracter = new ColaCaracter(getCantSimbolos());
		for(int i= 0; i < frecuenciaSimbolos.length;i++)
		{
			if(frecuenciaSimbolos[i] > 0)
			{
				Simbolo simbolo = new Simbolo(frecuenciaSimbolos[i] , (char)i);
				listaPesos.insertarNodo(simbolo,null,null);				
				colaCaracter.encolar((char)i);
				contadorNodos++;
			}
		}
	}	
	
	/*Descripcion:	Metodo que desencola los caracteres para enviarlos uno por uno al metodo getRecorrido*/
	
	public void desencolarColaCaracter()
	{
		int ascii;
			
		while(colaCaracter.colaVacia() != true)
		{
			ascii = colaCaracter.desencolar();
			getRecorrido(ascii);
		}
	}
	
	
	/*Descripcion: 	Consiste en crear lista con los nuevos nodos(padres), de la siguiente manera:
	 * 				1. Extraer de la listaPesos los datos de: 
		  			   hijoDer,hijoIzq y frecuencia, con estos datos se creara un nodo(padre).
		  			2. Insertar el nuevoSimbolo (nodo padre) en listaPesos.
		  			3. Se repite la accion hasta que encuentre un nodo que no tenga nodo para apuntar, 
		  			   es decir, el siguiente = null.
		  			*/	
	
	public void crearNodosArbol()
	{			
		Nodo hijoDer,hijoIzq;
		int sumaFrecuencia;
		Simbolo nuevoSimbolo;
		raiz = listaPesos.getRaiz();
		
		while(listaPesos.getRaiz().getSiguiente() != null)	
		{
			hijoIzq = 	listaPesos.getRaiz();			
			hijoIzq.setArista(1);
			listaPesos.setRaiz(hijoIzq.getSiguiente());  // Corremos la raiz a la siguiente posicion(nodo)
			hijoDer = 	listaPesos.getRaiz();
			hijoDer.setArista(0);			
			
			//Peso del nodoPadre
			sumaFrecuencia = hijoDer.getSimbolo().getFrecuencia() + hijoIzq.getSimbolo().getFrecuencia();
			//Instancia nodoPadre
			nuevoSimbolo = new Simbolo(sumaFrecuencia, '*');					
			
			listaPesos.insertarNodo(nuevoSimbolo,hijoIzq,hijoDer);
			listaPesos.setRaiz(listaPesos.getRaiz().getSiguiente());
			contadorNodos++;
		}			
	}
	
	/*Descripcion: 	Metodo que permite: 
	 * 				1. Medir la profundidad del arbol
	 * 				2. Recibe un ascii y busca en la lista pesos cual es igual.
	 * 				3. Una vez encuentre el caracter que es igual al ascii entrante, 
	 * 				   comienza el recorrido, comenzado por el padre, luego el padre del padre
	 * 				   y asi sucesivamente hasta llegar a la raiz
	 * 				4. Invierte el codigo del recorrido y lo envia al metodo codificacion(caracter,codigo)*/
	
	public void getRecorrido(int ascii)
	{
		int caracter, auxiliarProfundiad;
		String codigoInvertido="";
		listaPesos.setRaiz(raiz);  //iniciar el puntero en la raiz	
		contadorProfundidad = 0;
		
			while(listaPesos.getRaiz() != null)
			{
				caracter = listaPesos.getRaiz().getSimbolo().getCaracter();
				auxiliarProfundiad = contadorProfundidad;
				
				if(ascii == caracter)
				{
					while(listaPesos.getRaiz().getPadre() != null )
					{					
						codigoInvertido += listaPesos.getRaiz().getArista();					
						listaPesos.setRaiz(listaPesos.getRaiz().getPadre());
						contadorProfundidad++;
					}
					StringBuilder Obj = new StringBuilder(codigoInvertido); // Creamos el StringBuilder y le pasamos la cadena como parametro a su constructor	            
			        codigo = Obj.reverse().toString(); // Aqui es donde ocurre la magia									     
					codificacion(caracter, codigo);	
					
					if(contadorProfundidad < auxiliarProfundiad)
					{
						contadorProfundidad = auxiliarProfundiad;
					}
					break;
				}
				listaPesos.setRaiz(listaPesos.getRaiz().getSiguiente());
			}
	}
	
	/*Descripcion:	Metodo que recibe el caracter y su codigo correpondiente al recorrido del arbol
	 * 				huffman, para crear una listaCodigo con nodos con estos valores.*/
	
	public void codificacion(int caracter, String codigo)
	{	
		NodoCodigo nodoCodigo;
		nodoCodigo = new NodoCodigo(caracter, codigo);	
		raizCodigo = nodoCodigo;
		listaCodigo.insertarNodo(nodoCodigo);
		
	}
	
	/*Descripcion:	Metodo que se encarga de hacer el llamdo a crear los archivos de:
	 * 				-tabla.txt
	 * 				-huffman.txt
	 * 				-ascii.txt*/
	
	public void salidaArchivo()
	{
		try 
		{
			archivoHuffman.crearArchivoTabla(getContadorNodos(), listaCodigo, getProfundidad());
			archivoHuffman.crearArchivoHuffman(getTexto(), listaCodigo);
			archivoHuffman.crearArchivoAscii();
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null,"No se generaron los archivos codificados","Error",0);
		}
	}
}
