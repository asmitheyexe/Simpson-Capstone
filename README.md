# Simpson-Capstone
Senior year project:
  This project is eventually going to be a GUI that allows the user to generate Invoices, send Invoices to a connected printer, and store client information in a database server running on AWS.

The File that is used to get a connection to the database is not included. I will include it in future releases when I figure out a better way to store private information such as passwords to the databse in a more secure way. Currently the password and username to the AWS RDS databse is stored as a String in the DBConnection class. This is not a great way to handle this right now and will be changed.

2/14/19 - whats working
-There is a GUI that appears on startup that grabs the clients out of the AWS databse and displays their information in a table. 

2/26/19 -whats working
-User can now Get, Edit, Delete clients from the Database. The user's input is not being validated yet. Will implement soon

3/1/19 - AGENDA
-Finish Database validation, rebuild the database, Enforce Regular Expressions on user input, DOCUMENT MY CODE
