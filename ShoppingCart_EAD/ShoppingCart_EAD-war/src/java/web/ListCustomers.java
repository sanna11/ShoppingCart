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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ListCustomers", urlPatterns = {"/ListCustomers"})
public class ListCustomers extends HttpServlet {

    @EJB
    private SessionManagerBean sessionManagerBean;
    @EJB
    private CustomerEntityFacade customerEntityFacade;

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListCustomers</title>");
            out.println("<style> "
                    + "table, th, td {  border: 1px solid black;}"
                    + "th, td { padding: 5px; text-align: left; }"
                    + "</style>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet ListCustomers at " + request.getContextPath() + "</h1>");
            List customers = customerEntityFacade.findAll();
            out.println("<table style=\"width:100%\">");
            out.println("<tr><th></th><th></th><th>CustomerId</th>"
                        + "<th>Name</th><th>Address</th><th>Conatct</th></tr>");
            for (Iterator it = customers.iterator(); it.hasNext();) {
                CustomerEntity elem = (CustomerEntity) it.next();
                out.println("<tr>");
                out.println("<td><a href=\"\">Update</a></td>");
                out.println("<td><a href=\"\">Remove</a></td>");
                out.println("<td>"+elem.getCustomerId()+"</td>");
                out.println("<td>"+elem.getName()+"</td>");
                out.println("<td>"+elem.getAddress()+"</td>");
                out.println("<td>"+elem.getContact()+"</td>");
////                out.println(" <b>" + elem.getCustomerId() + " </b><br />");
////                out.println(elem.getName() + "<br /> ");
            }
            out.println("</table>");
            out.println("<br><a href='AddCustomer' onclick='myJavaScriptFunction();>Add new Customer</a>");

            out.println("<br>");
            out.println(sessionManagerBean.getActiveSessionsCount() + " user(s) reading the news.");
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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response,String message) {
        try {
            String manage = message;
            
            request.getSession().setAttribute("manage", manage); // add to session
            request.getRequestDispatcher("/AddCustomer").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ListCustomers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        //processRequest(request, response);
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
