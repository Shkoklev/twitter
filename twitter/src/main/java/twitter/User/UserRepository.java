package twitter.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    User findUserByEmail(String email);
    User findUserById(Long id);
}
