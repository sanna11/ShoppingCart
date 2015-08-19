/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sanna
 */
@Stateless
public class CustomerEntityFacade extends AbstractFacade<CustomerEntity> {
    @PersistenceContext(unitName = "ShoppingCart_EAD-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerEntityFacade() {
        super(CustomerEntity.class);
    }
    
    public CustomerEntity findCust(String id)
    {
        List customers = findAll();
        CustomerEntity cust = null;
        for (Object object : customers) {
            CustomerEntity customer = (CustomerEntity) object;
            if (customer.getCustomerId().equals(id)) {
                cust = customer;
                break;
            }
        }
        return cust;
    }
    
}
