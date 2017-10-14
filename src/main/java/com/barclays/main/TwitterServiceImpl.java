package com.barclays.main;

import com.barclays.manager.TwitterCriteria;
import com.barclays.model.Tweet;
import com.barclays.manager.TweetManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TwitterServiceImpl {
    private static final String USERNAME = "Username: ";
    private static final String RETWEETS = "Retweets: ";
    private static final String TEXT = "Text: ";
    private static final String MENTIONS = "Mentions: ";
    private static final String HASHTAGS = "Hashtags: ";

    public List<Tweet> getTweetList(String username) throws MalformedURLException {
        /**
         * Reusable objects
         */
        TwitterCriteria criteria = null;
        List<Tweet> t = null;

        /**
         *  Example 1 - Get tweets by username
         **/

	/*	criteria = TwitterCriteria.create()
                .setUsername("tsahay2")
				.setMaxTweets(10);
		t = TweetManager.getTweets(criteria);
		
		System.out.println("### Example 1 - Get tweets by username [barackobama]");
		System.out.println(USERNAME + t.get(0).getUsername());
		System.out.println(RETWEETS + t.get(0).getRetweets());
		System.out.println(TEXT + t.get(0).getText());
		System.out.println(MENTIONS + t.get(0).getMentions());
		System.out.println(HASHTAGS + t.get(0).getHashtags());
		System.out.println();
		*/
        /**
         *  Example 2 - Get tweets by query search
         **/

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        criteria = TwitterCriteria.create()
                .setUsername(username);

        t = TweetManager.getTweets(criteria);
      /*  for (Tweet twitter : t
                )

        {
//            String s[] = twitter.getText().split(".pdf");
//            String urlString = s[0] + ".pdf".trim();
//            System.out.println("urlString : " + urlString);

            String urlFinal = extractUrl(twitter.getText());
            System.out.println("Actual text :" + twitter.getText());
            System.out.println("Extracted Url : " + urlFinal);
            if (null != urlFinal) {
                URL url = new URL((urlFinal));
//            System.out.println(FilenameUtils.getBaseName(url.getPath()));
                try {
                    saveFileFromUrlWithCommonsIO("downloads/" + FilenameUtils.getBaseName(url.getPath()) + ".pdf", urlFinal);
//                saveFileFromUrlWithCommonsIO("test.pdf", urlFinal);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println(twitter.toString());


        }*/

        return t;
    }

    private static String extractUrl(String urlToBeExtracted) {
        String extractedUrl = null;
        String urlRegex = "((http|https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        final Pattern urlPattern = Pattern.compile(
                urlRegex,
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = urlPattern.matcher(urlToBeExtracted);
        while (matcher.find()) {
            int matchStart = matcher.start(1);
            int matchEnd = matcher.end();
            // now you have the offsets of a URL match
            extractedUrl = urlToBeExtracted.substring(matchStart, matchEnd);
            System.out.println(extractedUrl);
        }
        return extractedUrl;
    }

    /**
     * Example 3 - Get tweets by username and bound dates
     **/
    /*	criteria = TwitterCriteria.create()
                .setUsername("barackobama")
				.setSince("2015-09-10")
				.setUntil("2015-09-12")
				.setMaxTweets(1);
		t = TweetManager.getTweets(criteria).get(0);
		
		System.out.println("### Example 3 - Get tweets by username and bound dates [barackobama, '2015-09-10', '2015-09-12']");
		System.out.println(USERNAME + t.getUsername());
		System.out.println(RETWEETS + t.getRetweets());
		System.out.println(TEXT + t.getText());
		System.out.println(MENTIONS + t.getMentions());
		System.out.println(HASHTAGS + t.getHashtags());
		System.out.println();*/
    public static String expandUrl(String shortenedUrl) throws IOException {
        URL url = new URL(shortenedUrl);
        // open connection
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);

        // stop following browser redirect
        httpURLConnection.setInstanceFollowRedirects(false);

        // extract location header containing the actual destination URL
        String expandedURL = httpURLConnection.getHeaderField("Location");
        httpURLConnection.disconnect();

        return expandedURL;
    }

    public static void saveFileFromUrlWithCommonsIO(String fileName,
                                                    String fileUrl) throws MalformedURLException, IOException {
        FileUtils.copyURLToFile(new URL(fileUrl), new File(fileName));
    }

}