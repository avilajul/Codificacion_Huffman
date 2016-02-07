package Lista;
import Huffman.Simbolo;
/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: ABRIL 20 DE 2015
 * DESCRIPCION: Contiene y maneja los datos del nodo. 
 * 				Simbolo, siguiente, izquierdo, derecho
 * 				arista y padre
 * 				
 * UNIVERSIDAD DEL VALLE
 * */

public class Nodo 
{
	private Simbolo simbolo;  //dato
	private Nodo siguiente; // enlace al siguiente nodo
	private Nodo izq;
	private Nodo der;
	private int arista;  //referencia al nodo padre
	private Nodo padre;
	
	/*Descripcion:	Constructor*/
	
	public Nodo (Simbolo valor)
	{
		simbolo 	= valor;
		siguiente 	= null;
		izq			= null;
		der			= null;
		arista		= -1;
		padre		= null;
	}
	
	/*Descripcion:	Métodos de acceso y control de variables*/
	
	public Nodo getPadre()
	{
		return this.padre;
	}
	
	public void setPadre(Nodo padre)
	{
		this.padre = padre;
	}
	
	public int getArista(){
		return this.arista;
	}
	
	public void setArista(int arista)
	{
		this.arista = arista;
	}
	
	public void setIzq(Nodo izquierdo)
	{
		this.izq = izquierdo;
	}
	
	public void setDer(Nodo derecho)
	{
		this.der = derecho;
	}
	
	public Nodo getIzq()
	{
		return izq;
	}
	
	public Nodo getDer()
	{
		return der;
	}	
	
	public void setSiguiente(Nodo enlace)
	{   //ColocarNodo
		siguiente = enlace;
	}
	
	public void setSimbolo(Simbolo simbolo)
	{
		this.simbolo = simbolo;
	}
	
	public Nodo getSiguiente()
	{
		return this.siguiente;
	}
	
	public Simbolo getSimbolo()
	{
		return this.simbolo;
	}
}
