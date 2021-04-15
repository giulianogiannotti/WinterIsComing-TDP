package GOT;

import java.util.Comparator;
import java.util.Map;
/**
 * Proyecto de implementacion para segunda instacia de examen final libre de la materia Tecnologia de Programacion
 * @author Giuliano Giannotti
 */
public class ValueComparator implements Comparator {

	Map<String, Double> b;

	public ValueComparator(Map b) {
		this.b = b;
	}

	public int compare(Object one, Object two) {
		int value = ((Double) this.b.get(two)).compareTo((Double) b.get(one));
		if (value == 0) 
		    value=1;
		
		return value;
	}
}