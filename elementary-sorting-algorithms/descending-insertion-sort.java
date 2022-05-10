/***********************************************************************************
 * Compilation: javac <name>.java
 * Execution: java <name> int1 int 2 int3 ... intN
 *
 *      Execution explained: Enter integers separated by spaces to create
 * your desired array for the program. The first integer you enter will
 * determine the integer size, while the rest are added as elements to the array.
 *
 *      Example execution: java <name> 5 1 2 3 4 5
 * This execution will create the following array: [1, 2, 3, 4 ,5].
 ************************************************************************************/

/**
 * The {@code InsertionSortDescending} contains static methods
 * for sorting an integer array in descending order. It also
 * counts the number of inversions in the integer array.
 *
 * @author August Ronne, aronne@kth.se
 */

public class Assignment234 {
    private static int numberOfSwaps; // Keeps track of the number of element swaps
    /**
     * Sorts the array in ascending order.
     * @param array the array to be sorted
     */
    public static void sort(int[] array) {
        int size = array.length;
        for (int i = 1; i < size; i++) {
            for (int j = i; (j > 0) && (array[j] > array[j - 1]); j--) {
                swap(array, j, j - 1);
                printArray(array);
            }
        }
    }

    /**
     * Swaps two integers of an array.
     * @param array the array containing the integers
     * @param indexSmaller index of the smaller integer
     * @param indexBigger index of the bigger integer
     */
    private static void swap(int[] array, int indexBigger, int indexSmaller) {
        int temp = array[indexBigger];
        array[indexBigger] = array[indexSmaller];
        array[indexSmaller] = temp;
        numberOfSwaps++;
    }

    /**
     * Prints the contents of the integer array to the standard output.
     * @param array the array to be printed.
     */
    private static void printArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length - 1; i++) {
            sb.append("[").append(array[i]).append("], ");
        }
        sb.append("[").append(array[array.length - 1]).append("] ");
        if (numberOfSwaps > 0) {
            sb.append("(nr of element swaps: ").append(numberOfSwaps).append(" )");
        }
        System.out.println(sb.toString());
    }

    /**
     * Counts the number of inversions in the integer array.
     * @param array the array which is to have its inversions counted
     * @return the number of inversions in the array
     */
    private static int countInversions(int[] array) {
        int numberOfInversions = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    numberOfInversions++;
                    System.out.print(" [[" + i + ", " + array[i] + "" +
                            "], [" + j + ", " + array[j] + "]] ");
                }
            }
        }
        return numberOfInversions;
    }

    /**
     * Prints information about the sorting process to the
     * program user.
     * @param array the array to be sorted.
     */
    private static void printUserInformation(int[] array) {
        System.out.println("You have created an array with room for" +
                " " + array.length + " integers.");
        System.out.println("\nThe following integers have been added to your array:");
        printArray(array);
        System.out.println("\nYour array will now be sorted in descending order." +
                        " The program predicts that this will require" +
                        " " + countInversions(array) + " element swaps.\n");
        sort(array);
        System.out.println("\nYour array has been sorted");
        printArray(array);
    }

    /**
     * Receives a sequence of strings as command-line arguments.
     * With these strings the main method creates an integer array
     * which is sorted in descending order.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int arraySize = Integer.parseInt(args[0]);
        int[] array = new int[arraySize];
        for (int i = 1; i < args.length; i++) {
            array[i - 1] = Integer.parseInt(args[i]);
        }
        printUserInformation(array);
    }
}
