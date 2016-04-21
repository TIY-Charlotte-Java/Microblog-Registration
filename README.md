# Microblog-Registration
We're getting more advanced with our microblog now

![vega]



We're going to add more user data!

## Description

Right now, we're storing our user objects in a HashMap, in code. We're getting more advanced, so we need to store more User data for our Microblog. We don't want to store User data inside our `Messages` table, so we're going to create another table.

## Requirements
* Alter your code such that you ensure that a `Users` table exists. This table will store all of our User data.
* The `Users` table should contain:
  * ID
  * User Name
  * First Name
  * Last Name
* Create a registration page at `/register` with fields to support user (user name, first name, last name)
  * Add a link to `/register` to the login page
  * If a user exists at a given username, show an error back to the user.
  
* Replace the `UserName` field in the `Messages` table with a `UserId` column, and make sure it contains a Foreign Key constraint to the `Users` table.
  * This will ensure that you are incapable of adding a message to `Messages` without specifying a valid user.
  
* For the `/messages` endpoint, if a user session is not valid, redirect to `login`.
* 

[vega]: http://i.imgur.com/RBWxUYd.png
