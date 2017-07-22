package AmazonPackage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Meeliana on 7/16/2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Long countByEmail(String email);

}