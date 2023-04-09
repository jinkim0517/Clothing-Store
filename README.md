# Clothing Store Inventory Management System

### A database system for a clothing store

For my project, I am planning on creating an inventory management system for a clothing store. This system will have 
two main non-trivial variables:
- a class called **Clothing**,
- and a class that is a list of **Clothing** called **Inventory**.

The target users for this system would be clothing store owners who would want to digitally manage their 
inventory for convenience and to better their business. This system would hold information about different pieces of 
clothing that someone like a manager could use to gain a better understanding of their business. This topic interests me
because clothes are an interest to me, and also, understanding how store inventory is managed and the types of functions
that can be useful would be a valuable skill if I were to create programs for companies in the business industry.

Some features that a user would want to do with this system are: 
- A user of this system would want to create one or multiple custom Clothing instances and add them to the inventory by inputting data
into an interface.

- A user of this system would want to remove as many items as they want from their store inventory (one at a time).

- A user of this system would want to change any field of a clothing item.

- A user of this system would want to view all the items in their store inventory.

- A user of this system would want to be able to save their clothing inventory to a file if they choose so.

- A user of this system would want to be able to load their previously saved clothing inventory from a file if they choose so.

### Instructions for grader
- To add a custom piece of clothing, press the button labeled "Add Clothing".
  - Then, input all the information for the clothing piece, and then press the button labeled "Submit" once done.
    - A corresponding pop-up window will appear
- To remove a piece of clothing, press the button labeled "Remove Clothing".
  - Then, input all the information for the clothing piece that you would like to remove, and then press the button labeled "Submit" once done.
    - A corresponding pop-up window will appear
- To view the inventory (all the clothes), press the button labeled "View Clothing".
  - All the clothing will be shown, and you can reverse the order by pressing the button labeled "Reverse Order".
  - You can toggle through different types of clothing by pressing the button labeled "Current Type: \_\_\_", where "___" corresponds to the current type being shown.
  - Press the button labeled "Main Menu" to return to the main screen.
- To load a saved inventory, simply press the button labeled "Load Saved Inventory".
  - A pop-up screen will appear with a success or fail message.
- To save the current inventory, simply press the button labeled "Save Inventory" on the main menu.
  - A pop-up screen will appear with a success or fail message.
- To quit from the application, simply press the button labeled "Quit" on the main menu.
- The visual component can be found on the main screen, where one can see an extremely well-dressed monkey can be seen.
  - The image can be found in the images folder.

Image Credit: "$wag Gorilla", by Eunae Kim, March 29th 2023. 


### Phase 4: Task 3
For refactoring this project, there are many changes I would make (and will make after the semester is over) to the overall design of this program.
Firstly, I would definitely utilize the Singleton pattern to account for my single Inventory object being passed around as a parameter to each Screen subclass. 
This would help to simplify my design, since all the association arrows would be removed from all the Screen subtypes.
Also, I would add more functions to the Screen class so that there is less code duplication within its subclasses. 
One major area that I would like to refactor is the ViewScreen class, as the usage of methods like enumTypesToString() and convertInventory() aren't very elegant. The class has both a class-level
parameter called currentType, yet the methods still take an unnecessary parameter to indicate the current type. This would reduce confusion for both my future self and also any other viewers of this program.
Finally, I believe that a cleaner and simpler usage of all the JList, JFrame, and JButton within my program could be made if I learn more about the library. For instance,
instead of closing the window and reopening a new one to go to a new menu, perhaps I could change it to simply refresh the current window.