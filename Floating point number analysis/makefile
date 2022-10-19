


C = gcc
D = -D
FLAGS = -Wall -Werror -std=c99
DoubleFlag = -D DOUBLE

all: main fp_analyzer

main: main.c
	${CC} ${FLAGS} $^ -o $@



fp_analyzer: fp_analyzer.c
	${CC} ${FLAGS} $^ -o $@

zip: fp_analyzer.c main.c
	zip paX.zip $^

clean:
	rm -f fp_analyzer main
	rm -f paX.zip

