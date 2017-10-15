package com.barclays.controller;

import com.barclays.dto.UserSample;
import com.barclays.service.TwitterServiceImpl;
import com.barclays.dto.Tweet;
import com.barclays.service.TwitterStreamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.List;

@Controller
public class TwitterController {


    @Autowired
    TwitterServiceImpl twitterServiceImpl;

    @Autowired
    TwitterStreamImpl twitterStreamImpl;

    @Deprecated
    @RequestMapping(value = "/tweet/{username}",method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public List<Tweet> getHomePageTwiiterFeed(@PathVariable(value = "username") String username) throws IOException {
        return twitterServiceImpl.getTweetList(username);
    }


    @RequestMapping(value = "/tweets/{username}",method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public List<Status> getTweets(@PathVariable(value = "username") String username) throws IOException, TwitterException {
        return twitterServiceImpl.getTweets(username);
    }

    @RequestMapping(value = "/stream/{id}",method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Status getTwitterStream(@PathVariable(value = "id") long id) throws IOException {
        return twitterStreamImpl.getStatus(id);
    }
}
