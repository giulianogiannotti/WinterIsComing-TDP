package GOT;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;
/**
 * Proyecto de implementacion para segunda instacia de examen final libre de la materia Tecnologia de Programacion
 * @author Giuliano Giannotti
 *
 */
public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logica logica;
	private JButton botonComenzar, botonCargar, botonEn, botonEs;
	private Map<String, Double> entradas;
	private Entry<String, Double>[] arreglo;
	private InputStream fisEs, fisEn;
	private Properties properties;
	private JLabel lblTitulo, lblUno, lblDos, lblTres, lblCuatro, lblCinco, lblcambiarLenguaje;
	private JTextArea textUno, textDos, textTres, textCuatro, textCinco;
	private JList listaArchivos;
	DefaultListModel<String> list_model = new DefaultListModel<>();  
	public GUI() {
		setTitle("Winter is Coming");
		setResizable(false);
		getContentPane().setLayout(null);
		properties = new Properties();
        try {
			 fisEs = new FileInputStream("esp.properties");
			 fisEn = new FileInputStream("eng.properties");
			 properties.load(fisEs);
		    } catch (IOException e) {
			         e.printStackTrace();
		}
		inicializar();
	}

	private void inicializar() {
		
	 
		listaArchivos = new JList(list_model);
		listaArchivos.setBounds(441,70, 150, 150);
		listaArchivos.setVisible(false);	
		listaArchivos.addListSelectionListener(new ListSelectionListener() {

	            @Override
	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	System.out.print(listaArchivos.getSelectedIndex());
	                	entradas = logica.traerEntrada(listaArchivos.getSelectedIndex());
	    				Iterator<Entry<String, Double>> it = entradas.entrySet().iterator();
	    				arreglo = (Entry<String, Double>[]) new Entry[5];
	    				int index = 0;
	    				while (it.hasNext() && index < 5) {
	    					Entry<String, Double> act = it.next();
	    					arreglo[index] = act;
	    					index++;

	    				}

	    				ubicarPalabras();
	                	
	            		
	                }
	            }

			
	        });
		
		getContentPane().add(listaArchivos);
		
		
	
		logica = new Logica();
		// Botones de la GUI
		botonCargar = new JButton("Cargar Directorio");
		botonCargar.setBounds(350, 256, 150, 23);
		
		botonCargar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				     creoJField();
				    
		        }

			
		});
		getContentPane().add(botonCargar);
		
		botonComenzar = new JButton("Comenzar");
		botonComenzar.setEnabled(false);
		botonComenzar.setBounds(60, 256, 150, 23);
		botonComenzar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				logica.mapearPalabras();
				entradas = logica.organizarEntradas();
				Iterator<Entry<String, Double>> it = entradas.entrySet().iterator();
				arreglo = (Entry<String, Double>[]) new Entry[5];
				int index = 0;
				while (it.hasNext() && index < 5) {
					Entry<String, Double> act = it.next();
					arreglo[index] = act;
					index++;

				}

				ubicarPalabras();
				listaArchivos.setVisible(true);
			}
		});

		getContentPane().add(botonComenzar);

		botonEs = new JButton("Español");
		botonEs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fisEs = new FileInputStream("esp.properties");
					properties.load(fisEs);
					actualizarComponentes(properties);

				} catch (IOException e1) {
					     e1.printStackTrace();
				 }
            }
		});
		botonEs.setBounds(237, 10, 132, 26);
		getContentPane().add(botonEs);

		botonEn = new JButton("English");
		botonEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					fisEn = new FileInputStream("eng.properties");
					properties.load(fisEn);
					actualizarComponentes(properties);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		botonEn.setBounds(441, 10, 132, 26);
		getContentPane().add(botonEn);

		// Etiqueta del titulo sobre las palabras
		lblTitulo = new JLabel("Las cinco palabras mas usadas en el directorio son");
		lblTitulo.setBounds(130, 40, 332, 26);
		getContentPane().add(lblTitulo);

		// Etiquetas
		lblUno = new JLabel("1");
		lblUno.setBounds(200, 80, 12, 14);
		getContentPane().add(lblUno);

		lblDos = new JLabel("2");
		lblDos.setBounds(200, 115, 12, 14);
		getContentPane().add(lblDos);

		lblTres = new JLabel("3");
		lblTres.setBounds(200, 150, 12, 14);
		getContentPane().add(lblTres);

		lblCuatro = new JLabel("4");
		lblCuatro.setBounds(200, 185, 12, 14);
		getContentPane().add(lblCuatro);

		lblCinco = new JLabel("5");
		lblCinco.setBounds(200, 220, 21, 14);
		getContentPane().add(lblCinco);

		//Espacio donde iran las palabras con mas apariciones
		textUno = new JTextArea();
		textUno.setEditable(false);
		textUno.setBounds(220, 80, 150, 22);
		getContentPane().add(textUno);

		textDos = new JTextArea();
		textDos.setEditable(false);
		textDos.setBounds(220, 115, 150, 22);
		getContentPane().add(textDos);

		textTres = new JTextArea();
		textTres.setEditable(false);
		textTres.setBounds(220, 150, 150, 22);
		getContentPane().add(textTres);

		textCuatro = new JTextArea();
		textCuatro.setEditable(false);
		textCuatro.setBounds(220, 185, 150, 22);
		getContentPane().add(textCuatro);

		textCinco = new JTextArea();
		textCinco.setEditable(false);
		textCinco.setBounds(220, 220, 150, 22);
		getContentPane().add(textCinco);

		lblcambiarLenguaje = new JLabel("Cambiar idioma");
		lblcambiarLenguaje.setBounds(37, 10, 132, 26);
		getContentPane().add(lblcambiarLenguaje);
	}

	/**
	 * Muestra en los labels las cinco palabras con mayor aparicion en los archivos.
	 * Setea al principio como vacio por si queremos mostrar las cinco palabras con mayor aparicion
	 * de un archivo en especifico
	 */
	private void ubicarPalabras() {
        textUno.setText("");
        textDos.setText("");
        textTres.setText("");
        textCuatro.setText("");
        textCinco.setText("");
		textUno.setText(arreglo[0].getKey() + " = " + String.format("%.3f", arreglo[0].getValue()) + "%");
		textDos.setText(arreglo[1].getKey() + " = " + String.format("%.3f", arreglo[1].getValue()) + "%");
		textTres.setText(arreglo[2].getKey() + " = " + String.format("%.3f", arreglo[2].getValue()) + "%");
		textCuatro.setText(arreglo[3].getKey() + " = " + String.format("%.3f", arreglo[3].getValue()) + "%");
		textCinco.setText(arreglo[4].getKey() + " = " + String.format("%.3f", arreglo[4].getValue()) + "%");

	}

	/**
	 * Actualiza las componentes de los botones y labels dependiendo del idioma escogido.
	 * @param p propiedades
	 */
	private void actualizarComponentes(Properties p) {
		lblcambiarLenguaje.setText(p.getProperty("idioma"));
		lblTitulo.setText(p.getProperty("titulo"));
		botonCargar.setText(p.getProperty("c_directorio"));
		botonComenzar.setText(p.getProperty("comienzo"));

	}
	
	/**
	 * Crea el JField para buscar los archivos al dar click en el boton Cargar Directorio
	 */
	private void creoJField() {
		JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int returnVal = fileChooser.showOpenDialog( this );
        
        if( returnVal == JFileChooser.APPROVE_OPTION) {
        	
        	File directorySelected = fileChooser.getSelectedFile();//devolvera el directorio seleccionado
        	
        	FilenameFilter filter = (dir, name) -> name.endsWith(".txt");//Filtro para seleccionar solo los .txt
        	File[] listOfFiles = directorySelected.listFiles(filter);
        	
        	for(int i=0; i < listOfFiles.length; i++) {
        		list_model.addElement(listOfFiles[i].getName());  
        		
        	}
        	logica.cargarCarpeta(listOfFiles);
        	botonComenzar.setEnabled(true);
       
    
        		
        	
	}
	}

}
