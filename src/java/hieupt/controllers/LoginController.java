
package hieupt.controllers;

import hieupt.dtos.RegistrationErrorObj;
import hieupt.models.FeedbackDAO;
import hieupt.models.RegistrationDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginController extends HttpServlet {
    private static final String ADMIN = "SearchNotiController";
    private static final String USER = "SearchUserNotificationController";
    private static final String STAFF = "SearchNotiController";
    private static final String INVALID = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID;
        try {
            new FeedbackDAO().checkExpired();
            
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            
            RegistrationErrorObj errorObj = new RegistrationErrorObj();
            boolean valid = true;
            if(username.length() == 0){
                errorObj.setUsernameError("Username can't be blank");
                valid = false;
            }
            if(password.length() == 0){
                errorObj.setPasswordError("Password can't be blank");
                valid = false;
            }
            if(valid){
                RegistrationDAO dao = new RegistrationDAO();
                String tmp = dao.checkLogin(username, password);
                String[] dto = tmp.split("[.]");
                String role = dto[0];
                if(role.equals("failed")){
                    request.setAttribute("ERROR", "Invalid Username or Password");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", username);
                    session.setAttribute("ROLE", role);
                    session.setAttribute("FULLNAME", dto[1]);
                    if(role.equalsIgnoreCase("admin"))
                        url = ADMIN;
                    else if(role.equalsIgnoreCase("user"))
                        url = USER;
                    else if(role.equalsIgnoreCase("staff"))
                        url = STAFF;
                    else request.setAttribute("ERROR", "Your role is invalid");
                }
            } else {
                request.setAttribute("INVALID", errorObj);
            }     
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
