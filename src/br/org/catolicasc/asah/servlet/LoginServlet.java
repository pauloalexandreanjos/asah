package br.org.catolicasc.asah.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.org.catolicasc.asah.login.Autenticador;
import br.org.catolicasc.asah.model.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String login = request.getParameter("user");
        String passwd = request.getParameter("pass");

        Usuario loginUser = new Usuario();
        loginUser.setLogin(login);
        loginUser.setSenha(passwd);

        if (Autenticador.autenticarUsuario(loginUser)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("id", loginUser.getId());
            session.setAttribute("userName", loginUser.getNome());
        } else {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
	}
}