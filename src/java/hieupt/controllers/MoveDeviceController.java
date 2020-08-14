
package hieupt.controllers;

import hieupt.dtos.DeviceDTO;
import hieupt.dtos.DeviceMovingErrorObj;
import hieupt.models.DeviceDAO;
import hieupt.models.DevicePositionHistoryDAO;
import hieupt.models.RoomDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MoveDeviceController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchDeviceController";
    private static final String INVALID = "move.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String deviceID = request.getParameter("txtDeviceID");
            String moveTo = request.getParameter("txtMoveTo");
            String reason = request.getParameter("txtReason");
            String userMove = request.getParameter("txtUserMove");
            RoomDAO roomDAO = new RoomDAO();
            DeviceDAO deviceDAO = new DeviceDAO();
            DeviceDTO dto = deviceDAO.findByPrimaryKey(deviceID);
            DevicePositionHistoryDAO dpDAO = new DevicePositionHistoryDAO();
            String positionID = deviceDAO.getPositionID(deviceID);
            
            DeviceMovingErrorObj errorObj = new DeviceMovingErrorObj();
            boolean valid = true;
            
            if(moveTo.length() == 0){
                errorObj.setMoveToError("Room can't be blank");
                valid = false;
            }
            if(roomDAO.findByPrimaryKey(moveTo) == null){
                errorObj.setMoveToError("Room is not existed");
                valid = false;
            }
            if(reason.length() == 0){
                errorObj.setReasonError("Reason can't be blank");
                valid = false;
            }
            if(valid){
                if(dpDAO.update(positionID, userMove, reason, moveTo, deviceID)){
                    url = SUCCESS;
                } else{
                    request.setAttribute("ERROR", "Insert Failed");
                }
            }else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
                request.setAttribute("DTO", dto);
            }
        } catch (Exception e) {
            log("ERROR at MoveDeviceController: " + e.getMessage());
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
