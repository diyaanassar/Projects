/* Length Unit Converter
Converts centimeters to incbes to centimeters
Author: Diyaa Nassar - Nassard@bc.edu
*/

#include <stdio.h>
#include <stdlib.h>

void Cm_to_In(int temp) {
    float conversion = 2.54;
    double now_inch = temp / conversion;
    printf("%d cm = %f in\n", temp,  now_inch);
}

void In_to_cm(int temp) {
    float conversion = 2.54;
    double now_centi = temp * conversion;
    printf("%d in = %f cm\n", temp,  now_centi);
  
}

void Convert(int temp, char unit[10]) {
    if (unit[0] == 'c' && unit[1] == 'm') {
        Cm_to_in(temp);
    }
    else if (unit[0] == 'i' && unit[1] == 'n') {
        In_to_cm(temp);
    }
    else {
        printf("%s \n", "The unit must be in cm or in");
    }
}


int main(int argc, char **argv) {
    if (argc < 3) {
        printf("Must have number and unit btuawerf\n");
    }
    else {
        char * temp = argv[1];
        int temp1;
        temp1 = atoi(temp);
        Convert(temp1,argv[2]);
     
    }
 return 0;
}
