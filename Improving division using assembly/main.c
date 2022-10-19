/* Hand-Improved Integer Division
Improving division with assembly
Author: Diyaa Nassar- nassard@bc.edu
*/

#include <stdio.h>
#include <stdlib.h>

int div_qr(int dividend, int divisor, int *remainder);

int main() {
    printf("\nPlease enter an integer dividend and divisor or 'q' to quit \n > ");
    int dividend, divisor, remainder, quotient;
    while(scanf("%d%d", &dividend, &divisor)) {
        if(divisor != 0) {
            quotient =  div_qr(dividend, divisor, &remainder);
        printf("%d / %d = %d, r %d\n> ", dividend, divisor, quotient, remainder);
        }
        else {
            printf("Division by 0 is undefined\n > ");
        }
    }
    return EXIT_SUCCESS;
}
