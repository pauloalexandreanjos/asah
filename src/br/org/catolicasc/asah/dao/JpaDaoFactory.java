package br.org.catolicasc.asah.dao;

public class JpaDaoFactory {

	public static JpaDaoFactory instance = new JpaDaoFactory();
	
	private UsuarioDao usuarioDao;
	private RendaDao rendaDao;
	private GastoDao gastoDao;
	private MetaDao metaDao;
	private ParcelaDao parcelaDao;
	private SonhoDao sonhoDao;

	private JpaDaoFactory() {}
		
	public static JpaDaoFactory getInstance(){
		return instance;
	}
	
	public UsuarioDao getUsuarioDao(){
		if(this.usuarioDao == null)
			this.usuarioDao = new UsuarioDao();
		return this.usuarioDao;
	}

	public RendaDao getRendaDao(){
		if(this.rendaDao == null)
			this.rendaDao = new RendaDao();
		return this.rendaDao;
	}
	
	public GastoDao getGastoDao(){
		if(this.gastoDao == null)
			this.gastoDao = new GastoDao();
		return this.gastoDao;
	}
	
	public MetaDao getMetaDao(){
		if(this.metaDao == null)
			this.metaDao = new MetaDao();
		return this.metaDao;
	}
	
	public ParcelaDao getParcelaDao(){
		if(this.parcelaDao == null)
			this.parcelaDao = new ParcelaDao();
		return this.parcelaDao;
	}
	
	public SonhoDao getSonhoDao(){
		if(this.sonhoDao == null)
			this.sonhoDao = new SonhoDao();
		return this.sonhoDao;
	}
}
