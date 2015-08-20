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
public class CustomerOrderEntityFacade extends AbstractFacade<CustomerOrderEntity> {
    @PersistenceContext(unitName = "ShoppingCart_EAD-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerOrderEntityFacade() {
        super(CustomerOrderEntity.class);
    }
    
    public CustomerOrderEntity findCustOrder(String OrderNo)
    {
        List customerOrders = findAll();
        CustomerOrderEntity custOrder = null;
        for (Object object : customerOrders) {
            CustomerOrderEntity customerOrder = (CustomerOrderEntity) object;
            if (customerOrder.getOrderNo().equals(OrderNo)) {
                custOrder = customerOrder;
                break;
            }
        }
        return custOrder;
    }
    
}
