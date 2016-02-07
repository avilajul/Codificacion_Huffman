package ListaCodificacion;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: Mayo 09 DE 2015
 * DESCRIPCION: Contiene y maneja la listaCodigo. Crea la lista
 * 				que almacenara el caracter y su codigo huffman correspondiente
 * UNIVERSIDAD DEL VALLE
 * */

public class ListaCodigo 
{
	private NodoCodigo raiz;
	private NodoCodigo auxiliarRaiz;
	
	/*Descripcion:	Constructor*/
	
	public ListaCodigo() 
	{
		raiz = null;   //raiz de la lista vacia.
		auxiliarRaiz = null;
	}
	
	/*Descripcion: Obtiene la raizAuxiliarRaiz	*/
	public NodoCodigo getAuxiliarRaiz()
	{
		return auxiliarRaiz;
	}
	
	/*Descripcion: Obtiene la raiz	*/
	
	public NodoCodigo getRaiz()
	{
		return this.raiz;
	}
	
	/*Descripcion:	Modificada el valor de la raiz*/
	
	public void setRaiz(NodoCodigo raiz)
	{
		this.raiz = raiz;
	}
		
	/*Descripcion:	Este metodo es utilizado para crear una lista ordenada segun el caracter (codigo ascii).*/
	
	public void insertarNodo(NodoCodigo nodo)
	{
		NodoCodigo nuevoNodo = new NodoCodigo(nodo.getCaracter(),nodo.getCodigo());	
		
		//Variables auxiliares en caso de que la lista no quede ordenada
		NodoCodigo reconocimiento1;
		NodoCodigo reconocimiento2;		
		
		//lista vacia
		if(raiz == null)
		{
			raiz = nuevoNodo;
			auxiliarRaiz = nuevoNodo;
			nuevoNodo.setSiguiente(null);
		}
		else
		{
			reconocimiento1 = raiz;
			//mientras haya al menos un nodo en la lista
			while(reconocimiento1 != null){
				reconocimiento2 = reconocimiento1.getSiguiente();
				//el numero entrante debe ir al inicio de la lista?
				//el caracter asociado es mmenor o igual a la frecuencia de la raiz
				if(nuevoNodo.getCaracter() <= reconocimiento1.getCaracter())
				{
					nuevoNodo.setSiguiente(raiz);
					raiz = nuevoNodo;
					break;
				}
				else
				{
					//la nueva frecuencia es mayor y debe ir al final de la lista?
					if ((nuevoNodo.getCaracter() > reconocimiento1.getCaracter()) 
							&& (reconocimiento2 == null))
					{
						reconocimiento1.setSiguiente(nuevoNodo);
						nuevoNodo.setSiguiente(null);
						break;						
					}
					else
					{
						//el nuevo nodo(caracter) debe ir en la mitad de otros dos nodos?
						
						if ( (reconocimiento1.getCaracter() < nuevoNodo.getCaracter()) 
								&& (reconocimiento2.getCaracter() >= nuevoNodo.getCaracter())) 
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
		
}
