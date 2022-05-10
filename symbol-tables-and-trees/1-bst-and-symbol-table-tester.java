import edu.princeton.cs.algs4.*;
import java.io.File;
import java.io.FileInputStream;

public class SymbolTableTester {
    private SymbolTableTester() {}

    public static void main(String[] args) {
        // cleanTextFile(); -- this method only called once

        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();

        configureProgramInput();

        int minWordLength = 2;

        testBinarySearchST(st, minWordLength);
        st.nToMIntervalFrequency(1, 6);

        // testBST(minWordLength);
    }

    public static void testBinarySearchST(BinarySearchST<String, Integer> st, int minWordLength) {
        int wordsRead = 0;
        int uniqueKeys = 0;
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
                uniqueKeys++;
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

        StdOut.println("\n**** BINARY SEARCH SYMBOL TABLE TEST ****" +
                "\n\nThe most frequently occurring word in the read .txt-file was [" +
                mostFrequentWord + "] with " + st.get(mostFrequentWord) + " occurrences.\n" +
                "Running this test with a BinarySearchST data structure took " + totalTime + " nanoseconds\n" +
                "\nThe .txt file contained " + uniqueKeys + " unique keys");

    }

    public static void testBST(int minWordLength) {
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();
        int wordsRead = 0;
        int uniqueKeys = 0;
        long startTime = System.nanoTime();

        while (!StdIn.isEmpty()) {
            String key = StdIn.readString().toLowerCase();
            wordsRead++;
            if (key.length() < minWordLength) {
                continue;
            }
            if (bst.contains(key)) {
                bst.put(key, (bst.get(key) + 1));
            } else {
                uniqueKeys++;
                bst.put(key, 1);
            }
        }

        String mostFrequentWord = "";
        bst.put(mostFrequentWord, 0);
        for (String word : bst.keys()) {
            if (bst.get(word) > bst.get(mostFrequentWord)) {
                mostFrequentWord = word;
            }
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        StdOut.println("\n**** BST TEST ****" +
                "\n\nThe most frequently occurring word in the read .txt-file was [" +
                mostFrequentWord + "] with " + bst.get(mostFrequentWord) + " occurrences.\n" +
                "Running this test using a BST data structure took " + totalTime + " nanoseconds." +
                "\nThe .txt file contained " + uniqueKeys + " unique keys");
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
            if (!(c == ' ') && !(c == '\n') && !Character.isAlphabetic(c)) {
                outputFile.print(' ');
            } else {
                outputFile.print(c);
            }
        }
    }
}
