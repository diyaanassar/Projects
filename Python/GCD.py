##Diyaa Nassar
##Creating a function to recursivley find the GCD of two numbers
# creating the gcd function
def gcd(x, y):
    # When the second number(y) becomes 0, return the first number(x)
    if (y == 0):
        return x
    else:
        # The function is called with the arguments as the secind number and the remainder of the division of the first and second number
        return gcd(y, x % y)
    
# Validation of the inputs
number_one = False
# While number one (x) is not false
while not number_one:
    try:
        # Asking for an input
        x= int(input("Please enter the first integer: "))
        if x >= 1:
            # If the input is greater or equal to one, then x is true
            number_one = True
        else:
          # If its less then one, print the statement and ask for another input
          print("Only numbers greater or equal to 1 are accepted as valid input.")
    except:
        # If the input is a letter or symbol (causing an error) then print this statement and ask for another inout
        print("Only numbers greater or equal to 1 are accepted as valid input.")

# Same validation as the x variable but for the y variable
number_two = False
while not number_two:
    try:
        y=int(input("Please enter the second integer: "))
        if y >= 1:
            number_two = True
        else:
            print("Only numbers greater or equal to 1 are accepted as valid input.")
    except:
        print("Only numbers greater or equal to 1 are accepted as valid input.")
# print the gcd of x and y and call the function
print("The gcd of",x , "and", y," is: ", gcd(x,y))
