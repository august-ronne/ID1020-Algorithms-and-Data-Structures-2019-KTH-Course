import edu.princeton.cs.algs4.*;
import java.io.File;
import java.io.FileInputStream;

public class RedBlackTester {
    private RedBlackTester() {}

    /**
     * The main method sets up the necessary arrangements in
     * order to test the separate chaining hash data structure
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // cleanTextFile(); -- this method only called once
        configureProgramInput();
        int minWordLength = 2;
        RedBlackTree<String, Integer> rbt = new RedBlackTree<>();
        testLinearHash(rbt, minWordLength);

    }

    public static void testLinearHash(
            RedBlackTree<String, Integer> st, int minWordLength) {

        int wordsRead = 0;
        long startTime = System.nanoTime();
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString().toLowerCase();
            wordsRead++;
            if (key.length() < minWordLength) {
                continue;
            }
            if (st.contains(key)) {
                st.put(key, (st.get(key) + 1));
            } else {
                st.put(key, 1);
            }
        }
        String mostFrequentWord = "";
        st.put(mostFrequentWord, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(mostFrequentWord)) {
                mostFrequentWord = word;
            }
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        StdOut.println("\nStoring every word and its number of occurrences as" +
                "a key-value pair in a red black BST" +
                "and \nthen finding the most frequently occurring word took " +
                totalTime + " nanoseconds.\nThe most frequently occurring word was" +
                " [" + mostFrequentWord + "] with " + st.get(mostFrequentWord) + " occurrences.");

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
