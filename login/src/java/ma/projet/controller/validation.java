package ma.projet.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "validation", urlPatterns = {"/validation"})
public class validation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enteredCode = request.getParameter("verificationCode");
        String generatedCode = (String) request.getSession().getAttribute("verificationCode");

        if (enteredCode != null && enteredCode.equals(generatedCode)) {
            // Les codes correspondent, redirigez vers la page "ChangePwd.jsp"
            response.sendRedirect("ChangePwd.jsp");
            
        } else {
            // Les codes ne correspondent pas, renvoyez un message d'erreur vers la page "forgotPassword.jsp"
            request.setAttribute("msg", "Code de validation incorrect.");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        }
    }
}
