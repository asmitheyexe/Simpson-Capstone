# Simpson-Capstone
Senior year project:
  This project is eventually going to be a GUI that allows the user to generate Invoices, send Invoices to a connected printer, and store client information in a database server running on AWS.

The File that is used to get a connection to the database is not included. I will include it in future releases when I figure out a better way to store private information such as passwords to the databse in a more secure way. Currently the password and username to the AWS RDS databse is stored as a String in the DBConnection class. This is not a great way to handle this right now and will be changed.

2/14/19 - whats working
- There is a GUI that appears on startup that grabs the clients out of the AWS databse and displays their information in a table. 

2/26/19 -whats working
- User can now Get, Edit, Delete clients from the Database. The user's input is not being validated yet. Will implement soon

3/1/19 - AGENDA
- Finish Database validation, rebuild the database, Enforce Regular Expressions on user input, DOCUMENT MY CODE

3/28/19 - UPDATE
- Tried making the code less hard coded. The program runs slower due to constant calls to the database to retrieve column names. Need to figure out a way to store Column names in memory on startup so i dont need to keep calling the Database everytime I make a SQL statement for just column names. DOCUMENT MY CODE.

3/29/19 - UPDATE
- Decided to just keep column names in a Class all on their own with getters and just update the column names manually for now so I don't have to sacrifices program speed and make unecessary calls to the database.
- Removed excess space
- Changed functionality of how some classes make Scenes. Now there is a Generic class for making Scenes that will return a GridPane
- Added Error alert boxes when user enters wrong information into the text fields while inserting a new client into the database
- Started creating the invoice GUI. loads and pops up but nothing has functionality yet.
- Going to make a invoice template

# Sources
- If I use exact code examples I give a link to the post in the comments in the code.
- https://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm
- https://stackoverflow.com/
- https://www.geeksforgeeks.org/
