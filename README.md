# LGIM_Interview_Assignment_3L
My submission of the technical assignment for the LGIM Graduate Software Engineer position.

### How to run the code.
The program is a command-line application and should be started via the Main class. One should cd to the bin directory via their terminal and type:

    java Main [absolute path to text file]

This will launch the application with the absolute path as the command-line argument.

### Project structure

- src:
    - Contains the source code: specifically the Main class which is used to run the code and the textfilestats package containing the TextFile class.
- bin:
    - Contains the bytecode.
-  resources:
    - Contains a sample text file and test cases that are used for the JUnit testing.
    - "test_file_one.txt" is a normal test case; the text file contains words with punctuation over several lines.
    - "test_file_two.txt" is an empty test case; the text file is empty.
    - "test_file_three.txt" is a test case containing only special characters.
    - "test_file_four.txt" is a test case containing a single letter.
- interview-assignment-3L.pdf:
    - Is the .pdf detailing the task of the technical assignment.

### Design Justifications
- The application features an object-oriented programming architecture so that the extensibility is maximised. Moreover, it allows for encapsulation of the data and abstraction of the implementation details, making the attributes more secure and computations more user-friendly.
- Please also see comments and javadoc for assumptions, preconditions, postconditions, and data structure justifications. 
