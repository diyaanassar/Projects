#define BITE_SIZE 32
#define max_mantissa 23
#define max_exponent 8
#define max_sign 1

void Binary(int i, int j);

typedef union {
    float f;
    unsigned int u;
    struct {
        unsigned int mantissa : max_mantissa;
        unsigned int exponent : max_exponent;
        unsigned int sign : max_sign;
    } num;
} values;

void print_bits(values var);

void print_fields(values var);

void print_normalized(values var);

