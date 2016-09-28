package instager;

import java.util.Comparator;

public class ComparadorTipoDes implements Comparator<Imagen>{

	@Override
	public int compare(Imagen o1, Imagen o2) {
		String Nombre1 = o1.getTipo().toUpperCase();
		String Nombre2 = o2.getTipo().toUpperCase();
		
		return Nombre2.compareTo(Nombre1);
	}

}