package br.org.catolicasc.asah.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Gasto implements IBean {

	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	
	public void setDescricao(String login) {
		this.descricao = login;
	}

	public String getDescricao() {
		return descricao;
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
