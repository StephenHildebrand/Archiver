package net.shiild.archiver.util;

import java.util.Scanner;

/**
 * Archiver.java is a program that handles both compression and decompression
 * for the input to Main.java.
 * 
 * Input is restricted as follows: 1) The file may span multiple lines. 2) The
 * lines consist of "words" made up of upper- and lower-case letters separated
 * by blanks and special characters such ".,/%&!@#$?-_><". Special characters
 * are not to be compressed, they are simply copied from input file to output
 * file. 3) The file contains no digits 0123456789.
 * 
 * The archive (compressed) file is identical to the text (uncompressed) one,
 * except as follows: 1) A zero and a blank ("0 ") are prepended to the first
 * line. 2) The first occurrence of each word remains as is, but all subsequent
 * occurrences are replaced by positive integers, as specified further on. 3)
 * After the last line of text, a new line beginning with "0 " is added. The
 * rest of this line is a comment containing statistics on the compression (as
 * shown in the example below).
 * 
 * @author StephenHildebrand
 */
public class Archiver {

	private String input;

	private LinkedList list;

	private String output;

	private String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";

	private int textCharCount;

	private int archiveCharCount;

	/**
	 * Constructor for Archiver class.
	 * 
	 * @param input
	 *            to be archived or extracted
	 */
	public Archiver(String input) {
		this.input = input;
		output = ""; // Instantiate archive to an empty string.
		list = new LinkedList();
	}

	/**
	 * To archive the text, the program reads the next word from the input file
	 * and searches for it in the list. Each word checked for special characters
	 * via handleSpecial() which, if found, are handled. Each word is then
	 * passed to archiveWord() to be archived.
	 */
	public void archive() {
		Scanner sc = new Scanner(input);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			char[] lineArray = line.toCharArray();
			String word = ""; // String for building a word from chars.

			for (int i = 0; i < lineArray.length; i++) {
				textCharCount++; // Increment the text char count by 1.
				archiveCharCount++;
				// If not a continuation of a previous word.
				if (Character.isSpaceChar(lineArray[i]) || specialChars.contains("" + lineArray[i])) {
					if (word.length() > 0) { // Then this char marks end of a
												// word.
						archiveWord(word);
						word = ""; // Reset the word string.
					}
					output += lineArray[i];
				} else { // Append next letter to word string.
					word += lineArray[i];
				}
			}
			if (word.length() > 0 && Character.isAlphabetic(word.charAt(0)))
				archiveWord(word);
			output += "\n";
		}
		sc.close();
	}

	/**
	 * This method adds the word to a LinkedList, list, and inserts a numerical
	 * representation for the current word into the compressed string. If the
	 * word isn't found, it writes the word to the archive output file and
	 * inserts it at the front of the list. If the word is found in the list,
	 * the program write out it's list position to the archive, removes the word
	 * from the list, then reinserts it at the front (this is the move-to-front
	 * feature).
	 * 
	 * @param word
	 *            string to archive
	 */
	private void archiveWord(String word) {
		if (list.isEmpty()) { // Empty list: add word to front.
			list.addToFront(word);
			output += word;
		} else { // Nonempty list: find position, return it, move word to front.
			// Add 1 to position because archive count starts at 1 (not 0).
			int position = list.findPosByString(word) + 1;
			if (position == 0) { // Word not in list.
				list.addToFront(word);
				output += word;
			} else { // Word found in list.
				// Decrement archive char count by the amount that the integer
				// is less than the word it replaced.
				String intAsString = Integer.toString(position);
				archiveCharCount += (intAsString.length() - word.length());
				output += (list.findPosByString(word) + 1);

				list.remove(word);
				list.addToFront(word);
			}
		}
	}

	/**
	 * To extract text from the archive, a word read from input is written out
	 * again and inserted at the front of the list. When the number i is read,
	 * the word in the i-th position is written out, then the word is deleted
	 * from the list and reinserted at the front. Decompression ends when the
	 * number 0 is read.
	 * 
	 * @param input
	 *            string input to be extracted.
	 */
	public void extract() {
		Scanner sc = new Scanner(input);
		String word = ""; // String for building a word from chars.
		while (sc.hasNextLine() && !word.equals("0")) {
			String line = sc.nextLine();
			char[] lineArray = line.toCharArray();

			for (int i = 0; i < lineArray.length; i++) {
				// Read 0 indicated end of file
				if (lineArray[i] == '0') {

				} else if (Character.isSpaceChar(lineArray[i]) || specialChars.contains("" + lineArray[i])) {
					if (word.length() > 0) { // Then this char marks end of a
												// word.
						if (word.equals("0"))
							break;
						extractWord(word);
						word = ""; // Reset the word string.
					}
					output += lineArray[i];
				} else { // Append next letter to word string.
					word += lineArray[i];
				}
			}
			if (word.length() > 0 && Character.isAlphabetic(word.charAt(0))) {
				extractWord(word);
				word = "";
			}
			output += "\n";
		}
		sc.close();
	}

	/**
	 * To extract text from the archive, a word read from input is written out
	 * again and inserted at the front of the list. When the number i is read,
	 * the word in the i position is written out, then the word is deleted from
	 * the list and reinserted at the front.
	 * 
	 * @param word
	 *            string to extract
	 */
	private void extractWord(String word) {
		int position;
		if (Character.isDigit(word.charAt(0))) { // word is an integer.
			position = Integer.parseInt(word);
			String extractedWord = list.findStringByPos((position - 1));
			list.remove(extractedWord);
			list.addToFront(extractedWord);
			output += extractedWord;
		} else { // word is not an integer.
			list.addToFront(word);
			output += word;
		}
	}

	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * @return the textCharCount
	 */
	public int getTextCharCount() {
		return textCharCount;
	}

	/**
	 * @return the archiveCharCount
	 */
	public int getArchiveCharCount() {
		return archiveCharCount;
	}

}