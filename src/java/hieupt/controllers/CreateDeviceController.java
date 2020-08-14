
package hieupt.controllers;

import hieupt.dtos.DeviceDTO;
import hieupt.dtos.DeviceErrorObj;
import hieupt.models.DeviceDAO;
import hieupt.models.RoomDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CreateDeviceController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchNotiController";
    private static final String INVALID = "createDevice.jsp"; 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String deviceID = request.getParameter("txtDeviceID");
            String deviceName = request.getParameter("txtDeviceName");
            String deviceDescription = request.getParameter("txtDeviceDescription");
            String deviceType = request.getParameter("txtDeviceType");
            String room = request.getParameter("txtRoom");
            String deviceImage = request.getParameter("txtDeviceImage");
            String dateOfPurchase = request.getParameter("txtDateOfPurchase");
            String warrantyPeriod = request.getParameter("txtWarrantyPeriod");
            String deviceStatus = request.getParameter("txtDeviceStatus");
            RoomDAO roomDAO = new RoomDAO();
            DeviceDAO dao = new DeviceDAO();
            
            DeviceErrorObj errorObj = new DeviceErrorObj();
            boolean valid = true;
            if(deviceID.length() == 0){
                errorObj.setDeviceIDError("Device ID can't be blank");
                valid = false;
            }
            if(dao.findByPrimaryKey(deviceID) != null){
                errorObj.setDeviceIDError("Device is existed");
                valid = false;
            }
            if(deviceName.length() == 0 ){
                errorObj.setDeviceNameError("Name can't be blank");
                valid = false;
            }
            if(deviceType.length() == 0){
                errorObj.setDeviceTypeError("Type can't be blank");
                valid = false;
            }
            if(roomDAO.findByPrimaryKey(room) == null){
                errorObj.setRoomError("Room is invalid or Not existed");
                valid = false;
            }
            if(deviceImage.length() == 0){
                deviceImage = "img/demo.jpg";
            }
            try {
                if(dao.validate(dateOfPurchase) == false){
                    errorObj.setDateOfPurchaseError("Invalid format mm/dd/YYYY");
                    valid = false;
                }
            } catch (Exception e) {
                errorObj.setDateOfPurchaseError("Invalid format mm/dd/YYYY");
                valid = false;
            }
            try {
                if(dao.validate(warrantyPeriod) == false){
                    errorObj.setWarrantyPeriodError("Invalid format mm/dd/YYYY");
                    valid = false;
                }
            } catch (Exception e) {
                errorObj.setWarrantyPeriodError("Invalid format mm/dd/YYYY");
                valid = false;
            }
            if(deviceStatus.length() == 0){
                errorObj.setDeviceStatusError("Status can't be blank");
                valid = false;
            }
            DeviceDTO dto = new DeviceDTO(deviceID, deviceName, deviceDescription, deviceType, room, deviceImage, deviceStatus, dateOfPurchase, warrantyPeriod);
            if(valid){
                if(dao.insert(dto)){
                    url = SUCCESS;
                } else{
                    request.setAttribute("ERROR", "Insert Failed");
                }
            }else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("ERROR at CreateDeviceController: " + e.getMessage());
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
