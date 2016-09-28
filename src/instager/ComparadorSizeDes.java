package instager;

import java.util.Comparator;

public class ComparadorSizeDes implements Comparator<Imagen>{

	@Override
	public int compare(Imagen o1, Imagen o2) {
		int Nombre1 = o1.getWidth();
		int Nombre2 = o2.getWidth();
		
		return Nombre2-Nombre1;
	}
}