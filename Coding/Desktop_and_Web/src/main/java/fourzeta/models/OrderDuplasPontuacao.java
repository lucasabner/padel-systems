package fourzeta.models;

import java.io.Serializable;
import java.util.Comparator;

public class OrderDuplasPontuacao implements Comparator,Serializable {

	@Override
	public int compare(Object o1, Object o2) {
		Dupla d1 = (Dupla) o1;
		Dupla d2 = (Dupla) o2;
		if (d1.getPonTotal() > d2.getPonTotal()) {
			return -1;
		}
		return 0;
	}
}