package twitter.User;

import twitter.Tweet.Tweet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String email;

    @OneToMany(mappedBy="user")
    private List<Tweet> tweets;
    @ManyToMany(targetEntity=User.class,fetch=FetchType.EAGER)
    private Set<User> followingUsers;

    public User(){
        tweets=new ArrayList<>();
        followingUsers=new HashSet<>();
    }
    public User(String email) {
        this.email=email;
        tweets=new ArrayList<>();
        followingUsers=new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Set<User> getFollowingUsers() {
        return followingUsers;
    }

    public void setFollowingUsers(Set<User> followingUsers) {
        this.followingUsers=followingUsers;
    }
}
