import java.util.Comparator;

public class GraphnodeComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		Graphnode lhs = (Graphnode) o1;
		Graphnode rhs = (Graphnode) o2;

		LetterComparator lc = new LetterComparator();
		return lc.compare(lhs.getData(), rhs.getData());
	}

}
