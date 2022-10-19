##Nassar_Diyaa
##Developing a functios that can loop through a sentance and take out hashtagged words and put them into a list
def labeled():
    # variables to set up the repeated prompt of asking for messages
    user_message = ""
    z = ""
    while True:
        # if the user types anything but q, they will continue to be asked to enter a message
        if z != 'q':
            z = input("Enter a message or type q to end\n ")
            user_message = user_message + " " + z
        # if the user types q, the function will begin to evaluate the messages
        else:
            # split the message into two parts, before and after the #
            x = user_message.split("#")
            list1 = []
            # a loop that runs through the split sentence
            for i in range(len(x)):
                if i > 0:
                    # token will store the characters starting from after the hashtag untill the next space
                    token = (x[i].split(" "))[0]
                    # take that token and add it to the list1 (an empty list)
                    list1.append(token)
            # print the list of all the hashtaged words
            print(list1)
            break


# call the function
labeled()


def tabulated():
    # variables to set up the repeated prompt of asking for messages
    user_message = ""
    z = ""
    while True:
        #  if the user types anything but q, they will continue to be asked to enter a message
        if z != 'q':
            z = input("Enter a message or type q to end: ")
            user_message = user_message + " " + z
        # if the user types q, the function will begin to evaluate the messages
        else:
            # splits the message into sections, depending on where the # is
            x = user_message.split("#")
            list1 = []
            # loop through the split message
            for i in range(len(x)):
                if i > 0:
                    # token will store the characters starting from after the hashtag untill the next space
                    token = (x[i].split(" "))[0]
                    # take that token and add it to the list1 (an empty list)
                    list1.append(token)
            # an empty list to find the number of unique words in the list
            unique_list = []
            nest = []
            # Loop through the hashtagged words to see if any are repeated
            for x in list1:
                # if a word is found more than once, it wont add it to the unique list
                if x not in unique_list:
                    unique_list.append(x)
            # Loop through the unique list
            for x in unique_list:
                count = 0
                # loop through the original list of hashtagged words
                for y in list1:
                    # if a words from the unique list is found more than once in the original list, the counter goes up
                    if x == y:
                        count += 1
                # the final list
                final = []
                # add the hashtagged word followed by how many times it is stated
                final.append((x + " : " + str(count)))
                nest.append(final)
            # pring the nested list
            print(nest)
            break
# call the function
tabulated()
