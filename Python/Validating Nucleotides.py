#Diyaa Nassar
#Asking a user for a size and length of nucleotides then validating them and printing them in a file

# importing random
import random
import string

#function to validate gc content
def validateGC_Content(x):
    #formula to find the gc content
    formula = ((x.count('G') + x.count('C')) / (x.count('A') + x.count('T') + x.count('G') + x.count('C'))) * 100
    #if the gc content is between 40 and 60 then return true
    if (40 <= formula <= 60):

        return True
   #if not return false
    else:
        return False
#function to find the rendundant barcodes
def isRedundantBarcode(x):
    # looping through the letters to see if there are any triple repeating letters
    for i in range(2, len(x)):
        if x[i - 2] == x[i - 1] and x[i - 1] == x[i]:
            return True
    return False
# main function
def main():
    # setting variables
    counter = 0
    # asking the user for the size and length of dna
    size = int(input("Size of DNA:"))
    barcodeCOUNT = int(input("How many: "))
    nucleotides = ['A', 'T', 'C', 'G']
    # the list of restricted phrases
    restrictionList = ['ACCGGT', 'GGCGCGCC', 'GGATCC', 'CCTGCAGG']
    # opening a new file
    file = open("nucleotidehw.txt", "w")
    # while the counter is less than the barcodecount
    while counter < barcodeCOUNT:
        element = (''.join(random.choice(nucleotides) for x in range(size)))
       #running the nucleotides through all of the other functions to validate it.
        if (element not in restrictionList):
            if (validateGC_Content(element)):
                if not isRedundantBarcode(element):
                    #if it passes through all the tests, then print it in the homework file
                    new = (str(("Barcode ", counter + 1, ":", element)))
                    file.write(new)
                    file.write("\n")
                    counter += 1
    #close the file
    file.close()
#calling the main function
main()
