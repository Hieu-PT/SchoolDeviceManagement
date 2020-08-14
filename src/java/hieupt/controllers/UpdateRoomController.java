
package hieupt.controllers;

import hieupt.dtos.RoomDTO;
import hieupt.dtos.RoomErrorObj;
import hieupt.models.RoomDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateRoomController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchRoomController";
    private static final String INVALID = "updateRoom.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String room = request.getParameter("txtRoom");
            String floor = request.getParameter("txtFloor");
            
            RoomDAO dao = new RoomDAO();
            RoomDTO dto = new RoomDTO(room, floor);
            RoomErrorObj errorObj = new RoomErrorObj();
            boolean valid = true;
            if(floor.length() == 0 ){
                errorObj.setFloorError("Floor can't be blank");
                valid = false;
            }
            if(valid){
                if(dao.update(dto)){
                    url = SUCCESS;
                } else{
                    request.setAttribute("ERROR", "Update Failed");
                }
            }else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
                request.setAttribute("DTO", dto);
            }
        } catch (Exception e) {
            log("ERROR at UpdateRoomController: " + e.getMessage());
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
