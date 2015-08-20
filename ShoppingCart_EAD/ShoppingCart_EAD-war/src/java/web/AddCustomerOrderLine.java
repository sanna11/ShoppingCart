/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CustomerOrderEntity;
import ejb.CustomerOrderEntityFacade;
import ejb.CustomerOrderLineEntity;
import ejb.CustomerOrderLineEntityFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
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
@WebServlet(name = "AddCustomerOrderLine", urlPatterns = {"/AddCustomerOrderLine"})
public class AddCustomerOrderLine extends HttpServlet {

    @EJB
    private CustomerOrderLineEntityFacade customerOrderLineEntityFacade;
    
    @EJB
    private CustomerOrderEntityFacade customerOrderEntityFacade;
    
    CustomerOrderEntity customerOrder;
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
        List customerOrders = customerOrderEntityFacade.findAll();
        
        for (Object object : customerOrders) {
                    CustomerOrderEntity custOrder= (CustomerOrderEntity) object;
                    if (custOrder.getOrderNo().equals(orderNo)) {
                        customerOrder = custOrder;
                        break;
                    }
                }
        if (customerOrder == null) {
            response.sendRedirect("ListCustomerOrders");
        }
        String cid, cname, duedate, amount, comments;
        
        cid = customerOrder.getCustomer().getCustomerId();
        cname = customerOrder.getCustomer().getName();
        duedate = dateFormat.format(customerOrder.getDuedate());
        System.out.println("date "+duedate);
        amount = String.valueOf(customerOrder.getPrice());
        comments = customerOrder.getComment();
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
            out.println("<style> "
                    + "table, th, td {  border: 1px solid black;}"
                    + "th, td { padding: 5px; text-align: left; } "
                    + "</style>");
            out.println("<title>Add Customer Order Line</title>");     
            out.println("</head>");
            out.println("<body>");
            out.println("<form>");
            out.println("Order No:<br> <input type='text'  class='mytext' value='" + orderNo + "' disabled>");
            out.println("<br><br>");
            out.println("Customer ID:<br> <input type='text' name='custid' value='" + cid + "' class='mytext'disabled>");
            out.println("<br><br>");
            out.println("Customer Name:<br> <input type='text' name='custname' value='" + cname + "' class='mytext'disabled>");
            out.println("<br><br>");
            out.println("Amount:<br> <input type='text' name='amount' value='" + amount + "' class='mytext' disabled><br>");
            out.println("<br><br>");
            out.println("Due Date:<br> <input type='text' name='date' value='" + duedate + "' class='mytext' disabled><br>");
            out.println("<br><br>");
            out.println("Comments:<br> <textarea name='comment' rows='4' cols='35' disabled>" + comments + "</textarea><br>");
            out.println("<br><br><br>");
            out.println("Line No:<br> <input type='text' name='lineno' class='mytext'>");
            out.println("<br><br>");
            out.println("Part No:<br> <input type='text' name='partno' class='mytext'>");
            out.println("<br><br>");
            out.println("Amount:<br> <input type='text' name='amount' value='0.0' class='mytext' disabled><br>");
            out.println("<br><br>");
            out.println("<input type='submit' value='Add New Customer Order Line' class='mytext'><br>");
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
