package ma.projet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ma.projet.entity.Client;
import ma.projet.service.ClientService;
import ma.projet.service.UserService;
import ma.projet.entity.User;
import ma.projet.util.Utils;


@WebServlet(name = "Auth", urlPatterns = {"/auth"})
public class Auth extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserService us = new UserService();
        User u = us.getByEmail(email);
        ClientService cs = new ClientService(); 
        Client clt = cs.getByEmail(email);

        if (u == null) {
            request.setAttribute("msg", "Email or password incorrect");
            request.getRequestDispatcher("auth.jsp").forward(request, response);
        } else {
            if (clt != null) {
                if (clt.getPassword().equals(Utils.MD5(password))) {
                    // Mot de passe correct pour le client
                    HttpSession session = request.getSession();
                    session.setAttribute("client", clt);
                    request.setAttribute("userType", "client");
                    request.getRequestDispatcher("welcome.jsp").forward(request, response);
                } else {
                    // Mot de passe incorrect pour le client
                    request.setAttribute("userType", "client");
                    request.setAttribute("msg", "Le mot de passe est incorrect");
                    request.getRequestDispatcher("auth.jsp").forward(request, response);
                }
            } else {
                // C'est un employé
                if (u.getPassword().equals(Utils.MD5(password))) {
                    // Mot de passe correct pour l'employé
                    request.setAttribute("userType", "employee");
                    request.getRequestDispatcher("welcomeEmp.jsp").forward(request, response);
                } else {
                    // Mot de passe incorrect pour l'employé
                    request.setAttribute("userType", "employee");
                    request.setAttribute("msg", "Le mot de passe est incorrect");
                    request.getRequestDispatcher("auth.jsp").forward(request, response);
                }
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
        return "Authentication Servlet";
    }
}