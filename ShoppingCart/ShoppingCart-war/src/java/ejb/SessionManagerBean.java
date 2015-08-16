/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Sanna
 */
@Singleton
@LocalBean
@WebListener
public class SessionManagerBean implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
