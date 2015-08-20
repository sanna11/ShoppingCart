/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author USER
 */
@Entity
public class CustomerOrderLineEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id    
    private String lineNo;
    
    private String partNo;
    
    private double amount;
    
    @ManyToOne
    private CustomerOrderEntity customerOrder;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineNo != null ? lineNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerOrderLineEntity)) {
            return false;
        }
        CustomerOrderLineEntity other = (CustomerOrderLineEntity) object;
        if ((this.lineNo == null && other.lineNo != null) || (this.lineNo != null && !this.lineNo.equals(other.lineNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.CustomerOrderLineEntity[ id=" + lineNo + " ]";
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String LineNo) {
        this.lineNo = LineNo;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CustomerOrderEntity getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrderEntity customerOrder) {
        this.customerOrder = customerOrder;
    }
    
}
