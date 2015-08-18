/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CustomerEntity;
import ejb.CustomerEntityFacade;
import ejb.SessionManagerBean;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Sanna
 */
@WebServlet(name = "UpdateCustomer", urlPatterns = {"/UpdateCustomer"})
public class UpdateCustomer extends HttpServlet {

    @Resource(mappedName = "jms/MessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/Message")
    private Queue queue;
    @EJB
    private SessionManagerBean sessionManagerBean;
    @EJB
    private CustomerEntityFacade customerEntityFacade;
    private CustomerEntity updatingCustomer = null;

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
        String uri = request.getQueryString();
        String id = request.getParameter("id");
        List customers = customerEntityFacade.findAll();
        String pname, pcontact, paddress;
        for (Object object : customers) {
            CustomerEntity customer = (CustomerEntity) object;
            if (customer.getCustomerId().equals(id)) {
                updatingCustomer = customer;
                break;
            }
        }
        if (updatingCustomer == null) {
            response.sendRedirect("ListCustomers");
        }
        pname = updatingCustomer.getName();
        paddress = updatingCustomer.getAddress();
        pcontact = updatingCustomer.getContact();
        String action = null;
        action = request.getParameter("action");

        if (action != null && action.equalsIgnoreCase("Update Customer")) {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String contact = request.getParameter("contact");
//        manage=(String)request.getSession().getAttribute("manage");
            if ((name != null) && (address != null) && (contact != null)) {
                id = updatingCustomer.getCustomerId();
                customerEntityFacade.remove(updatingCustomer);
                try {
                    Connection connection = connectionFactory.createConnection();
                    Session session = connection.createSession(false,
                            Session.AUTO_ACKNOWLEDGE);
                    MessageProducer messageProducer =
                            session.createProducer(queue);

                    ObjectMessage message = session.createObjectMessage();

                    CustomerEntity e = new CustomerEntity();
                    e.setCustomerId(id);
                    e.setName(name);
                    e.setAddress(address);
                    e.setContact(contact);
                    message.setObject(e);
                    messageProducer.send(message);
                    messageProducer.close();
                    connection.close();
                    response.sendRedirect("ListCustomers");

                } catch (JMSException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (action != null && action.equalsIgnoreCase("Delete Customer")) {

            customerEntityFacade.remove(updatingCustomer);
            response.sendRedirect("ListCustomers");

        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<style>body {background-color: #d0e4fe;} "
                    + "p {font-family: \"Times New Roman\"; font-size: 20px; }"
                    + ".mytext { height: 30px;}"
                    + " </style> ");
            out.println("<title>Servlet AddCustomer</title>");
            out.println("</head>");
            out.println("<body>");
//            out.println(name);
//            out.println(address);
//            out.println(contact);
            //out.println("<h1>Servlet AddCustomer at " + request.getContextPath() + "</h1>");
            out.println("<form>");
            out.println("Customer ID:<br> <input type='text' name='cid' value='" + id + "' class='mytext' disabled>");
            out.println("<br><br>");
            out.println("Name:<br> <input type='text' name='name' value='" + pname + "' class='mytext'><br>");
            out.println("<br><br>");
            out.println("Address:<br> <textarea name='address' rows='4' cols='35'>" + paddress + "</textarea><br>");
            out.println("<br><br>");
            out.println("Contact:<br> <input type='text' name='contact'class='mytext' value='" + pcontact + "'><br>");
            out.println("<br><br><br>");
            out.println("<input type='submit' name='action' value='Update Customer' class='mytext'>\t");
            out.println("<input type='submit' name='action' value='Delete Customer' class='mytext'><br>");
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
