package br.org.catolicasc.asah.dao;

public class JpaDaoFactory {

	public static JpaDaoFactory instance = new JpaDaoFactory();
	
	private UsuarioDao usuarioDao;
	

	private JpaDaoFactory() {}
		
	public static JpaDaoFactory getInstance(){
		return instance;
	}
	
	public UsuarioDao getUsuarioDao(){
		if(this.usuarioDao == null)
			this.usuarioDao = new UsuarioDao();
		return this.usuarioDao;
	}

}
