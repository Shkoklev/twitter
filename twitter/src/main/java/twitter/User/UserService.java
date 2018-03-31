package twitter.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    private boolean checkIfUserExists(String email) {
        User user=userRepository.findUserByEmail(email);
        if(user==null)
            return false;
        return true;
    }

    public boolean signUpNewUser(String email) {
        if(!checkIfUserExists(email)) {
            User newUser=new User(email);
            newUser.getFollowingUsers().add(newUser);
            userRepository.save(newUser);
            return true;
        }
        return false;
    }

    public boolean signInWIthAccount(String email) {
        if(checkIfUserExists(email))
            return true;
        return false;
    }

    public boolean followAnotherUser(Long mainUserId,Long userToFollowId) {
        User currentUser=userRepository.findUserById(mainUserId);
        User userToFollow=userRepository.findUserById(userToFollowId);

        currentUser.getFollowingUsers().add(userToFollow);
        userRepository.save(currentUser);
        return true;
    }

}
