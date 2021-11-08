# NewsX

The API key was generated from the https://newsapi.org/

For documentation of how to use API check out this link https://newsapi.org/docs

The Capstone Project for Udacity Android Developer Nanodegree

Description 

A News App which helps users especially living in India to get latest news about what is happening in India. A detailed information of each news article will be shown with the goal to keep the user up to date. The user will be provided with News Highlights and the news is sorted according to the categories like General, Entertainment, Sports, Health, Business, Science and Technology. The user will be able to save the news offline so that he/she can read the article anytime and also will be notified accordingly.

Intended User

Intended for users who want to keep themselves up to date with current affairs happening in India.

Features


●	Displays Top Headlines of the latest News happening in India  
●	Displays categories like General, Entertainment, Sports, Health, Business, Science and Technology and get top headlines related to each category 
●	Display detailed information about the selected news article.
●	Save the News headline data for offline purpose
●	Get randomly notified when new news arrives.
●	Share news to anyone in social media.
●	User Authentication is done via Firebase Authentication

Programming Language used: Java
Android Studio version: 3.2.1 
Gradle Version : 4.6
Support for accessibility: Content Description will be provided in all the Image Views.



Key Considerations

How will your app handle data persistence? 

The App uses Retrofit to load data from the news API from https://newsapi.org/ and its uses combination of Live Data and View Model to handle data persistence. 
The App also uses Room Database and combination of Live Data and View Model to handle data persistence.

Describe any edge or corner cases in the UX.

When the app receives notification and when user clicks on the notification the same activity is loaded in which the user had previously left.
When there is no internet connection then user is given a message via toast saying “no internet  connection” if the user is logged in then only Favorites news articles will we available for user to see and if there are no Favorite news articles then a message will be displayed saying no favorite news articles.

Describe any libraries you’ll be using and share your reasoning for including them.

Picasso is used to handle the loading and caching of images. (version 2.5.2)
Retrofit is used to get data from news API.(version 2.3.0)
Butter knife library is used for Data binding.(version 8.8.1)
Lifecycle Architecture is used to implement view model and Live Data(version 1.1.1)
Room is used for database offline (version 1.1.1)

Describe how you will implement Google Play Services or other external services.

AdMob: To monetize the App
Firebase Authentication.: Login and Registration.
Firebase Cloud Messaging: Push Notification.


Next Steps: Required Tasks

Task 1: Project Setup
1.	Create an account on https://newsapi.org/ and get the API KEY.
2.	Configure the libraries required for the project
Task 2: Implement UI for Each Activity and Fragment
1.	Build UI for Login and Registration Activity.
2.	Build UI for News Activity and News Fragment
3.	Build UI for News Details Activity.
4.	Build Fragments to populate the View Pager and Tab Layout.
5.	Ensure all strings are in a strings.xml file and enables RTL layout switching on all layouts



Task 3: Implement Networking (ONLINE)
1.	Bind the activities with data received from the News API.
2.	Fetch data using Retrofit and then use Live Data and View Model to populate the UI.

Task 4: Implement Database (OFFLINE)
1. Implement Room Database to store news data offline and use Live Data and View model to populate the UI.


Task 5: Implement Widget
1.	Implement widget UI and display pinned news article selected by the user.
2.	Notification is implemented using Intent Service and Firebase Job dispatcher in a way that user is notified when his/her phone is kept on charging and is notified to check the news after every 15 mins.

Task 6: Implement Firebase and AdMob Services
1.	Implement Firebase Authentication for registration and login
2.	Implement Firebase Cloud Messaging for Push notification.
3.	Implement AdMob services to display dummy ads







