package br.org.catolicasc.asah.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.org.catolicasc.asah.model.Usuario;

public class UsuarioDao extends JpaDaoBase<Usuario>{

	public Usuario findByLogin(String login) throws NoResultException{
		
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByLogin", Usuario.class);
		query.setParameter("login", login);
		return query.getSingleResult();
	}
}
