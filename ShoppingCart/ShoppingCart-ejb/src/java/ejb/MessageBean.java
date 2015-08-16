/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sanna
 */
@MessageDriven(mappedName = "jms/MessageBean", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageBean implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;
    @PersistenceContext(unitName = "ShoppingCart-ejbPU")
    private EntityManager em;

    public MessageBean() {
    }

    @Override
    public void onMessage(Message message) {

        ObjectMessage msg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                Object obj = msg.getObject();

                if (obj instanceof Customer) 
                {
                    persist((Customer) obj);
                } 
                else if (obj instanceof CustomerOrder) 
                {
                    persist((CustomerOrder)obj);
                } 
                else if (obj instanceof CustomerOrderLine) 
                {
                    persist((CustomerOrderLine)obj);
                }
                else
                {
                    //error validation part
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
