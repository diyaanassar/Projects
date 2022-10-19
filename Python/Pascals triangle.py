##Diyaa Nassar
##Recursivley creating pascals triangle
# creating the pascal triangle function
def pascal(x):
    line = [1]
    # when x becomes 0
    if x == 0:
      # join the values of the list line, and print it
      print(" ".join(str(s) for s in line))
      # exit the function
      exit
    else:
        # recursion function that will create pascals triangle
        previousLine = pascal(x - 1)
        # for every value in the length of the previous line
        for i in range(len(previousLine) - 1):
            # append the value to the previous line
            line.append(previousLine[i] + previousLine[i + 1])
        line += [1]
        print(" ".join(str(s) for s in line))
    return line
# validation for the inputs
number_one = False
while not number_one:
    # While the input is not false
    try:
        # keep asking for an input
        x= int(input("Welcome to Pascal's triagnle generator. \nPlease enter the number of levels to genreate : " ))
        if x >= 1:
            # if the input entered is greater or equal to one then the condition is true
            number_one = True
        else:
    #           # If its less then one, print the statement and ask for another input
          print("Only numbers greater or equal to 1 are accepted as valid input.")
    except:
        # if the input is not greater or equal to one, then print the statement and ask for another input
        print("Your number must be positive (greater than zero).")
# call the function
(pascal(x))
