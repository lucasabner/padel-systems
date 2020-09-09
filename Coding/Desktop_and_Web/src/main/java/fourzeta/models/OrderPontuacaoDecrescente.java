package fourzeta.models;

import java.io.Serializable;
import java.util.Comparator;

public class OrderPontuacaoDecrescente implements Comparator, Serializable {

	@Override
	public int compare(Object o1, Object o2) {
		Ranking p1 = (Ranking) o1;
		Ranking p2 = (Ranking) o2;
		if (p1.getPontos() > p2.getPontos()) {
			return -1;
		}
		return 0;
	}
}