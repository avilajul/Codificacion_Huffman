package ListaCodificacion;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: Mayo 09 DE 2015
 * DESCRIPCION: Contiene y maneja los datos del nodo. 
 * 				Caracter,codigo y siguiente.
 * UNIVERSIDAD DEL VALLE
 * */

public class NodoCodigo 
{
	private int caracter;
	private String codigo;
	private NodoCodigo siguiente;
	
	
	/*Descripcion: Constructor */
	public NodoCodigo(int caracter, String codigo)
	{
		this.caracter 	= caracter;
		this.codigo		= codigo;				
	}
	
	/*Descripcion: Metodos de control y acceso*/
	public void setCaracter(int caracter) 
	{
		this.caracter = caracter;
	}
	
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	
	public void setSiguiente(NodoCodigo siguiente)
	{
		this.siguiente = siguiente;
	}
	
	public int getCaracter()
	{
		return this.caracter;
	}
	
	public String getCodigo()
	{
		return this.codigo;
	}
	
	public NodoCodigo getSiguiente()
	{
		return this.siguiente;
	}
}
