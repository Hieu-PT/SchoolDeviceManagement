
package hieupt.controllers;

import hieupt.dtos.RegistrationDTO;
import hieupt.dtos.RegistrationErrorObj;
import hieupt.models.RegistrationDAO;
import hieupt.models.RoomDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateUserController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchUserController";
    private static final String INVALID = "updateUser.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String fullname = request.getParameter("txtFullname");
            String role = request.getParameter("txtRole");
            String workingRoom = request.getParameter("txtWorkingRoom");
            RegistrationDTO dto = new RegistrationDTO(username, fullname, role, workingRoom);
            RoomDAO roomDAO = new RoomDAO();
            RegistrationErrorObj errorObj = new RegistrationErrorObj();
            boolean valid = true;
            if(fullname.length() == 0){
                errorObj.setFullnameError("Fullname can't be blank");
                valid = false;
            }
            if(role.length() == 0){
                errorObj.setRoleError("Role can't be blank");
                valid = false;
            }
            if(roomDAO.findByPrimaryKey(workingRoom) == null){
                errorObj.setWorkingRoomError("Room is invalid or Not existed");
                valid = false;
            }
            if(valid){
                RegistrationDAO dao = new RegistrationDAO();
                if(dao.update(dto)){
                    url = SUCCESS;
                } else request.setAttribute("ERROR", "Update Failed");
            }else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
                request.setAttribute("DTO", dto);
            }
        } catch (Exception e) {
            log("ERROR at UpdateUserController: " + e.getMessage());
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
