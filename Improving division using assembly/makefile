C = gcc
D = -D
FLAGS = -Wall -Werror -std=c99
APP  = div1


main: main.c 
	${C} ${FLAGS} $^ -o $@


div1: div1.s main.c
	${C} ${FLAGS} $^ -o $@

div2: div2.s main.c 
	${C} ${FLAGS} $^ -o $@

zip: div1.c main.c
	zip paX.zip $^

clean:
	rm -f div main
	rm -f paX.zip

