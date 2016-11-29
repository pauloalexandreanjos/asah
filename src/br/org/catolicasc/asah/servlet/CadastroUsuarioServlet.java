package br.org.catolicasc.asah.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.catolicasc.asah.dao.JpaDaoFactory;
import br.org.catolicasc.asah.dao.UsuarioDao;
import br.org.catolicasc.asah.model.Usuario;

@WebServlet("/novousuario")
public class CadastroUsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public CadastroUsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("novousuario.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("user");
		String senha = request.getParameter("pass");
		String nome = request.getParameter("nome");
		
		UsuarioDao usuarioDao = JpaDaoFactory.getInstance().getUsuarioDao();
		
		Usuario user = usuarioDao.findByLogin(login);
		
		if (user != null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		user = new Usuario(nome,login,senha);
		
		usuarioDao.salva(user);
		
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.sendRedirect("index.html");
	}
}