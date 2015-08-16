/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.Customer;
import ejb.CustomerFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sanna
 */
@WebServlet(name = "AddCustomer", urlPatterns = {"/AddCustomer"})
public class AddCustomer extends HttpServlet {

    @EJB
    private CustomerFacade customerFacade;
    
    @Resource(mappedName = "jms/MessageBeanFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName = "jms/MessageBean")
    private Queue queue;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
        if ((id != null) && (name != null) && (address != null) && (contact != null)) {
            try {
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false,
                        Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer =
                        session.createProducer(queue);

                ObjectMessage message = session.createObjectMessage();
                // here we create NewsEntity, that will be sent in JMS message
                Customer customer = new Customer();
                customer.setCustomerID(id);
                customer.setName(name);
                customer.setAddress(address);
                customer.setContactNo(contact);
                message.setObject(customer);
                messageProducer.send(message);
                messageProducer.close();
                connection.close();
//                response.sendRedirect("ListNews");

            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }

        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head><style>body {background-color: #d0e4fe;} "
                    + "h1 {color: orange;text-align: center;} "
                    + "p {font-family: \"Times New Roman\"; font-size: 20px; }"
                    + " </style> ");
            out.println("<title>AddCustomer</title>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet AddCustomer at " + request.getContextPath() + "</h1>");
            List news = customerFacade.findAll();
//            int count=news.size();
            out.println("<br/><form>");
            out.println("Customer ID: <br/><input type='text' name='id' ><br/><br/>");
            out.println("Name: <br/><input type='text' name='name'><br/><br/>");
            out.println("Address: <br/><textarea name='body' ></textarea><br/><br/>");
            out.println("Contact: <br/><input type='text' name='contact'><br/><br/>");
            out.println("<input type='submit'><br/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
