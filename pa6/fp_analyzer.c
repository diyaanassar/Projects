/*Unpacking Floating-point numbes
visualizes the internal representations of floating point numbers
Author: Diyaa Nassar- nassard@bc.edu
*/

#include <stdio.h>

#include "fp_analyzer.h"

void binary(int i, int j) {
    for(int x = j - 1; x >= 0; x--) {
        if((i >> x) & 1) {
            printf("1");
        }
         else {
             printf("0");
        }
     }
}

void print_bits(values var) {
    printf("%d", var.num.sign);
    binary(var.num.exponent, max_exponent);
    binary(var.num.mantissa, max_mantissa);
    printf("\n");
}

void print_fields(values var) {
    printf("%s %d\n", "Sign:", var.num.sign);
    printf("Exponent: ");
    binary(var.num.exponent, max_exponent);
    printf("\nMantissa: ");
    binary(var.num.mantissa, max_mantissa);
  
}

void print_normalized(values var) {
    float count; 
    float multiplier = 2;
    int num_sign = var.num.sign;
    int num_exponent = var.num.exponent;
    float num_mantissa = var.num.mantissa;
    while (count < max_mantissa  - 1) {
        multiplier = multiplier * 2;
        count++;
    }
    num_mantissa = num_mantissa / multiplier;
    printf("\nOriginal Value: (-1)^{%d} * (1 + %f) * 2^{%d -  127} = ", num_sign, num_mantissa, num_exponent);
    printf("%.45f\n", var.f);
}
