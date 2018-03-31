package twitter.Tweet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter.User.User;
import twitter.User.UserRepository;

import java.util.ArrayDeque;
import java.util.Deque;

@Component
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    UserRepository userRepository;

    private Deque<Tweet> allTweets;

    public boolean writeTweet(Long userId,String tweetMessage) {
        Tweet newTweet=new Tweet(tweetMessage,userRepository.findUserById(userId));
        tweetRepository.save(newTweet);
        updateUserTweets(newTweet,userId);
        return true;
    }

    private void updateUserTweets(Tweet tweet,Long userId) {
        User user = userRepository.findUserById(userId);
        user.getTweets().add(tweet);
        userRepository.save(user);
    }

    public String showTweets(Long userId) {
        allTweets = new ArrayDeque<>();
        getAllTweetsFromFollowers(userId);
        return tweetsToString();
    }

    public String tweetsToString() {
        StringBuilder sb=new StringBuilder();
        for(Tweet tweet: allTweets) {
            sb.append(tweet.getUser().getEmail()+":\n"+ tweet.getTweet() + "\n\n");
        }
        return sb.toString();
    }

    private void getAllTweetsFromFollowers(Long userId) {
        userRepository.findUserById(userId)
                .getFollowingUsers()
                .stream()
                .map(User::getTweets)
                .flatMap(e->e.stream())
                .forEach(allTweets::push);
    }
}
