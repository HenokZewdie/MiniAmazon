package AmazonPackage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Meeliana on 7/17/2017.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long>{
   List<Customer> findByZipCode(long zip);
   List<Customer> findById(long id);

}
