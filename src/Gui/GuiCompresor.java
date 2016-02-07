package Gui;
import java.awt.Container;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;

import Archivo.Archivo;
import Huffman.CodificacionHuffman;

/* AUTORES: JULIAN EDUARDO AVILA, DIANA LUCIA AVILA
 * CODIGOS: 1053506 - 1356358
 * FECHA: ABRIL 18 DE 2015
 * DESCRIPCION: Clase de interfaz grafica de usuario
 * 				para la seleccion de un archivo a ser comprimido
 * 				a traves del algoritmo de codificacion Huffman.
 * UNIVERSIDAD DEL VALLE
 * */


public class GuiCompresor extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lblTitulo,lblArchivo;
	private JTextField txtRuta;
	private JButton btnComprimir, btnBuscar;
	private JFileChooser flcBuscar;
	private Container contenedor = getContentPane();
	private String ruta;
	private FileNameExtensionFilter filtro;

	/*Descripcion: 	Constructor, instancia los componentes que forman parte de
	 * 				la insterfaz.*/
	public GuiCompresor()
	{		
		setSize(430,200);
		setTitle("Compresor Huffman");
		setResizable(false);  //Colocando fijo el tamano de la ventana.
		setLocationRelativeTo(null);
		contenedor.setLayout(null);
		
		setIconImage(new ImageIcon("src/img/icono.png").getImage());		
		
		lblTitulo 		= new JLabel("COMPRESOR HUFFMAN");
		lblArchivo 		= new JLabel("Archivo: ");
		
		txtRuta 		= new JTextField();
		flcBuscar		= new JFileChooser();		
		filtro 			= new FileNameExtensionFilter(".txt", "txt");
		
		btnComprimir	= new JButton("Comprimir");
		btnBuscar		= new JButton("Examinar...");
				
		lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 20));		
		lblTitulo.setBounds(80, 10, 300, 30);
		lblArchivo.setBounds(20,  80, 50, 20);
		
		flcBuscar.setFileFilter(filtro);
		txtRuta.setBounds(80, 80, 200, 20);		
		btnBuscar.setBounds(300, 80, 100, 30);
		btnComprimir.setBounds(140, 130, 100, 30);
		
		btnComprimir.addActionListener(this);
		btnBuscar.addActionListener(this);		
				
		contenedor.add(lblTitulo);
		contenedor.add(lblArchivo);
		contenedor.add(txtRuta);
		contenedor.add(btnBuscar);
		contenedor.add(btnComprimir);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	/*Descripcion:  Metodo que recibe como parametro la ruta donde esta el archivo
	 * 				e instancia el objeto archivo, lo carga y lo retorna.*/
	public String cargarArchivo(String ruta)
	{
		Archivo archivo = new Archivo();		
		return archivo.cargarArchivo(ruta);
	}
	
	/*Descripcion: Eventos en los botones*/
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnBuscar)
		{
			flcBuscar.showOpenDialog(this);
			txtRuta.setText(flcBuscar.getSelectedFile().getAbsolutePath());
			ruta = flcBuscar.getSelectedFile().getAbsolutePath();
		}	
		
		if(e.getSource() == btnComprimir)
		{
			CodificacionHuffman codificacion = new CodificacionHuffman(cargarArchivo(ruta));
			codificacion.frecuencia();
		
			if (codificacion.getCaracterValido()) 
			{
				codificacion.ordenarFrecuencias();
				codificacion.crearNodosArbol();	
				codificacion.desencolarColaCaracter();
				codificacion.salidaArchivo();	
			}		
			else
			{
				JOptionPane.showMessageDialog(null,"El archivo contiene caracteres no validos, por favor vuelva a intentarlo","Error",0);
			}	
		}
	}
}
