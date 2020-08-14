
package hieupt.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    
    private static final String CREATEUSER = "CreateUserController";
    private static final String SEARCHUSER = "SearchUserController";
    private static final String DELETEUSER = "DeleteUserController";
    private static final String EDITUSER = "EditUserController";
    private static final String UPDATEUSER = "UpdateUserController";
    
    private static final String CREATEDEVICE = "CreateDeviceController";
    private static final String SEARCHDEVICE = "SearchDeviceController";
    private static final String DELETEDEVICE = "DeleteDeviceController";
    private static final String EDITDEVICE = "EditDeviceController";
    private static final String UPDATEDEVICE = "UpdateDeviceController";
    private static final String SEARCHPOSITIONHISTORY = "SearchPositionHistoryController";
    
    private static final String CREATEROOM = "CreateRoomController";
    private static final String SEARCHROOM = "SearchRoomController";
    private static final String DELETEROOM = "DeleteRoomController";
    private static final String EDITROOM = "EditRoomController";
    private static final String UPDATEROOM = "UpdateRoomController";
    private static final String FEEDBACK = "FeedbackController";
    private static final String SENDFEEDBACK = "SendFeedbackController";
    private static final String FIXEDHISTORY = "FixedHistoryController";
    private static final String MOVE = "MoveController";
    private static final String MOVEDEVICE = "MoveDeviceController";
    
    private static final String ACCEPT = "AcceptController";
    private static final String REJECT = "DenyController";
    private static final String FINISH = "FinishController";
    private static final String SENDRESPONSE = "SendResponseController";
    private static final String REGISTER = "RegisterController";
    
    private static final String CHANGEPASSWORD = "ChangePasswordController";
    private static final String SEARCHSATISTICAL = "SearchStatisticalController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if(action.equals("Login"))
                url = LOGIN;
            else if(action.equals("Create User"))
                url = CREATEUSER;
            else if(action.equals("Search User"))
                url = SEARCHUSER;
            else if(action.equals("DeleteUser"))
                url = DELETEUSER;
            else if(action.equals("Edit User"))
                url = EDITUSER;
            else if(action.equals("Update User"))
                url = UPDATEUSER;
            else if(action.equals("Create Device"))
                url = CREATEDEVICE;
            else if(action.equals("Search Device"))
                url = SEARCHDEVICE;
            else if(action.equals("DeleteDevice"))
                url = DELETEDEVICE;
            else if(action.equals("Edit Device"))
                url = EDITDEVICE;
            else if(action.equals("Update Device"))
                url = UPDATEDEVICE;
            else if(action.equals("Position History"))
                url = SEARCHPOSITIONHISTORY;
            else if(action.equals("Create Room"))
                url = CREATEROOM;
            else if(action.equals("Search Room"))
                url = SEARCHROOM;
            else if(action.equals("DeleteRoom"))
                url = DELETEROOM;
            else if(action.equals("Edit Room"))
                url = EDITROOM;
            else if(action.equals("Update Room"))
                url = UPDATEROOM;
            else if(action.equals("Feedback"))
                url = FEEDBACK;
            else if(action.equals("Send Feedback"))
                url = SENDFEEDBACK;
            else if(action.equals("Fixed History"))
                url = FIXEDHISTORY;
            else if(action.equals("Move"))
                url = MOVE;
            else if(action.equals("Move Device"))
                url = MOVEDEVICE;
            else if(action.equals("Accept"))
                url = ACCEPT;
            else if(action.equals("Finish"))
                url = FINISH;
            else if(action.equals("Send"))
                url = SENDRESPONSE;
            else if(action.equals("Register"))
                url = REGISTER;
            else if(action.equals("Reject"))
                url = REJECT;
            else if(action.equals("Change"))
                url = CHANGEPASSWORD;
            else if(action.equals("Statistical"))
                url = SEARCHSATISTICAL;
            else request.setAttribute("ERROR", "Your action is invalid");
        } catch (Exception e) {
            log("ERROR at MainController: " + e.getMessage());
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
