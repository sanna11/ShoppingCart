/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CustomerEntity;
import ejb.CustomerEntityFacade;
import ejb.CustomerOrderEntity;
import ejb.CustomerOrderEntityFacade;
import ejb.SessionManagerBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "UpdateCustomerOrder", urlPatterns = {"/UpdateCustomerOrder"})
public class UpdateCustomerOrder extends HttpServlet {

    @EJB
    private SessionManagerBean sessionManagerBean;
    @EJB
    private CustomerEntityFacade customerEntityFacade;
    @EJB
    private CustomerOrderEntityFacade customerOrderEntityFacade;
    
    private CustomerOrderEntity updatingCustomerOrder = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
        String orderNo = request.getParameter("orderno");
//        List customers = customerOrderEntityFacade.findAll();
//        CustomerOrderEntity updatingCustomerOrder = customerOrderEntityFacade.findCustOrder(orderNo);
        List customerOrders = customerOrderEntityFacade.findAll();
        
        for (Object object : customerOrders) {
                    CustomerOrderEntity custOrder = (CustomerOrderEntity) object;
                    if (custOrder.getOrderNo().equals(orderNo)) {
                        updatingCustomerOrder = custOrder;
                        break;
                    }
                }
        String cid, cname, duedate, amount, comments;
        if (updatingCustomerOrder == null) {
            response.sendRedirect("ListCustomerOrders");
        }
        cid = updatingCustomerOrder.getCustomer().getCustomerId();
        cname = updatingCustomerOrder.getCustomer().getName();
        duedate = dateFormat.format(updatingCustomerOrder.getDuedate());
        System.out.println("date "+duedate);
        amount = String.valueOf(updatingCustomerOrder.getPrice());
        comments = updatingCustomerOrder.getComment();
        String action = null;
        action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("Update Customer Order")) {
            cid = request.getParameter("custid");

            amount = request.getParameter("amount");
//            double price = Double.parseDouble(amount);
            duedate = request.getParameter("date");
            comments = request.getParameter("comment");
            if ((cid != null) && (duedate != null) && (comments != null) ) {
//                CustomerEntity customer = customerEntityFacade.findCust(cid);
                List customers = customerEntityFacade.findAll();
                CustomerEntity customer = null;
                for (Object object : customers) {
                    CustomerEntity cust = (CustomerEntity) object;
                    if (cust.getCustomerId().equals(cid)) {
                        customer = cust;
                        break;
                    }
                }
                if (customer != null) {
                    orderNo = updatingCustomerOrder.getOrderNo();
                    customerOrderEntityFacade.remove(updatingCustomerOrder);
                    CustomerOrderEntity custOrder = new CustomerOrderEntity();
                    custOrder.setOrderNo(orderNo);
                    custOrder.setCustomer(customer);
                    custOrder.setDuedate(new Date(duedate));
                    custOrder.setComment(comments);
//                    custOrder.setPrice(price);

                    customerOrderEntityFacade.create(custOrder);
                    response.sendRedirect("ListCustomerOrders");
                }
            }
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
            out.println("<title>AddCustomerOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form>");
            out.println("Order No:<br> <input type='text'  class='mytext' value='" + orderNo + "' disabled>");
            out.println("<br><br>");
            out.println("Customer ID:<br> <input type='text' name='custid' value='" + cid + "' class='mytext'>");
            out.println("<br><br>");
            out.println("Amount:<br> <input type='text' name='amount' value='" + amount + "' class='mytext' disabled><br>");
            out.println("<br><br>");
            out.println("Due Date:<br> <input type='text' name='date' value='" + duedate + "' class='mytext'><br>");
            out.println("<br><br>");
            out.println("Comments:<br> <textarea name='comment' rows='4' cols='35'>" + comments + "</textarea><br>");
            out.println("<br><br><br>");
            out.println("<input type='submit' name='action' value='Update Customer Order' class='mytext'><br>");
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
