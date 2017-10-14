package com.barclays.main;

import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by TUSHAR on 24-04-2017.
 */

@Component
public class TwitterStreamImpl {

    Twitter twitter = TwitterFactory.getSingleton();
    public static Long _twitterUserId;
    public static FilterQuery filtre;
    private Status status;

    public Status getStatus(long id) {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey("sJUXr9KQ7tKV9sXMx2MIMcjF7")
                .setOAuthConsumerSecret("vZneZFGLBritLIOpeC3kj78FExvupyRvcmNSHTn0QOHv62KJUc")
                .setOAuthAccessToken("112106055-dw9Wfn8FHujP8ym5LDP8BK4AM2qnPlMiiwLXjYWb")
                .setOAuthAccessTokenSecret("GRgnzNmyxG8iHoQdc8Np0vlzm10gxgK6EJyeC5HadqjaJ");
        twitter4j.TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onException(Exception e) {

            }

            @Override
            public void onStatus(Status statusRecieved) {
               /* Twitter tf = new TwitterFactory().getInstance();
                System.out.println("Status is :" + status.getText());
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                System.out.println(status.getInReplyToStatusId());
                StatusUpdate statusUpdate = new StatusUpdate("Hey @" + status.getUser().getScreenName() + " ! Thanks for tweeting me :)");
                statusUpdate.inReplyToStatusId(status.getInReplyToStatusId());*/
               status = statusRecieved;
              /*  try {
                    tf.updateStatus(statusUpdate);
                } catch (TwitterException e) {
                    e.printStackTrace();
                }*/

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int i) {

            }

            @Override
            public void onScrubGeo(long l, long l1) {

            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {

            }
        };

        twitterStream.addListener(listener);
        FilterQuery query = new FilterQuery();
        query.follow(id);
        twitterStream.filter(query);
        /*filtre = new FilterQuery();
        filtre.follow(112106055);

        String[] keywordsArray = {"tsahay"};
        filtre.track(keywordsArray);
        n double[][] locations = {{-74, 40}, {-73, 41}}; //those are the boundary from New York City
        filtre.locations(locations);
        twitterStream.filter(filtre);*/
    return status;
    }


    public void sendDirectTweet(String twitterName, String directMessageInput) throws TwitterException {
        DirectMessage directMessage = twitter.sendDirectMessage(twitterName, directMessageInput);
    }

}



