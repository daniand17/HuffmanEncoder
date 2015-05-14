import java.util.PriorityQueue;

public class HuffmanEncoder {

	private int[] letterCounts;
	private char[] supportedLetters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', ' ', ',', '.', '\'', '\n', '!', '?', '\t',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', };

	private String[] keys;
	private char[] uniqueChars;

	public HuffmanEncoder() {

		letterCounts = new int[supportedLetters.length];
		for (int i = 0; i < letterCounts.length; i++)
			letterCounts[i] = 0;
	}

	public String getHuffmanCode(String sentence) {

		sentence = sentence.toLowerCase();

		char[] chars = sentence.toCharArray();

		for (char c : chars)
			++letterCounts[getLetterPosition(c)];

		PriorityQueue<Graphnode> graphPQ = new PriorityQueue<Graphnode>(
				new GraphnodeComparator());

		// Create all the graph nodes and put them in the graph priority queue
		for (int i = 0; i < letterCounts.length; i++)
			if (letterCounts[i] > 0)
				graphPQ.offer(new Graphnode(new Letter(letterCounts[i], ""
						+ supportedLetters[i])));

		Graphnode graph = null;

		while (!graphPQ.isEmpty()) {

			Graphnode lowestFreq = graphPQ.remove();
			Graphnode nextLowest;
			if (!graphPQ.isEmpty()) {
				nextLowest = graphPQ.remove();

				Letter conjunction = new Letter(lowestFreq.getData().getCount()
						+ nextLowest.getData().getCount(), lowestFreq.getData()
						.getLetter() + nextLowest.getData().getLetter());

				Graphnode joinedNode = new Graphnode(conjunction);

				joinedNode.setLeftChild(lowestFreq);
				joinedNode.setRightChild(nextLowest);
				graphPQ.add(joinedNode);
			} else {
				graph = lowestFreq;
				break;
			}
		}

		String huffmanRepresentation = "";

		for (char c : chars)
			huffmanRepresentation += graph.generateHuffmanPrefix("" + c);

		// Find how many unique letters there are
		int count = 0;
		for (int i = 0; i < letterCounts.length; i++)
			if (letterCounts[i] > 0)
				count++;

		keys = new String[count];
		uniqueChars = new char[count];

		count = 0;
		for (int i = 0; i < letterCounts.length; i++) {

			if (letterCounts[i] > 0) {
				String prefix = graph.generateHuffmanPrefix(""
						+ supportedLetters[i]);
				keys[count] = prefix;
				uniqueChars[count] = supportedLetters[i];
				count++;
			}
		}

		return huffmanRepresentation;
	}

	private int getLetterPosition(char c) {

		for (int i = 0; i < supportedLetters.length; i++)
			if (supportedLetters[i] == c)
				return i;

		return 0;

	}

	public String decodeHuffmanCode(String huffmanCode) {

		String decodedString = "";

		int strStart = 0;
		for (int i = 0; i < huffmanCode.length(); i++) {

			if (isCharacter(huffmanCode.substring(strStart, i))) {

				decodedString += getCharacter(huffmanCode
						.substring(strStart, i));
				strStart = i;
			}
		}

		return decodedString;
	}

	private String getCharacter(String code) {
		for (int i = 0; i < keys.length; i++) {

			if (code.equals(keys[i]))
				return "" + uniqueChars[i];

		}

		return "";
	}

	public String[] getHuffmanKeys() {
		return keys;
	}

	private boolean isCharacter(String str) {
		for (String key : keys)
			if (str.equals(key))
				return true;
		return false;
	}
}
