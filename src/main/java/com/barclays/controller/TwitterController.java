package com.barclays.controller;

import com.barclays.main.TwitterServiceImpl;
import com.barclays.model.Tweet;
import com.barclays.main.TwitterStreamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twitter4j.Status;

import java.net.MalformedURLException;
import java.util.List;

@Controller
public class TwitterController {


    @Autowired
    TwitterServiceImpl twitterServiceImpl;

    @Autowired
    TwitterStreamImpl twitterStreamImpl;

    @RequestMapping(value = "/tweets/{username}",method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public List<Tweet> getHomePageTwiiterFeed(@PathVariable(value = "username") String username) throws MalformedURLException {
        return twitterServiceImpl.getTweetList(username);
    }

    @RequestMapping(value = "/stream/{id}",method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Status getTwitterStream(@PathVariable(value = "id") long id) throws MalformedURLException {
        return twitterStreamImpl.getStatus(id);
    }
}
