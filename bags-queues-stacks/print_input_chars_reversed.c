/***********************************************************
 *	Compilation: gcc -o <name>	print_input_chars_reversed.c
 *	Execution: <name>
 *	Dependencies: n/a
 *	
 *	A program that reverses the chars entered by the user by
 *	storing the chars in an array of pre-determined size, and
 * 	then printing the chars in reverse.
 *
 *	Author: August Ronne, aronne@kth.se
 *	
 ***********************************************************/

#include <stdio.h>

int n = 20;				// size of char array, pre-determined by program

void reverseChars();	// declaring the reverseChar function

/**
 *	main function calls and tests the reverseChar function
 */
int main() {
    reverseChars();
    return 0;
}

/**
 * reads char input from user until it registers a
 * newline character, then it prints the reversed chars
 */
void reverseChars(){
    char chars[n];
    char c;
    int i = 0;

    while(c != '\n'){
        c = getchar();
        chars[i] = c;
        i++;
    }

    while(i > 0){
        putchar(chars[--i]);
    }
}