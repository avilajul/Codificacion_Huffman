package Archivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JOptionPane;

import ListaCodificacion.ListaCodigo;
import ListaCodificacion.NodoCodigo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: ABRIL 18 DE 2015
 * DESCRIPCION: Clase para manejo de archivos, lectura y escritura sobre ellos.
 * MODIFICACIONES:  Mayo 16 de 215 
 * 					Se crearon los metodos: 
 * 					binarioADecimal(numeroBinario)
 * 					crearArchivoTabla(cantidadNodos, listaCodigo, profundidad) 
 * 					crearArchivoAscii()
 * 					crearArchivoHuffman(texto, listaCodigo)
 *  
 * UNIVERSIDAD DEL VALLE
 * */


public class Archivo 
{
	private File archivoOriginal;
	
	/*Descripcion: Constructor*/
	public Archivo() 
	{
	}	
	/*Descripcion: 	Metodo que recibe la localizacion del archivo, crea una instancia
	 * 				del este, el cual es necesario para la instancia del fileReader y el buffer
	 * 				para hacer el recorrido del archivo por cada linea y retorna el string lectura*/
	
	public String cargarArchivo(String ruta)
	{
		archivoOriginal = new File(ruta);
		String lectura="";
		
		try
		{
			FileReader file = new FileReader(archivoOriginal);
			BufferedReader buffer = new BufferedReader(file);

			String aux;
			
			while((aux = buffer.readLine())!=null)
			lectura += aux;
			
			lectura =lectura.toLowerCase();
			//JOptionPane.showMessageDialog(null, lectura);

		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"Archivo no encontrado!","Error",0);
		}
		catch (Exception e) 
		{				
			JOptionPane.showMessageDialog(null,"El Archivo contiene errores!","Error",0);
		}
		return lectura;		
	}

	/*Descripcion: Metodo que genera el archivo con el texto original ya codificado (codificacion huffman)	*/
	
	public void crearArchivoHuffman(String texto, ListaCodigo listaCodigo) throws IOException
	{
        String ruta = "src/Archivos/huffman.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        char auxCaracter;
      
        NodoCodigo imprimirNodo;
		imprimirNodo = listaCodigo.getRaiz();
        
            bw = new BufferedWriter(new FileWriter(archivo));
            
            for(int i = 0; i < texto.length(); i++)
            {
            	auxCaracter = texto.charAt(i);

            	while( imprimirNodo != null)
        		{	
            		if(auxCaracter == imprimirNodo.getCaracter())
            		{
            			bw.write(imprimirNodo.getCodigo());
            			imprimirNodo = listaCodigo.getRaiz();
            			break;
            		}        			
        			imprimirNodo = imprimirNodo.getSiguiente();
        		}            	
            }
    		bw.close();
	}

	/*Descripcion: 	Metodo que genera un archivo con una conversion a ascii del codigo huffman generado
	 * 				por el metodo crearArchivoHuffman(texto, listaCodigo) */
	
	public void crearArchivoAscii() throws IOException
	{
        String ruta = "src/Archivos/ascii.txt";        
        BufferedWriter bw;
        String texto;
        File archivo = new File(ruta);
        texto = cargarArchivo("src/Archivos/huffman.txt");
        int bits = 0;
        String numeroByte="";
        int decimal;

        	bw = new BufferedWriter(new FileWriter(archivo));
            
            for(int i = 0; i < texto.length(); i++)
            {
            	numeroByte += texto.charAt(i);
            	bits ++; 
            	
            	if (bits == 7)
            	{                		
            		decimal = binarioADecimal(numeroByte);              		          	
                	bw.write((char)decimal);              
                	bits = 0;
                	numeroByte = "";                	
            	}
            	if ((i == texto.length()-1) && (bits < 7))
            	{            		
            		decimal = binarioADecimal(numeroByte);            		
            		bw.write((char)decimal);     
            	}    	           	      
            }
    		bw.close();
	}
	
	/*Descripcion: 	Metodo que genera un archivo que contiene la tabla de: Nodos creados, 
	 * 				profundidad maxima del arbol, simbolos y codigos correspondiente*/
	
	public void crearArchivoTabla(int cantidadNodos, ListaCodigo listaCodigo, int profundidad) throws IOException
	{
        String ruta = "src/Archivos/tabla.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("Nodos creados: "+cantidadNodos+"\r\n");
        bw.write("Profundidad m�xima del �rbol: "+profundidad+"\r\n");
        bw.write("Tabla de codificaci�n generada:\r\n");
        bw.write("Simbolo:\tCodigo\r\n");
            
        NodoCodigo imprimirNodo;
    	imprimirNodo = listaCodigo.getRaiz();
    	while( imprimirNodo != null)
    	{
    		bw.write((char)imprimirNodo.getCaracter()+"\t\t"+imprimirNodo.getCodigo()+"\r\n");
    		imprimirNodo = imprimirNodo.getSiguiente();
    	}
    	bw.close();
	}
	
	/*Descripcion: 	Metodo que se encarga de hacer la conversion de un numero binario de 7 bits
	 * 				a numero decimal, el cual representa a un codigo ascii*/
	
	public int binarioADecimal(String numeroBinario)
	{
		int longitud = numeroBinario.length();//Numero de digitos que tiene nuestro binario
		int resultado = 0;//Aqui almacenaremos nuestra respuesta final
		int potencia = longitud - 1;
		  
		for(int i = 0;i < longitud;i++)
		{//recorremos la cadena de numeros
			if(numeroBinario.charAt(i) == '1')
			{
				resultado += Math.pow(2,potencia);
			  }
			  potencia --;//drecremantamos la potencia
		 }
		  return resultado;
	}
}
