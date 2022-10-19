/* Matrix Multiplier
Transposes and multiplies matricies and returns the elapsed time
Author: Diyaa Nassar- nassard@bc.edu
*/
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

#define MIN_DIM_POWER 3
#define MAX_DIM_POWER 10
#define MAX_VALUE 20
#define FALSE 0
#define TRUE 1

void multiply(const int dim, const int * const a, const int * const b, int * const c) {
    int sum = 0;
    for (int i = 0; i < dim; i++) {
        for(int j = 0; j < dim; j++) {
            for(int k = 0; k < dim; k++) {
                sum += a[i * dim + k] * b[k * dim + j];
            }
        c[i * dim + j] = sum;    
        sum = 0;
        } 

    }
}

void multiply_transpose(const int dim, const int * const a, const int * const b_t, int * const c) {
    int sum = 0;
    for (int i = 0; i < dim; i++) {
        for (int j = 0; j < dim; j++) {
            for(int k = 0; k < dim; k++) {
                sum += a[i * dim + k] * b_t[j * dim + k]; 
            }
            c[i * dim + j]= sum;
        sum = 0;
        }
    }
    
}

void transpose(const int dim, int * m) {
    int tmp;
    for (int i = 0; i < dim; i++) {
        for(int j = 0; j < i; j++) {
            tmp = m[i * dim + j];
            m[i * dim + j] = m[j * dim + i];
            m[j * dim + i] = tmp;
        }
    }
}

void print(const int dim, const int * const m) {
    for (int i = 0; i < dim; i++) {
        for (int j = 0; j < dim; j++) {
            printf("%d\t", m[i * dim +j]);
        }
    printf("\n");
    }  
}   

void init(const int dim, int * const m) {
    for (int i = 0; i < dim; i++) {
        for(int j = 0; j < dim; j++) {
            m[i * dim + j] = rand() % (MAX_VALUE + 1);
        }
    }
}

struct timeval run_and_time(
    void (* mult_func)(const int, const int * const, const int * const, int * const),
    const int dim, const int * const a, const int * const b, int * const c) {
    struct timeval start, end, elapsed;
    gettimeofday(&start, NULL);
    mult_func (dim, a, b,c);
    gettimeofday(&end, NULL);
    elapsed.tv_sec = end.tv_sec - start.tv_sec;
    elapsed.tv_usec = end.tv_usec - start.tv_usec;
    if (elapsed.tv_usec < 0) {
        elapsed.tv_sec--;
        elapsed.tv_usec += 1000000;
    }
    return elapsed;
    }

int verify(const int dim, const int * const c1, const int * const c2) {
    for(int i = 0; i < dim; i++) {
        for(int j = 0; j < dim; j++) {
            if(c1[i * dim +j] != c2[i * dim + j]) {
                return FALSE;
            }
            else {
                return TRUE;
            }
         }
      }
    return 0;
}

void run_and_test(const int dim) {
    int * arra = malloc(sizeof(int) * dim * dim);
    int * arrb = malloc(sizeof(int) * dim * dim);
    int * c1 = malloc(sizeof(int) * dim * dim);
    int * c2 = malloc(sizeof(int) * dim * dim);
    init (dim, arra);
    init (dim, arrb);
    struct timeval reg_matrix = run_and_time(&multiply, dim, arra, arrb, c1);
    transpose (dim, arrb);
    struct timeval t_matrix = run_and_time(&multiply_transpose, dim, arra, arrb, c2);
    if(verify (dim, c1, c2)) {
        printf("Results agree.\n");
        printf("Standard Multiplication: %ld seconds, %d microseconds\n",(long) reg_matrix.tv_sec, (int) reg_matrix.tv_usec);
        printf("Multiplication with transpose: %ld seconds, %d microseconds\n",(long) t_matrix.tv_sec, (int) t_matrix.tv_usec);
    }
    else {
        printf("Results do not agree.\n");
    }
    
    free(arra);
    free(arrb);
    free(c1);
    free(c2);
}

int main() {
    for (int power = MIN_DIM_POWER; power <= MAX_DIM_POWER; power++) {
        run_and_test(1 << power);
    }
    return EXIT_SUCCESS;
}

