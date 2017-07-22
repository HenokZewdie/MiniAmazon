package AmazonPackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date orderDate;
    private long quantity;
    private long customerID;
    private long productID;

    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {return orderDate;}

    public void setOrderDate(Date orderDate) {this.orderDate = orderDate;}

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }
}
