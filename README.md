## Algorithms & Data Structures (KTH Kista course)
Completed labs for the KTH Kista course *ID1020: Algorithms and Data Structures*

### Course Content
:link: [Course Homepage](https://www.kth.se/student/kurser/kurs/ID1020?l=en)<br /><br />
* __Basic algorithm analysis__: Simpler analysis with respect to the resource needs of algorithms in the form of time and memory.
* __Fundamental algorithms__: 
    * Simple numerical algorithms.
    * Sequential and binary search algorithms.
    * Depth first search and Width first search.
    * Sorting algorithms: selection sorting, insertion sorting, Quicksort, heapsort, mergesort.
* __Fundamental data structures__:
    * Linear lists, linked lists, doubly linked lists.
    * Stacks, queues, heaps.
    * Symbol tables, hash tables.
    * Binary trees, Red-Black trees, binary search trees.
<br />

### 1.  Bags, Queues, & Stacks
:file_folder: [View lab work](/bags-queues-stacks)<br /><br />
Tasks solved for this lab:
1. In C implement a recursive and an iterative version of a function which reads characters from stdin until a newline character is read and then prints them on stdout in reverse order. Hint: use getchar(), putchar() (or getc(), putc()). For the iterative version you may assume a fixed max length of the input.
2. Implement the above program in JAVA using one of the ADTs suggested in ch. 1.3
3. Implement a generic iterable FIFO-queue based on a double linked list.
4. Implement a generic iterable circular linked list which allows the user to insert and remove elements to/from the front and back end of the queue. Be careful when designing the API.
5. Implement a generalized queue which allows the user to remove the kth element from the queue. Assume the most recently added element has index 1.
6. Implement a filter (i.e. a program which reads from standard in until an EOF is read, does some manipulation of what is read, and then prints output to  standard out) which can determine if parentheses are properly balanced. I.e. a program which could check whether a JAVA/C program is properly balanced. You need to check the following types of parentheses: {},[],()

### 2.  Elementary Sorts
:file_folder: [View lab work](/elementary-sorting-algorithms)<br /><br />
Tasks solved for this lab:

1. Implement one of the algorithms in chapter 2.1. Augment the sorting process so that all the content of the array that is being sorted is printed after each inner loop iteration. Write a unit test in main() which allows the user to define the size of the input (N) and then input (N) integers from the command line which is to be sorted.
2. Augment the test code so that the array is sorted in descending order instead of ascending order (you may add O(N) operations)
    Clarification: You should not change the sorting method, nor should you sort the array an extra time.
3. Augment the above implementation so that it prints the number of swaps performed when sorting the array.
4. Add a method which counts the number of inversions in the input array and prints a list of all inversions on the format [i,a[i]], [j, a[j]] where i and j are indices and a[i], a[j] are the values of the elements. Call the method from main() before the array is sorted.
5. Implement a function in C which takes an array of integers (both positive and negative) and orders the elements in the array so that all negative elements come before the positive. You are not allowed to sort the array - only collect all negative values first. The algorithm should only use O(1) extra memory (i.e. be in-place Wikipedia: In-place algorithm (Länkar till en externa sida.)Länkar till en externa sida.)
6. Design a loop invariant which explains how the method above works (add it as part of the comments in the .c file).
7. Implement a linked list which should hold elements of type int. The elements should be stored in ascending order in the list (order the elements when they are inserted). The elements of the list should be printed after each enqueue operation in your unit test.


### 3. Symbol Tables and Their Applications
:file_folder: [View lab work](/symbol-tables-and-trees)<br /><br />
Tasks solved for this lab:

1. Write a simple filter to clean the text, i.e. to remove all characters that are not alphabetic, blank or newline - replacing every other character by a blank to keep the number of characters constant to the original text. Hint: this is easy to do in C using the "isalpha()" function (to find out more about it on a unix/linux system type: man isalpha)
2. Use the first N (N in the order of hundred words) words from the text to compare the running times of the ordered array ST (algorithm 3.2) to the Binary Search Tree algorithm (Algorithm 3.3) (you need only implement the basic operations to put and get keys to/from the ST) Use the FrequencyCounter from page 372 as test program (you may need to change how you read the words if you do not use the libraries from Sedgewick&Wayne)..
3. Extend one of your ST implementations to allow the user to query the system on which are the nth to the n+xth most frequent words in the full text.
4. (can be hard) Implement a Red-Black tree and compare the time it takes to execute the Frequency test on the full text to that of the Binary Search Tree. Make sure to remove the time it takes to read the text from the measurements (i..e. to only compare the execution times of the algorithms)
5. Write a program that shows how evenly the built-in hash function for strings in Java distributes the hashes for the words found in the text.
6. Write an "index"-program which allows the user to ask the question "on which positions in the text (i.e. the number of characters from the beginning) you find the word X". The program should list the position of all occurrences of X as answer to the query. In this assignment you may use the Java library (built-in) lists.
7. Compare the performance of Hashing with separate chaining (Algorithm 3.5) to Hashing with linear probing (algorithm 3.6). Again using the same test program as in the previous assignments.


### 4. Graphs (Undirected & Directed)
:file_folder: [View project directory](/graphs)<br /><br />
Tasks solved for lab 4A - Undirected Graphs:
Write a program based on DFS which can answer questions of the type: "Find the a path from X to Y" Which should result in a list of vertices traversed from X to Y if there is a path.
Change the program to use BFS.
Assign unique weights to the edges (1,2,3...E) and write a program that can find the shortest path between X and Y and print the vertices traversed and the associated sum of the weights of the path.
Write a program which computes a minimum spanning tree for the largest connected component in the weighted graph from 3

Tasks solved for lab 4B - Directed Graphs:
1. Write a program that can answer if there is a path between any to vertices.
2. Write a program that can find directed cycles in the graph (if there are no such cycles you should add non-trivial edges to the graph to test your program)
3. Write a program that can find and print a topological sort of the following small data base:
4. TA-TB, TA-TF, TC-TA, TD-TF, TF-TE, TG-TE, TH-TG, TI-TH, TJ-TK, TJ-TL, TJ-TM, TL-TM
