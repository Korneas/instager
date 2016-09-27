package instager;

import java.util.Comparator;

public class ComparadorNombre implements Comparator<Imagen>{

	@Override
	public int compare(Imagen o1, Imagen o2) {
		// TODO Auto-generated method stub
		return o1.getNombre().compareTo(o2.getNombre());
	}

}
