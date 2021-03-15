import textfilestats.TextFile;

/**
 * A command-line application that is used as an example application to run the
 * TextFile class methods.
 * 
 * The absolute path to the text file stored on disk is passed as a command-line
 * argument.
 * 
 * @author Owen Charles
 * @version 2021/03/14
 */
public class Main {

	public static void main(String[] args) {

		final String absolutePath = args[0];
		final String regex = "[a-z]";
		
		TextFile textFile = new TextFile(absolutePath);

		System.out.println("Word count: " + textFile.countWords());
		System.out.println("Number of lines: " + textFile.countLines());
		System.out.println("Average number of letters per word: " + textFile.averageLettersPerWord());
		System.out.println("Most common letter: " + textFile.computeCommonLetter(regex));
		
	}
}
