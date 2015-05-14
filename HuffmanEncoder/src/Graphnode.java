public class Graphnode {

	private Graphnode leftChild;
	private Graphnode rightChild;

	private Letter letter;

	public Graphnode(Letter letter) {
		this.letter = letter;
		leftChild = null;
		rightChild = null;
	}

	public Graphnode getLeftChild() {
		return leftChild;
	}

	public Graphnode getRightChild() {
		return rightChild;
	}

	public void setLeftChild(Graphnode left) {
		leftChild = left;
	}

	public void setRightChild(Graphnode right) {
		rightChild = right;
	}

	public Letter getData() {
		return letter;
	}

	public void setData(Letter letter) {
		this.letter = letter;
	}

	public boolean contains(Letter letter) {
		return findNodeByValue(letter) != null;
	}

	public Graphnode findNodeByValue(Letter letter) {
		return findNodeByValue(this, letter);
	}

	private Graphnode findNodeByValue(Graphnode node, Letter letter) {
		if (node == null || node.letter.equals(letter))
			return node;
		else {
			Graphnode foundNode = findNodeByValue(node.getLeftChild(), letter);
			if (foundNode != null)
				return foundNode;
			else
				return findNodeByValue(node.getRightChild(), letter);
		}
	}

	public void printTree() {

		System.out.println(printNode(this, "Summary:", 0));

	}

	public String printNode(Graphnode node, String str, int depth) {

		if (node == null)
			return str;

		str += "\n";

		for (int i = 0; i < depth; i++)
			str += "|  ";

		str += node.getData().getLetter();

		return printNode(node.getRightChild(),
				printNode(node.getLeftChild(), str, ++depth), depth);
	}

	private String generateHuffmanPrefix(Graphnode node, String letter,
			String prefix) {

		if (node == null)
			return prefix;

		// If this node contains the letter
		if (node.getData().getLetter().contains(letter)) {

			// If the left subtree contains the letter go left
			if (node.getLeftChild() != null
					&& node.getLeftChild().getData().getLetter()
							.contains(letter)) {

				prefix = "0"
						+ generateHuffmanPrefix(node.getLeftChild(), letter,
								prefix);
			} else if (node.getRightChild() != null) {
				prefix = "1"
						+ generateHuffmanPrefix(node.getRightChild(), letter,
								prefix);
			}

		}

		return prefix;

	}

	public String generateHuffmanPrefix(String letter) {

		String prefix = "";
		return generateHuffmanPrefix(this, letter, prefix);

	}
}
