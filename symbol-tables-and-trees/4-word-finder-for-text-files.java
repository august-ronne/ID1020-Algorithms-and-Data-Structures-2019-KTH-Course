import edu.princeton.cs.algs4.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;

public class WordIndices {
    private WordIndices() {}

    /**
     * main method driving the unit test
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // cleanTextFile(); -- this method only called once
        configureProgramInput();
        int numberOfWordsToRead = 100;
        String word = "ebook";
        printWordIndices(word, numberOfWordsToRead);

    }

    public static void printWordIndices(String word, int numberOfWordsToRead) {
        HashMap<String, LinkedList<Integer>> wordIndicesMap = new HashMap<>();
        LinkedList<Integer> wordIndices = new LinkedList<Integer>();
        int wordsRead = 0;
        int index = 0;
        while (!StdIn.isEmpty() && wordsRead < numberOfWordsToRead) {
            String key = StdIn.readString().toLowerCase();
            wordsRead++;
            if (key.equals(word)) {
                wordIndices.add(index);
            }
            index += key.length();
        }
        wordIndicesMap.put(word, wordIndices);
        StdOut.println("\nThe program has read the first " + numberOfWordsToRead + " lines of the novel" +
                " 'A Tale Of Two Cities'.\nYou have asked the program to calculate the character" +
                " indices where you find the word '" + word + "'.\nThe indices are listed below:\n");
        for (String s : wordIndicesMap.keySet()) {
            for (Integer i : wordIndicesMap.get(s)) {
                StdOut.println(" " + i + " ");
            }
        }
    }

    /**
     * Configures the input stream for the program.
     */
    public static void configureProgramInput() {
        String filePath = "C:\\Users\\August Ronne\\IdeaProjects\\Lab3Task2a\\src\\two_cities_clean.txt";
        try {
            FileInputStream fi = new FileInputStream(new File(filePath));
            System.setIn(fi);
        } catch (Exception e) {
            StdOut.println("No such file in given directory");
        }
    }

    /**
     * Read input from a .txt-file and write the cleaned output to a new
     * .txt-file created by the method. Only called once, hence not called
     * in main in this example program.
     */
    public static void cleanTextFile() {
        In inputFile = new In(
                new File("C:\\Users\\August Ronne\\IdeaProjects\\Lab3Task2a\\src\\98-0.txt"));
        Out outputFile = new Out(
                ("C:\\Users\\August Ronne\\IdeaProjects\\Lab3Task2a\\src\\two_cities_clean.txt"));

        while (inputFile.hasNextChar()) {
            char c = inputFile.readChar();
            if (!Character.isAlphabetic(c) && !(c == ' ') && !(c == '\n')) {
                outputFile.print(' ');
            } else {
                outputFile.print(c);
            }
        }
    }
}
