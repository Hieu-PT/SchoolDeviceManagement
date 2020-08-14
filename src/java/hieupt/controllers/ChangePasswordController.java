
package hieupt.controllers;

import hieupt.dtos.RegistrationErrorObj;
import hieupt.models.RegistrationDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangePasswordController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static String SUCCESS = "SearchNotiController";
    private static String INVALID = "changePasswordAdmin.jsp"; 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String role = (String) request.getSession().getAttribute("ROLE");
        if(role.equalsIgnoreCase("User")){
            INVALID = "changePasswordUser.jsp";
            SUCCESS = "SearchUserNotificationController";
        }
        else if(role.equalsIgnoreCase("Staff"))
            INVALID = "changePasswordStaff.jsp";
        String url = ERROR;
        try {
            String oldPass = request.getParameter("txtOldPassword");
            String newPass = request.getParameter("txtNewPassword");
            String confirm = request.getParameter("txtConfirm");
            String id = (String) request.getSession().getAttribute("USER");
            RegistrationDAO dao = new RegistrationDAO();
            
            RegistrationErrorObj errorObj = new RegistrationErrorObj();
            boolean valid = true;
            
            if(oldPass.length() == 0 ){
                errorObj.setOldPassError("Password can't be blank");
                valid = false;
            }
            if(newPass.length() == 0 ){
                errorObj.setNewPassError("Password can't be blank");
                valid = false;
            }
            if(confirm.equals(newPass)==false){
                errorObj.setConfirmError("Confirm must match Password");
                valid = false;
            }
            if(valid){
                if(dao.changePassword(id, newPass)){
                    url = SUCCESS;
                } else{
                    request.setAttribute("ERROR", "Insert Failed");
                }
            }else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at ChangePasswordController: " + e.getMessage());
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
