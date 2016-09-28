package instager;

import java.util.Comparator;

public class ComparadorNombreDes implements Comparator<Imagen>{

	@Override
	public int compare(Imagen o1, Imagen o2) {
		String Nombre1 = o1.getNombre().toUpperCase();
		String Nombre2 = o2.getNombre().toUpperCase();
		
		return Nombre2.compareTo(Nombre1);
	}

}
