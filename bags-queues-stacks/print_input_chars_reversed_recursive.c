/***********************************************************
*	Compilation: gcc -o <name>	print_input_chars_reversed_recursive.c
*	Execution: <name>
*	Dependencies: n/a
*	
*	A program that reverses the chars entered by the user
*	in the standard input using a recursive function
*
*	Author: August Ronne, aronne@kth.se
*	
***********************************************************/

#include <stdio.h>

// Defining our reversing function
void reverseChars();

int main() {
	reverseString();
}

// The function for reversing the chars from standard input
void reverseChars() {

	char c = getchar();
	if (c != ('\n')) {
		reverseChars();
		putchar(c);
	}
}