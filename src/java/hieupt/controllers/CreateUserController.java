
package hieupt.controllers;

import hieupt.dtos.RegistrationDTO;
import hieupt.dtos.RegistrationErrorObj;
import hieupt.models.RegistrationDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CreateUserController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchNotiController";
    private static final String INVALID = "createUser.jsp"; 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String fullname = request.getParameter("txtFullname");
            String role = request.getParameter("txtRole");
            RegistrationDAO dao = new RegistrationDAO();
            
            RegistrationErrorObj errorObj = new RegistrationErrorObj();
            boolean valid = true;
            if(username.length() == 0){
                errorObj.setUsernameError("Username can't be blank");
                valid = false;
            }
            if(dao.findByPrimaryKey(username)!= null){
                errorObj.setUsernameError("Username is existed");
                valid = false;
            }
            if(password.length() == 0 ){
                errorObj.setPasswordError("Password can't be blank");
                valid = false;
            }
            if(confirm.equals(password)==false){
                errorObj.setConfirmError("Confirm must match Password");
                valid = false;
            }
            if(fullname.length() == 0){
                errorObj.setFullnameError("Fullname can't be blank");
                valid = false;
            }
            if((role.equalsIgnoreCase("user") == false) && (role.equalsIgnoreCase("staff") == false) && (role.equalsIgnoreCase("admin") == false)){
                errorObj.setRoleError("Your role is invalid");
                valid = false;
            }
            if(valid){
                if(dao.insert(new RegistrationDTO(username, password, fullname, role, ""))){
                    url = SUCCESS;
                } else{
                    request.setAttribute("ERROR", "Insert Failed");
                }
            }else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at CreateUserController: " + e.getMessage());
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
