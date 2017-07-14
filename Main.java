import java.util.Scanner;

/**
 * Implement an algorithm to compress and uncompress text files using linked
 * lists with the move-to-front heuristic.
 * 
 * @author StephenHildebrand
 */
public class Main {

	// private static String input;

	/**
	 * Runs the program. Uses the Archiver class to archive or extract the
	 * passed argument, depending on its contents.
	 * 
	 * @param args
	 *            String text to archive or extract.
	 */
	public static void main(String[] args) {

		String input = "";

		// Read the user input and store it in the input string.
		Scanner sc = new Scanner(System.in);

		// Read the first line of text.
//		input = "" + sc.nextLine();

		// Continue reading, line by line and store in input.
		while (sc.hasNextLine()) {
			input += sc.nextLine();
		}
		sc.close();

		// Instantiate archiver with input.
		Archiver archiver = new Archiver(input);

		if (input.startsWith("0 ")) { // Input that begins with "0 ".
			archiver.extract();
			System.out.println(archiver.getOutput());
		} else { // Input that doesn't.
			archiver.archive();
			String summary = "0 Uncompressed: " + archiver.getTextCharCount() + " bytes;  Compressed: "
					+ archiver.getArchiveCharCount() + " bytes";
			System.out.println("0 " + archiver.getOutput() + "\n" + summary);
		}
	}
}
