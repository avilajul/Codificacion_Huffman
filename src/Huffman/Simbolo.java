package Huffman;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: ABRIL 18 DE 2015
 * DESCRIPCION: Clase que permite acceder, modificar y crear Simbolos
 * 				los cuales seran necesarios para almacenar una frecuencia de aparicion
 * 				y el caracter correspondiente.
 * UNIVERSIDAD DEL VALLE
 * */
public class Simbolo 
{

	private int frecuencia;
	private char caracter;
	
	/*Descripcion:	Constructor*/
	public Simbolo(int frecuencia,char caracter) 
	{
		this.frecuencia = frecuencia;
		this.caracter	= caracter;
	}
	/*Descripcion:	Metodos de control y acceso*/
	public int getFrecuencia()
	{
		return this.frecuencia;
	}
	
	public char getCaracter()
	{
		return this.caracter;
	}
	
	public void setFrecuencia(int frecuencia)
	{
		this.frecuencia =  frecuencia;
	}
	
	public void setCaracter(char caracter)
	{
		this.caracter = caracter;
	}
}
