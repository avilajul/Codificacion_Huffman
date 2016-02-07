package Cola;
/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: Mayo 6 DE 2015
 * DESCRIPCION: Clase para manejo de cola de caracteres del texto leido.
 *  
 * UNIVERSIDAD DEL VALLE
 * */


public class ColaCaracter 
{
	private int inicio, tail;
	private int cola[];

	/*Descripcion: Constructor*/
	
	public ColaCaracter(int tamano)
	{
		cola = new int[tamano];
    	inicio = -1;
    	tail = -1;
	}
	
	/*Descripcion: Metodo que retorna si la cola esta vacia o no*/
	
    public boolean colaVacia()
    {
    	if(inicio == -1 && tail == -1)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /*Descripcion: Metodo que retorna si la cola esta llena o no*/
    
    public boolean colaLlena()
    {
    	if(((tail+1)%cola.length) == inicio)
    	{
    			return true;
    	}
    	else
    	{
    		return false;
		}
    }
    
    /*Descripcion: Metodo para encolar valores*/
    
    public void encolar(int valor)
    {
    	if(colaLlena())
    	{
    		System.out.println("La cola est� llena");
    	}
    	else
    	{
			if(colaVacia())
			{
				inicio = 0; tail = 0;				
			}
			else 
			{
				tail = (tail+1) % cola.length;				
			}
			cola[tail] = valor;		
    	}				
    }
 
    /*Descripcion: Metodo para desencolar valores*/
    
    public int desencolar()
    {
    	int numero= 0;
    	if(colaVacia())
    	{
    		System.out.println("La Cola est� Vac�a");    		
    	}
    	else
    	{
    		numero = cola[inicio];
    		if(inicio == tail)
    		{
    			tail = -1; inicio = -1;
    		}
    		else
    		{
    			inicio = (inicio+1) % cola.length; 
			}
		}
		return numero;
	}
}
