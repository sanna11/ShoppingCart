/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.CustomerEntity;
import ejb.CustomerOrderEntity;
import ejb.CustomerOrderEntityFacade;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Sanna
 */
@WebServlet(name = "ListCustomerOrders", urlPatterns = {"/ListCustomerOrders"})
public class ListCustomerOrders extends HttpServlet {

    @EJB
    private CustomerOrderEntityFacade customerOrderEntityFacade;
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
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListCustomers</title>");
            out.println("<style> "
                    + "table, th, td {  border: 1px solid black;}"
                    + "th, td { padding: 5px; text-align: left; } "
                    + "</style>");
            out.println("<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>");
            out.println("<script>");
            out.println(" $(document).ready(function(){ "
                    + "$(\"#myTable td\").click(function() {"
                    + " var column_num = parseInt( $(this).index() ) + 1;"
                    + "var row_num = parseInt( $(this).parent().index() )+1; "
                    + "$(\"#result\").html( \"Row_num =\" + row_num + \"  ,  Rolumn_num =\"+ column_num );  "
                    + "});  "
                    + "});");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet ListCustomers at " + request.getContextPath() + "</h1>");
            List customerOrders = customerOrderEntityFacade.findAll();
            out.println("<table id=\"myTable\" border=\"1\" style=\"border-collapse: collapse;\" cellpadding=\"8\">");
            out.println("<tr><th></th><th>OrderNo</th><th>CustomerId</th><th>Name</th>"
                    + "<th>Amount</th><th>Due Date</th><th>Comments</th></tr>");
            for (Iterator it = customerOrders.iterator(); it.hasNext();) {
                CustomerOrderEntity elem = (CustomerOrderEntity) it.next();
                out.println("<tr>");
                out.println("<td><a href=\"/ShoppingCart_EAD-war/UpdateCustomer?id="+elem.getOrderNo()+"\">Update</a></td>");
                out.println("<td>"+elem.getCustomer().getCustomerId()+"</td>");
                out.println("<td>"+elem.getCustomer().getName()+"</td>");
                out.println("<td>"+elem.getPrice()+"</td>");
                out.println("<td>"+elem.getDuedate().toString()+"</td>");
                out.println("<td>"+elem.getComment()+"</td>");
////                out.println(" <b>" + elem.getCustomerId() + " </b><br />");
////                out.println(elem.getName() + "<br /> ");
            }
            out.println("</table>");
            out.println("<br><a href='AddCustomerOrder'>Add new Customer Order</a>");

            out.println("<br>");
            
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
