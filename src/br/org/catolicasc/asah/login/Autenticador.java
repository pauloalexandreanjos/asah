package br.org.catolicasc.asah.login;

import br.org.catolicasc.asah.dao.JpaDaoFactory;
import br.org.catolicasc.asah.dao.UsuarioDao;
import br.org.catolicasc.asah.model.Usuario;

public class Autenticador {

	private static UsuarioDao usuarioDao = JpaDaoFactory.getInstance().getUsuarioDao();
	
	public static boolean autenticarUsuario(Usuario usuario) {
	
		Usuario user = usuarioDao.findByLogin(usuario.getLogin());
		
		if (user == null) {
            return false;
        }
		
		if (usuario.getSenha().equals(user.getSenha())) {
            usuario.copyFrom(user);
            return true;
        }
        return false;
	}
}
