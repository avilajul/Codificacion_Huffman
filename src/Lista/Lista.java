package Lista;

import Huffman.Simbolo;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: ABRIL 20 DE 2015
 * DESCRIPCION: Clase encargada de la organizacion y acceso de nodos, permitiendo crear una lista para
 * 				su recorrido. 
 * UNIVERSIDAD DEL VALLE
 * */

public class Lista 
{
	
	private Nodo raiz;
	
	/*Descripcion:	Constructor*/
	
	public Lista()
	{
		raiz = null;   //raiz de la lista vacia.
	}
	
	/*Descripcion:	Este metodo es utilizado para crear una lista ordenada segun las frecuencias.
	 * 				Tambien es aplicado para la reorganizacion de la lista con los nodos padres creados
	 * 				de igual forma ordenados segun su frecuencia.*/
	
	public void insertarNodo(Simbolo nuevoSimbolo, Nodo hijoIzq, Nodo hijoDer)
	{
		Nodo nuevoNodo = new Nodo(nuevoSimbolo);
		nuevoNodo.setDer(hijoDer);
		nuevoNodo.setIzq(hijoIzq);
		
		/*si los hijos no son null es porque se esta creando un nodo padre con 
		 * hijos asociados, por lo tanto debe asignarse una arista y asociar un padre a los hijos.*/
		if(hijoIzq != null)
		{
			hijoIzq.setArista(0);
			hijoIzq.setPadre(nuevoNodo);
		}
		if(hijoDer != null)
		{
			hijoDer.setArista(1);
			hijoDer.setPadre(nuevoNodo);
		}
		
		//Variables auxiliares en caso de que la lista no quede ordenada
		Nodo reconocimiento1;
		Nodo reconocimiento2;
		
		//lista vacia
		if(raiz == null)
		{
			raiz = nuevoNodo;
			nuevoNodo.setSiguiente(null);
		}
		else
		{
			reconocimiento1 = raiz;
			//mientras haya al menos un nodo en la lista
			while(reconocimiento1 != null)
			{
				reconocimiento2 = reconocimiento1.getSiguiente();
				//el numero entrante debe ir al inicio de la lista?
				//la frecuencia asociada al simbolo es mmenor o igual a la frecuencia de la raiz
				if(nuevoNodo.getSimbolo().getFrecuencia() <= reconocimiento1.getSimbolo().getFrecuencia())
				{
					nuevoNodo.setSiguiente(raiz);
					raiz = nuevoNodo;
					break;
				}
				else
				{
					//la nueva frecuencia es mayor y debe ir al final de la lista?
					if ((nuevoNodo.getSimbolo().getFrecuencia() > reconocimiento1.getSimbolo().getFrecuencia()) 
							&& (reconocimiento2 == null))
					{
						reconocimiento1.setSiguiente(nuevoNodo);
						nuevoNodo.setSiguiente(null);
						break;						
					}
					else
					{
						//el nuevo nodo(frecuencia) debe ir en la mitad de otros dos nodos?
						
						if ( (reconocimiento1.getSimbolo().getFrecuencia() < nuevoNodo.getSimbolo().getFrecuencia()) 
								&& (reconocimiento2.getSimbolo().getFrecuencia() >= nuevoNodo.getSimbolo().getFrecuencia())) 
						{
							reconocimiento1.setSiguiente(nuevoNodo);
							nuevoNodo.setSiguiente(reconocimiento2);
							break;
						}
						else
						{
							reconocimiento1 = reconocimiento1.getSiguiente();   //Aumenta el reconocimiento para el siguiente ciclo							
						}
					}
				}
			}
		}
	}
	
	/*Descripcion: Obtiene la raiz	*/
	
	public Nodo getRaiz()
	{
		return this.raiz;
	}
	
	/*Descripcion:	Modificada el valor de la raiz*/
	
	public void setRaiz(Nodo raiz)
	{
		this.raiz = raiz;
	}
	
}