package GOT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Proyecto de implementacion para segunda instacia de examen final libre de la materia Tecnologia de Programacion
 * @author Giuliano Giannotti
 * 
 */
public class Logica {


	private File[] archivos;
	private List<String> palabras;
	private Map<String, Double> mapeoDePalabras;
	private LinkedList<LinkedList<String>> palabrasArchivo;

	
	
	public void cargarCarpeta(File[] path){
		palabrasArchivo= new LinkedList<LinkedList<String>>();
		palabras = new LinkedList<String>();
		
        archivos = path;
		for (int i = 0; i < archivos.length; i++) {
			try {
				LinkedList<String> lista_palabras = enlistarPalabras(archivos[i]);
				palabrasArchivo.add(lista_palabras);
			    } catch (IOException ex) {
			        	System.out.println(ex.getMessage());
			}
		}

	}

	
	/**
	 * Lista las palabras de un archivo en una lista enlazada y en una lista
	 * @param archivo Archivo a enlistar palabras
	 * @return Lista enlazada con las palabras del archivo
	 */
	private LinkedList<String> enlistarPalabras(File archivo) throws IOException {
		BufferedReader entrada = new BufferedReader(new FileReader(archivo));
		String linea = entrada.readLine();
		LinkedList<String> palabrasTemporal = new LinkedList<String>();
		
		while (linea != null) {
			   StringTokenizer st = new StringTokenizer(linea);
			   while (st.hasMoreTokens()) {
				      String palabra = st.nextToken();
				      palabras.add(palabra);
				      palabrasTemporal.add(palabra);
			}
			linea = entrada.readLine();
		}
		
		palabrasTemporal = eliminarLosSimbolos(palabrasTemporal);
		eliminarLosSimbolos();
		entrada.close();
		return palabrasTemporal;

	}
	/**
	 * Elimina simbolos de la lista parametrizada
	 * @param _palabrasTemporal lista enlazada de la cual eliminar simbolos
	 * @return la lista enlazada sin simbolos extraños
	 */
	private LinkedList<String> eliminarLosSimbolos(LinkedList<String> _palabrasTemporal) {
		LinkedList<String> palabrasSinSimbolos = new LinkedList<String>();
		Iterator<String> it = _palabrasTemporal.iterator();
		while (it.hasNext()) {
			   String s = it.next();
			   String reemplazo = s.replaceAll("//d", "");
			   palabrasSinSimbolos.add(reemplazo.toLowerCase());
		}
		
		return palabrasSinSimbolos;
	}

    /**
     * Elimina simbolos de la lista de palabras
     */
	private void eliminarLosSimbolos() {
		List<String> palabrasSinSimbolos = new LinkedList<String>();
		Iterator<String> it = palabras.iterator();
		while (it.hasNext()) {
			   String s = it.next();
			   String reemplazo = s.replaceAll("//d", "");
			   palabrasSinSimbolos.add(reemplazo.toLowerCase());
		}
		palabras = palabrasSinSimbolos;
	}

	/**
	 * Mapea las palabras de la lista en un hashMap
	 */
	public void mapearPalabras() {
		mapeoDePalabras = new HashMap<String, Double>();
		double cantApariciones = 0;
		double porcentaje = 0;
		double totalPalabrasLong = palabras.size();
		for (String palabra : palabras) {
			 cantApariciones = cantApariciones(palabra, palabras);
			 porcentaje = cantApariciones * 100 / totalPalabrasLong;
			 mapeoDePalabras.put(palabra, porcentaje);
		}
	}
	
	public Map<String, Double> mapearPalabras(int i) {
		
		Map<String, Double> mapeoDePalabras = new HashMap<String, Double>();
		double cantApariciones = 0;
		double porcentaje = 0;
		LinkedList<String> list_palabras= palabrasArchivo.get(i);
		double totalPalabrasLong = list_palabras.size();
		for (String palabra : list_palabras) {
			 cantApariciones = cantApariciones(palabra, list_palabras);
			 porcentaje = cantApariciones * 100 / totalPalabrasLong;
			 mapeoDePalabras.put(palabra, porcentaje);
		}
		
		return mapeoDePalabras;
	}

	/**
	 * Calcula la cantidad de apariciones de una palabra en una lista de palabras
	 * @param palabra palabra a buscar
	 * @param palabras lista en la cual buscar las palabras
	 * @return cantidad de apariciones de una palabra en una lista de palabras
	 */
	private int cantApariciones(String palabra, List<String> palabras) {
		int cant = 0;
		for (String palab : palabras) {
			if (palabra.equals(palab)) {
				cant++;
			}
		}
		return cant;
	}

	/**
	 * Organiza las entradas con un comparador en un mapeo ordenado
	 * @return mapeo con entradas ordenadas
	 */
	public Map<String, Double> organizarEntradas() {
		ValueComparator vc = new ValueComparator(mapeoDePalabras);
		TreeMap<String, Double> mapOrdenado = new TreeMap<String, Double>(vc);
		mapOrdenado.putAll(mapeoDePalabras);

		return mapOrdenado;
	}
	
	/**
	 * Retorna una entrada de map de i
	 * @param i 
	 * @return
	 */
	public Map<String, Double> traerEntrada(int i){
		Map<String, Double> traerMap = mapearPalabras(i);
		ValueComparator vc = new ValueComparator(traerMap);
		TreeMap<String, Double> mapOrdenado = new TreeMap<String, Double>(vc);
		mapOrdenado.putAll(traerMap);
		
		return mapOrdenado;
	}
	
	
	
	
	

}
