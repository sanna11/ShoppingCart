/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CustomerEntity;
import ejb.CustomerEntityFacade;
import ejb.CustomerOrderEntity;
import ejb.CustomerOrderEntityFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "AddCustomerOrder", urlPatterns = {"/AddCustomerOrder"})
public class AddCustomerOrder extends HttpServlet {

    @EJB
    private CustomerEntityFacade customerEntityFacade;
    @EJB
    private CustomerOrderEntityFacade customerOrderEntityFacade;
    @Resource(mappedName = "jms/MessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/Message")
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
        response.setContentType("text/html;charset=UTF-8");
        String OrderNo = request.getParameter("orderno");
        String cid = request.getParameter("id");
        String duedate = request.getParameter("date");
        String comments = request.getParameter("comment");
        if ((cid != null) && (OrderNo != null) && (duedate != null) && (comments != null)) {
//            try {
//                Connection connection = connectionFactory.createConnection();
//                Session session = connection.createSession(false,
//                        Session.AUTO_ACKNOWLEDGE);
//                MessageProducer messageProducer =
//                        session.createProducer(queue);
//                ObjectMessage message = session.createObjectMessage();
            CustomerOrderEntity custOrder = new CustomerOrderEntity();
            CustomerEntity customer = null;
//                CustomerEntity customer = customerEntityFacade.findCust(cid);
            List customers = customerEntityFacade.findAll();
            String pname, pcontact, paddress;
            for (Object object : customers) {
                CustomerEntity cust = (CustomerEntity) object;
                if (cust.getCustomerId().equals(cid)) {
                    customer = cust;
                    break;
                }
            }
            if (customer != null) {
                custOrder.setCustomer(customer);
                custOrder.setOrderNo(OrderNo);
                custOrder.setDuedate(new Date(duedate));
                custOrder.setComment(comments);
//                    message.setObject(custOrder);
//                    messageProducer.send(message);
//                    messageProducer.close();
//                    connection.close();
                customerOrderEntityFacade.create(custOrder);
                response.sendRedirect("ListCustomerOrders");
            }
//            } catch (JMSException ex) {
//                ex.printStackTrace();
//            }
//            customerOrderEntityFacade.create(custOrder);
        }
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<style>body {background-color: #d0e4fe;} "
                    + "p {font-family: \"Times New Roman\"; font-size: 20px; }"
                    + ".mytext { height: 30px;}"
                    + " </style> ");
            out.println("<title>AddCustomerOrder</title>");
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet AddCustomerOrder at " + request.getContextPath() + "</h1>");
            out.println("<form>");
            out.println("Order No:<br> <input type='text' name='orderno' class='mytext'>");
            out.println("<br><br>");
            out.println("Customer ID:<br> <input type='text' name='id' class='mytext'>");
            out.println("<br><br>");
            out.println("Amount:<br> <input type='text' name='amount' value='0.0' class='mytext' disabled><br>");
            out.println("<br><br>");
            out.println("Due Date:<br> <input type='text' name='date' class='mytext'><br>");
            out.println("<br><br>");
            out.println("Comments:<br> <textarea name='comment' rows='4' cols='35'></textarea><br>");
            out.println("<br><br><br>");
            out.println("<input type='submit' value='Add New Customer Order' class='mytext'><br>");
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
