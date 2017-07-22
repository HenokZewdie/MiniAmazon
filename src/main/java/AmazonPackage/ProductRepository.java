package AmazonPackage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Meeliana on 7/16/2017.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

}
