/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Sanna
 */
@Entity
public class CustomerOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;  
    
    
    @ManyToOne
    private CustomerEntity customer;
    
    @Id
    private String OrderNo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Duedate;
    private String comment;
    private double price = 0;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (OrderNo != null ? OrderNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrderEntity)) {
            return false;
        }
        CustomerOrderEntity other = (CustomerOrderEntity) object;
        if ((this.OrderNo == null && other.OrderNo != null) || (this.OrderNo != null && !this.OrderNo.equals(other.OrderNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.CustomerOrderEntity[ id=" + OrderNo + " ]";
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String OrderNo) {
        this.OrderNo = OrderNo;
    }

    public Date getDuedate() {
        return Duedate;
    }

    public void setDuedate(Date Duedate) {
        this.Duedate = Duedate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    
}
