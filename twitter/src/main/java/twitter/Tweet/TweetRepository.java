package twitter.Tweet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import twitter.User.User;

import java.util.List;

@Repository
public interface TweetRepository extends CrudRepository<Tweet,Long> {

    List<Tweet> findAllByUser(User user);
}
