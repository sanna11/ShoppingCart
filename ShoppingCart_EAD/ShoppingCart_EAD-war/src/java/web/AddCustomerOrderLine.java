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
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
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

    @Resource(mappedName = "jms/MessageFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/Message")
    private Queue queue;
    @EJB
    private CustomerOrderLineEntityFacade customerOrderLineEntityFacade;
    @EJB
    private CustomerOrderEntityFacade customerOrderEntityFacade;
    private CustomerOrderEntity customerOrder;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private CustomerOrderLineEntity updatingCustomerOrderLine = null;

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
        String action = request.getParameter("action");
        String lineNo = request.getParameter("lineno");
        String lno = null, pno = null, prce = null;
        if ( (action != null) &&( action.equalsIgnoreCase("update order line") || action.equalsIgnoreCase("delete order line"))) {

            List customerLineOrders = customerOrderLineEntityFacade.findAll();

            for (Object object : customerLineOrders) {
                CustomerOrderLineEntity custOrderLine = (CustomerOrderLineEntity) object;
                if (custOrderLine.getLineNo().equals(lineNo)) {
                    updatingCustomerOrderLine = custOrderLine;
                    break;
                }
                
            }
            if (updatingCustomerOrderLine == null) {
                    response.sendRedirect("ListCustomerOrders");
                }
            customerOrder = updatingCustomerOrderLine.getCustomerOrder();
            if (action.equalsIgnoreCase("update order line")) {

                lno = updatingCustomerOrderLine.getLineNo();
                pno = updatingCustomerOrderLine.getPartNo();
                prce = String.valueOf(updatingCustomerOrderLine.getAmount());
            } else  {
                customerOrder.setPrice(customerOrder.getPrice() - updatingCustomerOrderLine.getAmount());
                customerOrderEntityFacade.edit(customerOrder);
                customerOrderLineEntityFacade.remove(updatingCustomerOrderLine);
                response.sendRedirect("ViewCustomerOrder?orderno="+customerOrder.getOrderNo());
                
            }

        }
        if (orderNo != null) {
            List customerOrders = customerOrderEntityFacade.findAll();

            for (Object object : customerOrders) {
                CustomerOrderEntity custOrder = (CustomerOrderEntity) object;
                if (custOrder.getOrderNo().equals(orderNo)) {
                    customerOrder = custOrder;
                    break;
                }
            }
        }
        if (customerOrder == null) {
            response.sendRedirect("/ShoppingCart_EAD-war/ListCustomerOrders");
        }
        String cid, cname, duedate, amount, comments;

        cid = customerOrder.getCustomer().getCustomerId();
        cname = customerOrder.getCustomer().getName();
        duedate = dateFormat.format(customerOrder.getDuedate());
        System.out.println("date " + duedate);
        amount = String.valueOf(customerOrder.getPrice());
        comments = customerOrder.getComment();
        String lineno = request.getParameter("lineno");
        String partno = request.getParameter("partno");
        String price = request.getParameter("price");



        if ( (partno != null) && (price != null)) {

            if (action != null && action.equalsIgnoreCase("Update Customer Order Line")) {
                double fprce = customerOrder.getPrice() + Double.parseDouble(price) - updatingCustomerOrderLine.getAmount();
                customerOrder.setPrice(fprce);
                customerOrderEntityFacade.edit(customerOrder);
                updatingCustomerOrderLine.setCustomerOrder(customerOrder);

                updatingCustomerOrderLine.setPartNo(partno);
                updatingCustomerOrderLine.setAmount(Double.parseDouble(price));
                customerOrderLineEntityFacade.edit(updatingCustomerOrderLine);
            } else if (action.equalsIgnoreCase("Add New Customer Order Line")) {
                CustomerOrderLineEntity custOrderLine = new CustomerOrderLineEntity();
                customerOrder.setPrice(customerOrder.getPrice() + Double.parseDouble(price));
                customerOrderEntityFacade.edit(customerOrder);
                custOrderLine.setCustomerOrder(customerOrder);

                custOrderLine.setPartNo(partno);
                custOrderLine.setAmount(Double.parseDouble(price));

                custOrderLine.setLineNo(lineno);
                customerOrderLineEntityFacade.create(custOrderLine);
            }
            response.sendRedirect("ViewCustomerOrder?orderno=" + customerOrder.getOrderNo());
        }

        response.setContentType(
                "text/html;charset=UTF-8");
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
            if (action != null && action.equalsIgnoreCase("update order line")) {
                out.println("Line No:<br> <input type='text' name='lineno' value='"+lno+"' class='mytext' disabled>");
                out.println("<br><br>");
                out.println("Part No:<br> <input type='text' name='partno' value='"+pno+"' class='mytext' >");
                out.println("<br><br>");
                out.println("Amount:<br> <input type='text' name='price' value='"+prce+"' class='mytext' ><br>");
            }
            else {
            out.println("Line No:<br> <input type='text' name='lineno' class='mytext'>");
            out.println("<br><br>");
            out.println("Part No:<br> <input type='text' name='partno' class='mytext'>");
            out.println("<br><br>");
            out.println("Amount:<br> <input type='text' name='price' value='0.0' class='mytext' ><br>");
            }
            out.println("<br><br>");
            out.println("<input type='submit' name='action' value='Add New Customer Order Line' class='mytext'>  ");
            out.println("<input type='submit' name='action' value='Update Customer Order Line' class='mytext'><br>");
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
