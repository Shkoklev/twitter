package twitter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter.Tweet.TweetService;
import twitter.User.UserService;

@RestController
@CrossOrigin
public class TwitterController {

    @Autowired
    private UserService userService;
    @Autowired
    private TweetService tweetService;

    @GetMapping("/login")
    public boolean signIn(@RequestParam("email") String userEmail) {
      return userService.signInWIthAccount(userEmail);
    }

    @RequestMapping(method=RequestMethod.POST,value="/signup")
    public boolean signUp(@RequestParam("email") String userEmail) {
        return userService.signUpNewUser(userEmail);
    }

    @RequestMapping(method=RequestMethod.POST,value="/users/{userId}")
    public boolean writeNewTweet(@PathVariable Long userId,@RequestParam("message") String tweet) {
        return tweetService.writeTweet(userId,tweet);
    }

    @GetMapping("/users/{userId}")
    public String showTweets(@PathVariable Long userId) {
        return tweetService.showTweets(userId);
    }

    @RequestMapping(method=RequestMethod.PUT,value="/users/{userId}")
    public boolean followAnotherUser(@PathVariable Long userId,@RequestParam("userToFollowId") Long userToFollowId) {
        return userService.followAnotherUser(userId,userToFollowId);
    }

}
