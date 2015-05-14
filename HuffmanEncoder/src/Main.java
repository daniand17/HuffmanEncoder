public class Main {

	public static void main(String[] args) {

		HuffmanEncoder encoder = new HuffmanEncoder();

		String sentence = "Hello! My name is Andy, and I have a lovely bunch of coconuts."
				+ " Do you want to know how many a \nlovely bunch of coconuts is? It's 1234567890"
				+ " coconuts. ";
		String huffmanCode = encoder.getHuffmanCode(sentence);

		float uncompressedLength = sentence.length() * 8f;
		float compressedLength = huffmanCode.length();

		int cols = (int) Math.ceil(Math.sqrt(compressedLength));

		char[] charArray = huffmanCode.toCharArray();

		String str = "";
		for (int i = 0; i < charArray.length; i++)
			if (i % cols == 0)
				str += "\n" + charArray[i];
			else
				str += "" + charArray[i];

		System.out.println(str);

		System.out.println("\nUncompressed string takes up "
				+ sentence.length() + " bytes.\n");

		System.out.println("Compressed string takes up "
				+ (charArray.length / 8f) + " bytes\n");

		System.out
				.println("Compressed length is "
						+ (uncompressedLength / compressedLength)
						+ " times smaller!\n");

		String decodedString = encoder.decodeHuffmanCode(huffmanCode);

		System.out.println(decodedString);

	}
}
