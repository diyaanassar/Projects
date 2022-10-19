/* Printing ACII Codes
Prints the ACII and Binary codes of strings
Author: Diyaa Nassar- nassard@bc.edu
*/

#include <stdio.h>
#include <stdlib.h>

#define BITE_SIZE 8
#define MAX_INPUT_LENGTH 256

int get_input(int buffer_length, char *input) {
    printf("\n> ");
    while(fgets(input,  MAX_INPUT_LENGTH, stdin)) {
        if(input[0] == '\n') { 
        exit(1);
    }
        if((input == NULL || input+1 == NULL)) {
            return 1;
        }
        else {
            return 0;
        }
    }
    return 0;
}

void dec_to_bin_str(char input, char * output) {
    int ascii = input;
    for(int i = BITE_SIZE; i > 0; --i) {
        output[i] = (ascii & 1) + '0';
        ascii >>= 1;
    }
    for (int i = 0; i <= BITE_SIZE; i++) {
        printf("%c", output[i]);
    }
}

void print_ascii(char * input) {
    int i = 0;
    printf("ASCII Codes: \n");
    do {
        char new = input[i];
        printf("%d ", new);
        i++;
    }
    while (input[i] != '\0');
        printf("\n");
}
 
int main() {
    printf("\nEnter string of no more than 255 characters including spaces\nHit enter to quit");
    char input[255];
    while(!(get_input(MAX_INPUT_LENGTH, input))) {
        print_ascii(input);
        int i = 0;
        char output[BITE_SIZE + 1];
        do {
            dec_to_bin_str(input[i], output);
            printf(" ");
            i++;
        }
        while (input[i] != '\0');
    }
    return 0;

}