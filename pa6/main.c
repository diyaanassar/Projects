#include "fp_analyzer.c"

int main() {
    values var;
    printf("\nPlease enter a floating point value: \n > ");
    while(scanf("%g", &(var.f))) {
        printf("%f\nAll bits: ", var.f);
        print_bits(var);
        print_fields(var);
        print_normalized(var);
        printf(">");
    }
}