package textfilestats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for the TextFile class in the src/textfilestats package.
 * 
 * @author Owen Charles
 * @version 2021/03/17
 */
public class TextFileTest {
	
	private TextFile testFileOne;
	private TextFile testFileTwo;
	private TextFile testFileThree;
	private TextFile testFileFour;
	private TextFile testFileFive;
	
	@BeforeEach
	public void constructTestTextFiles() {
		
		testFileOne = new TextFile("resources/test_file_one.txt");
		testFileTwo = new TextFile("resources/test_file_two.txt");
		testFileThree = new TextFile("resources/test_file_three.txt");
		testFileFour = new TextFile("resources/test_file_four.txt");
		testFileFive = new TextFile("resources/test_file_five.txt");
		
	}
	
	@Test
	public void testCountWords() {
		
		assertEquals(169, testFileOne.countWords());
		assertEquals(0, testFileTwo.countWords());
		assertEquals(3, testFileThree.countWords());
		assertEquals(1,testFileFour.countWords());
		assertEquals(4, testFileFive.countWords());
		
	}
	
	@Test
	public void testCountLines() {
		
		assertEquals(21, testFileOne.countLines());
		assertEquals(0, testFileTwo.countLines());
		assertEquals(3, testFileThree.countLines());
		assertEquals(1, testFileFour.countLines());
		assertEquals(2, testFileFive.countLines());
	}
	
	@Test
	public void testAverageLettersPerWord() {
		
		assertEquals(4.5, testFileOne.averageLettersPerWord());
		assertEquals(0, testFileTwo.averageLettersPerWord());
		assertEquals(22.0, testFileThree.averageLettersPerWord());
		assertEquals(1, testFileFour.averageLettersPerWord());
		assertEquals(4.8, testFileFive.averageLettersPerWord());
		
	}
	
	@Test
	public void testComputeCommonLetter() {
		
		final String regex = "[a-z]";
		
		assertEquals('e', testFileOne.computeCommonLetter(regex));
		assertEquals(0, testFileTwo.computeCommonLetter(regex));
		assertEquals(0, testFileThree.computeCommonLetter(regex));
		assertEquals('z', testFileFour.computeCommonLetter(regex));
		assertEquals('w', testFileFive.computeCommonLetter(regex));
		
	}
}
