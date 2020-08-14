
package hieupt.controllers;

import hieupt.dtos.DeviceDTO;
import hieupt.models.DeviceDAO;
import hieupt.models.FeedbackDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SendResponseController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ViewFeedbackController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            FeedbackDAO dao = new FeedbackDAO();
            String feedbackID = request.getParameter("id");
            String repairFrom = request.getParameter("txtRepairFrom");
            String responseContent = request.getParameter("txtResponse");
            String user = (String) request.getSession().getAttribute("USER");
            String deviceID = request.getParameter("txtDeviceID");
            String sender = request.getParameter("txtSender");
            DeviceDTO dto = new DeviceDAO().findByPrimaryKey(deviceID);
            if(dao.finish(feedbackID,user, responseContent, repairFrom, dto.getNumberOfCorrections(), dto.getDeviceID(), sender)){
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Reponse Failed");
            }
        } catch (Exception e) {
            log("ERROR at SendResponseController: " + e.getMessage());
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
