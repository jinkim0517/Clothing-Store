# Clothing Store Inventory Management System

### A database system for a clothing store

For my project, I am planning on creating an inventory management system for a clothing store. This system will have 
two main non-trivial variables:
- a class called **Clothing**,
- and a class that is a list of **Clothing**.

This target users for this system would be clothing store owners who would want to digitally manage their 
inventory for convenience and to better their business. This system would hold information about different pieces of 
clothing that someone like a manager could use to gain a better understanding of their business.

Knowing this, some fields that the class Clothing
has would be:
- *Name*
  - A string that holds the name of a specific clothing piece.
- *Type*
  - A string that holds the type of clothing that this clothing instance is.
    - A top, bottom, outerwear, or footwear.
- *Price*
  - A double that holds the price of this clothing instance.
- *Availability*
  - A boolean that relates to whether or not an item is in stock.
- *Sales*
  - The number of times that this clothing instance has been sold.

A list of **Clothing** represents the entire store inventory, as it is a collection of all the clothes that the store
owns.

Some features that a user would want to do with this system are: 
- A user of this system would want to create a custom Clothing instance and add it to their inventory by inputting data
into an interface.

- A user of this system would want to remove an item from their store inventory.

- A user of this system would want to change any field of a clothing item.

- A user of this system would want to view the items in their store inventory, either through specific filtering
or sorting.