
package hieupt.controllers;

import hieupt.dtos.DeviceDTO;
import hieupt.models.DeviceDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SearchDeviceController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("ROLE");
        String url = "viewDeviceUser.jsp";
        List<DeviceDTO> result = null;
        String username = (String) session.getAttribute("USER");
        if(role.equalsIgnoreCase("admin"))
            url = "viewDevice.jsp";
        else if(role.equalsIgnoreCase("staff"))
            url = "viewDeviceStaff.jsp";
        try {
            String search = request.getParameter("txtSearch");
            
            DeviceDAO dao = new DeviceDAO();
            if(role.equalsIgnoreCase("user")){
                result = dao.findByLikeNameUser(username, search);
            } else result = dao.findByLikeName(search);
            
            request.setAttribute("INFO", result);
        } catch (Exception e) {
            log("ERROR at SearchDeviceController: " + e.getMessage());
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
