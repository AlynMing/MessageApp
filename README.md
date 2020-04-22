Unit 8: Group Milestone - README
===

# TEAMUP

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Teamup is a messaging app where users can send friends private messages from their device. Users can chat with users and share images. It’s a simple, quick and easy way to keep in touch with co-workers, friends and family.

### App Evaluation
- **Category:**
     *Messaging App
- **Mobile:** This app is being developed for mobile use.
- **Story:** Allows user to register, login and create messages with friends on the app
- **Market:** The app is for any individual looking to teamup and chat with friends or coworkers
- **Habit:** This is a social app and can be used at the users’ discretion.
- **Scope:** We allow users to register and add their friends to join for conversation and file sharing.
 
## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can register and log into an account - completed
* User can add new friends - completed
* User can view all friends- completed
* User can create message
* User can add friends - completed
* Add profile picture
* User can view all messages


**Optional Nice-to-have Stories**

* User can search messages
* User can change settings
* Add location
* Take pictures


### 2. Screen Archetypes

* Login
* Register – User can sign-up for an account
* Logout
* Chat Screen – Users can start a new chat and view previous chats.
* Profile Screen – Allow users to update info and pictures.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Friends
* Groups - This feature is no longer valid. We now only allow users of the app to add one another as friends.
* Messages
* Logout

**Flow Navigation** (Screen to Screen)

* Splash/Signup
* Create Account
* Login
   * Home Screen
* Groups
   * Create Group - This feature is no longer valid. We now only allow users of the app to add one another as friends.
   * View Groups - n/a
   * Remove Group - n/a
* Friends
   * View Friends
   * Add Friends
   * Remove Friends   
* Messages
    * Create a new message to a friend

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="https://github.com/JavaTeamUp/MessageApp/blob/master/sketch.JPG" width=600>

### [BONUS] Digital Wireframes & Mockups
https://github.com/JavaTeamUp/MessageApp/blob/master/Digital-Wireframe_Mockups.JPG

### [BONUS] Interactive Prototype
https://github.com/JavaTeamUp/MessageApp/blob/master/teamup.gif

## Schema 

Model: User		

Property		Type	Description	

objectId		String	unique id for the user post (default field)

emailVerified	Boolean	verify user has a email address

ACL				ACL	

createdAt		Date	Date/Time user was created

updatedAt		Date	Date/Time user was updated

authData		Object	

username		String	unique username for each user

password		String	user's password

email			String	user's email address

fname			String	user's first name

lname			String	user's last name

profileImage	File	user's profile image

friendId		Pointer	user friends

								
Model: Friends	

Property		Type	Description	

objectId		String	unique id for the user post (default field)

updatedAt		Date	Date/Time user was updated

createdAt		Date	Date/Time user was created
ACL				ACL	

FirstName		String	Friend's First Name

LastName		String	Friend's Last Name

profileImage	Pointer	user's profile image
friendId		Pointer	user friends

		
Model: Group

Property	Type	Description	

objectId	String	unique id for the user post (default field)

updatedAt	Date	Date/Time user was updated

createdAt	Date	Date/Time user was created

ACL			ACL	

groupName	String	Group's Name

userId		String	unique id for the user post (default field)

								
Model: Message

Property	Type	Description

objectId	String	unique id for the user post (default field)

updatedAt	Date	Date/Time user was updated

createdAt	Date	Date/Time user was created

ACL			ACL	

body		String	user chat conversation

userId		String	unique id for the user post (default field)

image		File	user file/image uploaded with message			


### Networking

Profile Screen							
(Read/GET) Query logged in user object	

(Update/PUT) Update user profile	

(Delete) Delete Account		
		  
"// (Read/GET) Query all posts where user is user
let query = PFQuery(className:""User"")
query.whereKey(""userId"", equalTo: currentUser)
query.order(byDescending: ""createdAt"")
query.findObjectsInBackground { (message: [PFObject]?, error: Error?) in
   if let error = error {
      print(error.localizedDescription)
   } else if let message = message {
      print(""Successfully retrieved \(message.count) message."")
      // TODO: Do something with messages...
   }
}"							
							
Group Screen		

(Read/GET) Query all groups

(Delete) Delete group							
							
"// (Read/GET) Query all groups where user is user
let query = PFQuery(className:""Group"")
query.whereKey(""userId"", equalTo: currentUser)
query.order(byDescending: ""createdAt"")
query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
   if let error = error {
      print(error.localizedDescription)
   } else if let groups = groups {
      print(""Successfully retrieved groups \(groups.count) groups."")
      // TODO: Do something with groups...
   }
}"							
							
							
							
							
							
							
							
							
							
							
							
							
