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
 * @author Sanna
 */
@Stateless
public class CustomerOrderLineFacade extends AbstractFacade<CustomerOrderLine> {
    @PersistenceContext(unitName = "ShoppingCart-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerOrderLineFacade() {
        super(CustomerOrderLine.class);
    }
    
}
