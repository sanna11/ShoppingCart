/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USER
 */
@Stateless
public class CustomerOrderLineEntityFacade extends AbstractFacade<CustomerOrderLineEntity> {
    @PersistenceContext(unitName = "ShoppingCart_EAD-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerOrderLineEntityFacade() {
        super(CustomerOrderLineEntity.class);
    }
    
}
