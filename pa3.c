/* Unit converter
Converts units and returns them
Author: Diyaa Nassar- nassard@bc.edu
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define CM_TO_IN 2.54
#define MM_PER_CM 10
#define CM_PER_M 100
#define CM_PER_KM 100000
#define IN_PER_FT 12
#define IN_PER_YD 36
#define IN_PER_MI 63360
#define UNIT_LENGTH 8
#define MAX_UNIT_LENGTH 256

typedef enum Unit {MM, CM, M, KM,IN, FT, YD, MI, UNSUPPORTED} Unit;
typedef enum Direction {INPUT, OUTPUT} Direction;
char *unit_strings[UNIT_LENGTH] = {"MM", "CM", "M", "KM", "IN", "FT", "YD", "MI"}; 

Unit unit_string_to_unit(const char * const unit_string) {
    for (int i = 0; i< UNIT_LENGTH; i++){
    if (!strcmp(unit_string, unit_strings[i])) {
        int new_enum_number = i;
        return new_enum_number;
        }       
    }   
    return UNSUPPORTED;
}

int is_metric(const Unit unit) {
    if (unit < 4){
        return 1;
    }
    else {
        return 0;
  }
 }

double convert_metric(const double value, const Unit unit, const Direction direction) {
    switch(unit) {
        case 0:
            return direction == OUTPUT ? value * MM_PER_CM : value / MM_PER_CM;
        case 1: {
            return value;
        }
        case 2: {
            return  direction == OUTPUT ? value / CM_PER_M : value * CM_PER_M;
        }
        case 3: {
            return direction == OUTPUT ? value / CM_PER_KM : value * CM_PER_KM;
        }
        default : {
            return EXIT_FAILURE;
                }

        }
}

double convert_us(const double value, const Unit unit, const Direction direction) {
    switch(unit) {
        case 4:
            return value;
        case 5: {
            return direction == OUTPUT ? value * IN_PER_FT : value * IN_PER_FT;
        }
        case 6: {
            return direction == OUTPUT ? value / IN_PER_YD : value * IN_PER_YD;
        }
        case 7: {
            return direction == OUTPUT ? value / IN_PER_MI : value * IN_PER_MI;
        }
        default : {
            return 1;
        }
    }       
}

double convert(const double value, const char * const input_unit_string, const char * const output_unit_string) {
    if (unit_string_to_unit(input_unit_string) == UNSUPPORTED || unit_string_to_unit(output_unit_string)== UNSUPPORTED) {
        return NAN;
    }
    else {
        int new_enum_number = unit_string_to_unit(input_unit_string);
        int output_unit = unit_string_to_unit(output_unit_string);
        if(is_metric(new_enum_number)) {
            float cms = (convert_metric(value, new_enum_number, INPUT));   
            if (is_metric(output_unit)) {
                return convert_metric(cms, output_unit, OUTPUT);
            } 
            else {
                float ins = cms / CM_TO_IN;
                return(convert_us(ins, output_unit, OUTPUT ));
                }
        }
                else {
                    float ins = (convert_us(value, new_enum_number, INPUT));
                    if(!is_metric(output_unit)){
                        return convert_us(ins, output_unit, OUTPUT);
                    }
                    else{
                        double cms = ins * CM_TO_IN;
                        return(convert_metric(cms, output_unit, OUTPUT));
                       }
                }
        }
        return 0;
}

int main() {
    double value; 
    char input_unit[MAX_UNIT_LENGTH];
    char output_unit[MAX_UNIT_LENGTH];
    printf ("Please enter a length to convert, in the form <number> <input unit> <output unit> \n Enter any letter to quit\n > ");  
    while ((scanf("%lf %255s %255s", &value, input_unit, output_unit))) {
        double result;
        result = convert(value, input_unit, output_unit);
        if (! isnan(result)) {
            printf("%lf %s = %f %s\n", value, input_unit, result, output_unit);
        }
        else { 
            printf("Allowable units: MM, CM, M, KM, IN, FT, YD, MI");
        }
    }
    return 0;
}

