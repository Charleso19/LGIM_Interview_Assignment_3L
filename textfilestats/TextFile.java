package textfilestats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This implementation forms part of the LGIM Interview Assignment 3L.
 * 
 * This class is an object-oriented programming representation of a text file,
 * that performs the following statistical computations:
 * 
 * 1) whitespace delimited word count.
 * 2) line count
 * 3) average number of letters per word (to one decimal place)
 * 4) most common letter.
 * 
 * Assumptions:
 * 
 * 1) We assume the text file has zero blank lines before, after, and in the
 *    middle of the text file.
 * 2) The pathToFile path name should the the absolute path to the text file
 *    on the user's computer.
 * 
 * @author Owen Charles
 * @version 2021/03/14
 */
public class TextFile {
	
	// Instance variables are final as they should not be changed.
	private final String pathToFile;
	private final File         file;
	
	/**
	 * The constructor initialises the absolute path and the File instance.
	 * 
	 * @param pathToFile is the absolute path to the text file.
	 */
	public TextFile(String pathToFile) {
		
		this.pathToFile = pathToFile;
		this.file       = new File(pathToFile);
	}
	
	/**
	 * Getter method for the pathToFile instance variable.
	 * 
	 * @return a String representing the absolute path to the text file.
	 */
	public String getPathToFile() {
		
		return pathToFile;
	}
	
	/**
	 * Getter method for the File instance variable.
	 * 
	 * @return the File attribute of the TextFile object.
	 */
	public File getFile() {
		
		return file;
	}

	/**
	 * Returns the number of whitespace delimited words in the text file.
	 * 
	 * Precondition: There are no blank lines in the text file.
	 * Postcondition: The whitespace delimited word count will be returned.
	 * 
	 * @return An int representing the whitespace delimited word count.
	 */
	public int countWords() {

		int wordCount = 0;

		try (Scanner scanner = new Scanner(getFile())) {

			while (scanner.hasNext()) {

				wordCount++;
				scanner.next();
			}


		} catch (FileNotFoundException e) {

			TextFile.error(e);

		}
		
		return wordCount;

	}

	/**
	 * Returns the number of lines in the text file.
	 * 
	 * Precondition: There are no blank lines in the text file.
	 * Postcondition: The number of lines will be returned.
	 * 
	 * @return the number of lines in the text file.
	 */
	public int countLines() {

		int numberOfLines = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(getPathToFile()))) {

			while (reader.readLine() != null) {

				numberOfLines++;

			}

		} catch (FileNotFoundException e) {

			TextFile.error(e);

		} catch (IOException e) {

			TextFile.error(e);
		}
		
		return numberOfLines;

	}

	/**
	 * The method calculates and returns the average number of letters per word.
	 * 
	 * The average number of letters per word (to one decimal place) is calculated
	 * via the following formula:
	 * 
	 * Total number of letters in the text file / total number of whitespace delimited words in the text file.
	 * 
	 * 
	 * Precondition: Letters are defined as any character a-z, A-Z, including the
	 * 				 special characters: `¬|!"£$%^&*()_+#~'@;:}[*<>/,.
	 * 
	 * Postcondition: The average number of letters per word to one decimal place
	 * 				  will be returned as a double.
	 * 
	 * @return a double representing the average number of letters per word to one
	 *         decimal place.
	 * 		   
	 */
	public double averageLettersPerWord() {

		/* Have used wordCount variable here instead of reusing countWords method
		 * as overhead would negatively effect performance.
		 */
		int wordCount = 0;
		int numberOfLetters = 0;

		try (Scanner scanner = new Scanner(getFile())) {

			// Step 1: Add up all letters and words in the text file.
			while (scanner.hasNext()) {

				char[] letters = scanner.next().toCharArray();
				numberOfLetters += letters.length;

				wordCount++;

			}

		} catch (FileNotFoundException e) {

			TextFile.error(e);
		}
		
		// Step 2: Return the average.
		return TextFile.divideAndRound(numberOfLetters, wordCount, 1);
	}

	/**
	 * The method computes and returns the most common letter in the text file.
	 * 
	 * Precondition 1: We assume that upper-case and lower-case letters represent the
	 * 				   same letter.
	 * 
	 * Precondition 2: We assume there is only one most common letter in the text
	 *				   document. If there is more than one letter that occurs the
	 *				   most times, then it is not guaranteed which common letter
	 *				   will be returned.
	 *
	 * Postcondition: The most common letter in the text file is returned. 
	 * 
	 * @param regex is used to filter what is and what is not classed as a letter.
	 * @return a char representing the most common letter in the text file.
	 */
	public char computeCommonLetter(final String regex) {

		/* Implemented a HashMap instead of TreeMap as insertion and lookup operations
		 * are O(1) for HashMap, whereas they are O(log n) for TreeMap. As order is not
		 * necessary, a HashMap is the best option.
		 */
		Map<Character, Integer> letterToAmount = new HashMap<>();

		Pattern pattern = Pattern.compile(regex);

		try (Scanner scanner = new Scanner(getFile())) {

			while (scanner.hasNext()) {

				char[] characters = scanner.next().toLowerCase().toCharArray();

				for (char element : characters) {

					Matcher matcher = pattern.matcher(String.valueOf(element));

					/* If the char is part of the alphabet AND the char is already in the map, then
					 * we increment its value.
					 */
					if (matcher.matches() && letterToAmount.containsKey(element)) {
						
						// Increment the value for char in the map.
						letterToAmount.put(element, letterToAmount.get(element) + 1);
						
					
						/* Else, if the char is part of the alphabet AND the char is NOT already in the
						 * map, then we add it to the map.
						 */
					} else if (matcher.matches() && !(letterToAmount.containsKey(element))) {
						
						// The char is present, and is therefore associated with a one.
						letterToAmount.put(element, 1);
					}

				}

			}

		} catch (FileNotFoundException e) {

			TextFile.error(e);

		}
		
		
		// Find max value in Map and return the char it is associated with.
		int maxAmount = 0;
		char mostCommonLetter = 0;
		
		for (Map.Entry<Character, Integer> entry : letterToAmount.entrySet()) {
			
			if (entry.getValue() > maxAmount) {
				
				maxAmount = entry.getValue();
				mostCommonLetter = entry.getKey();
				
			}
			
		}

		return mostCommonLetter;

	}

	/* A private method to divide two numbers and round it to one decimal place. The
	 * method improves modularity; the implementation of the method can be changed
	 * for different results.
	 */
	private static double divideAndRound(final double dividend, final double divisor, final int places) {

		BigDecimal bigDividend = new BigDecimal(dividend);
		BigDecimal bigDivisor = new BigDecimal(divisor);
		BigDecimal bigAverage = bigDividend.divide(bigDivisor, 1, RoundingMode.HALF_UP);

		/* Conversion to double may lose some precision; we assume the accuracy lost is not
		 * an issue for the numbers produced via analysing text files.
		 */
		return bigAverage.doubleValue();
	}

	// A method that displays the Exception and exits.
	private static void error(Exception e) {

		System.err.println(e.getClass().getSimpleName() + " error has occured. Exiting...");
		System.exit(-1);

	}
}
