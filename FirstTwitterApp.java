package firsttwitterapp;

import java.util.List;
import java.util.Scanner;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.IDs;
import twitter4j.User;


public class FirstTwitterApp
{
	public static void main(String args[]) throws TwitterException 
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("") //insert the consumer key
			.setOAuthConsumerSecret("") //insert the consumer secret
			.setOAuthAccessToken("") //insert the access token
			.setOAuthAccessTokenSecret(""); //insert the access token secret here.
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
	
		
        Scanner scan = new Scanner(System.in);
        
        /*No.1	*/
        System.out.println("Do you want to display the current user's home timeline or some other user's timeline ? : (Type 1 for Home Timeline or 2 for User Timeline) ");
        int tell = scan.nextInt();
                
		if (tell==1)
                {
                 
                    List<Status> status = twitter.getHomeTimeline(); 
                    for (Status st : status)
                    {
                    		System.out.println(st.getUser().getName() + "--------" +st.getId() + "--------" + st.getText());
			
                    }
                    System.out.println("\n\n\n");
                }
		
		if (tell==2)
                {
                    System.out.println("Type in the twitter id of the user (without @) : ");
                    String user_id = scan.next();
                    List<Status> status = twitter.getUserTimeline(user_id); 
                    for (Status st : status)
                    {
                    		System.out.println(st.getUser().getName() + "--------" +st.getId() + "--------" + st.getText());
                    }
                    System.out.println("\n\n\n");
                }
		
		/*No.2*/
        
		
		
		System.out.println("Enter a valid WOEID to get the #Trends in that area : ");
	    int woeid = scan.nextInt();
		Trends trends = twitter.getPlaceTrends(woeid);
        int count = 0;
        for (Trend trend : trends.getTrends()) 
        {
            if (count < 10) 
            {
                System.out.println(trend.getName() + "----" + trend.getURL());
                count++;
            }
        }
       
		
		/*No.3*/
        
		System.out.println("\n\n\nEnter the tweet you need to post as status : ");
		String tweetstatus = scan.next();
		twitter.updateStatus(tweetstatus);	
		System.out.println("---Status posted successfully!---");

        int status_count=0,del_count;
        System.out.println("Enter the number of the status which is to be deleted (Type 1 to delete the most recent status posted) : ");
        del_count = scan.nextInt();
                
        List<Status> status = twitter.getUserTimeline();
		for (Status st : status)
		{	
			status_count++;
			if(status_count==del_count)
                {
                    twitter.destroyStatus(st.getId());
                }
		}
		System.out.println("---Status deleted successfully!---");
		
		/*No.4*/
		
		System.out.println("\n\n\nEnter keywords to search :\n(Tip : Include # to search for hashtag keywords)\n");
		String querysearch = scan.next();
        Query query = new Query(querysearch);
        QueryResult result;
        do 
        {
        		result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            for (Status tweet : tweets) 
            {
                	System.out.println(tweet.getUser().getScreenName() + " - " + tweet.getText() + "\n\n\n"); 
            }
         } while ((query = result.nextQuery()) != null);
         System.out.println("---Search complete!---");
       
		/*No.5*/
		
		System.out.println("\nEnter the text message that you need to send : ");
		String directMessage= scan.next();
		System.out.println("\nType in the twitter handle name of the follower to whom you need to send the message (without @ symbol): ");
		String twitterName = scan.next();
		
		twitter.sendDirectMessage(twitterName, directMessage);
		System.out.println("---Message Sent Successfully!---"); 
		
		/*No.6*/
		
		System.out.println("\n\n\nEnter the twitter id of the user to display his/her followers and following list : ");
		String username = scan.next();
		
        long cursor = -1;
        IDs ids;
        System.out.println("\n\n\nListing followers's ids : ");
        do 
        {
             ids = twitter.getFollowersIDs(username,cursor);
             for (long id : ids.getIDs()) 
             {
            	 	System.out.print(id);
                User user = twitter.showUser(id);
                System.out.println(" " + user.getName());
             }
         } while ((cursor = ids.getNextCursor()) != 0);
                
             
         long cursor2 = -1;
         IDs ids2;
         System.out.println("\n\n\nListing friends's ids (following) : ");
         do 
         {
              ids2 = twitter.getFriendsIDs(username,cursor2);
              for (long id : ids2.getIDs())
              {
                  System.out.print(id);
                  User user = twitter.showUser(id);
                  System.out.println(" " + user.getName());
              }
         } while ((cursor2 = ids2.getNextCursor()) != 0);
     
          scan.close(); 

	}
}
