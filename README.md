# DayApp Planner Interface 

## Introduction 


With everyone at full swing with their busy schedules throughout the school year, it has become more difficult
than ever to keep track of not only tasks, but to also find time to yourself. 
With the help of this Day Interface, not only will it display tasks as an easy reminder, but this application will 
also perform the following:

- Display a Menu **interface** with ability to choose options from submenus (Tasks, Clothing, Outfits)
- Personalized **to-do lists menu** 
  - Ability to add, remove, print, and list tasks
- Planned **meals** (instead of figuring out on the spot what and when to eat)
  - Add or Remove Meals
- Display **outfit/clothing** for the day
  - Add or Remove Outfits
- Reset Day (Clear all items for the day in preparation for the next day)

Anyone who faces difficulty with time management will find this interface super useful and likely see a noticeably 
improvement 
to their lifestyle. In fact, this will help save large amounts of time for the next day, having planned it earlier. 
This project is of interest to me not only because of its practicality, but also the fact that it provides a method of
automation, an integral
part of software construction that I am passionate about.

## User Stories
To-Do Tasks

- As a user, I want to be able to create a task
- As a user, I want to be able to add a task to a list of tasks each day
- As a user, I want to delete tasks that are completed
- As a user, I want to be able to list all the names of the tasks availble, so that I can cross
  it off my checklist


Meals

- As a user, I want to be able to add task planned for each meal of the day (Breakfast, Lunch, Dinner, Snack), along with a description of the location
- As a user, I want to be able to remove the meal of my choice, simply by typing the name of the meal I don't like
- As a user, I want to create a list of all the foods available for the week

Clothing

- As a user, I want to be able to add an outfit(s) (containing fields of Top, Bottom, Footwear, and Accessories worn) 
 planned for the day
- As a user, I want to be able to remove an outfit, in case I have changed my mind or don't like it
- As a user, I want to create a list of all the clothing available for the week

Data Persistence

- As a user, I want to be able to save each Day Item (Clothes, Food, TaskList) for the day
- As a user, I want to be able to load each Day Item (Clothes, Food, TaskList) whenever needed

GUI
- As a user, I want to be able to save each Day Item on a Graphical User Interface
- As a user, I want to be able to load and Display each Day Item on a Graphical User Interface
- As a user, I want to be able to enter each the fields of each task item in a text entry field to appear in the GUI 
- As a user, I want to be able to enter each the fields of each food item in a text entry field to appear in the GUI
- As a user, I want to be able to enter each the fields of each clothing item in a text entry field to appear in the GUI
- As a user, I want to be able to display a real-time clock that shows each day of the week

Example of an EventLog Occurring:
Thu Nov 25 09:46:29 PST 2021
Added Webwork To TaskList!
Thu Nov 25 09:46:45 PST 2021
Added Outfit for School To Clothes!
Thu Nov 25 09:46:54 PST 2021
Added Lunch To Meals!
Thu Nov 25 09:46:57 PST 2021
Tasks Cleared!
Thu Nov 25 09:46:57 PST 2021
Meals Cleared!
Thu Nov 25 09:46:57 PST 2021
Clothes Cleared!

Phase 4 Task 3:
A lot of refactoring could have been done with regards to the GUI - TaskWindow, ClothingWindow, and MealsMenu all 
contain the same methods that make up the JFrame: if I had more time, just like with Item and ItemList,
an abstract class could be created to clean up more duplicate code - this will in fact save a lot of headaches in case 
I run into a problem, because I would have to not only make one change, but three changes across those three classes.
As well, some of the method behaviour in my classes does not accurately reflect the description of the class itself.
For example, several JPanel's and JTextField's were created in each of the three GUI windows for implementing text
purposes for the user, and these JFrame components could be refactored into it's own class. Lastly, since there
was limited time in completing the phases, I would also try to refactor the method names and variables to be more 
specific and attune to the purchase of the behaviour.
