import java.util.Comparator;

public class LetterComparator implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {

		Letter lhs = (Letter) arg0;
		Letter rhs = (Letter) arg1;

		if (lhs.getCount() > rhs.getCount())
			return 1;
		else if (lhs.getCount() < rhs.getCount())
			return -1;

		return 0;
	}

}
