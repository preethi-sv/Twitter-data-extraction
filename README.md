# Twitter-data-extraction
Extraction of data from Twitter.

Programming language : JAVA
IDE : Eclipse
Name of the Twitter account used : Alexa
Twitter App name : Twitter_Access_App_1

Twitter4j library is downloaded from its official webpage and extracted after JDK and Eclipse IDE is installed. 
The JAR file twitter4j-core-4.0.4.jar is added to the application class path. 
An app is created in Twitter by giving a name for the app, an URL. 
Access level can be changed in the app. Access token and secret is requested from the twitter. 
Finally, consumer key, consumer secret, access token and access token secret are copied. 
In the Java project, all the required twitter4j classes such as ConfigurationBuild, TwitterFactory, Twitter,
 Status, Query, Trends, IDs, User, etc are imported and the following are implemented.

1.Get Twitter Timeline of the user.
2.Get the Trends on Twitter based on user input WOEID (Where On Earth Identifier)
3.Post and Delete Status
4.Send personal messages to follower.
5.Search tweets using Keywords.
6.Get Friends and Followers of any public user.
