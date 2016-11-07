package br.org.catolicasc.asah.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario implements IBean{

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String login;
	private String senha; 
	
	public Usuario() {
		super();
	}

	public Usuario(String nome, String login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return this.senha;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}