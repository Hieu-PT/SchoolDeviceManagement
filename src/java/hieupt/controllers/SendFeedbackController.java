
package hieupt.controllers;

import hieupt.dtos.FeedbackDTO;
import hieupt.dtos.FeedbackErrorObj;
import hieupt.models.FeedbackDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendFeedbackController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchDeviceController";
    private static final String INVALID = "feedback.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String deviceID = request.getParameter("txtDeviceID");
            String sender = request.getParameter("txtSender");
            String requiredContent = request.getParameter("txtRequiredContent");
            
            FeedbackDAO dao = new FeedbackDAO();
            FeedbackDTO dto = new FeedbackDTO(deviceID, sender, requiredContent);
            FeedbackErrorObj errorObj = new FeedbackErrorObj();
            boolean valid = true;
            if(requiredContent.length() == 0 ){
                errorObj.setRequiredContentError("Content can't be blank");
                valid = false;
            }
            if(valid){
                if(dao.insert(dto)){
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
            log("ERROR at SendFeedbackController: " + e.getMessage());
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
