package ma.projet.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ma.projet.entity.Client;
import ma.projet.service.ClientService;

@WebServlet(name = "ChangePwd", urlPatterns = {"/ChangePwd"})
public class ChangePwd extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newPassword = request.getParameter("password");
        String enteredEmail = (String) request.getSession().getAttribute("email");
        ClientService cs = new ClientService();
        List<Client> clients=cs.getAll();
        for (Client client:clients){
            if((enteredEmail).equals(client.getEmail())){
                client.setPassword(newPassword);
                cs.update(client);
                response.sendRedirect("auth.jsp");
                
            } 
        }
        
      
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ChangePwd Servlet";
    }
    
    
}
