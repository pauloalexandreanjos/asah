package br.org.catolicasc.asah.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.org.catolicasc.asah.enums.Periodo;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Renda implements IBean{

	@Id
	@GeneratedValue
	private Long id;
	private float valor;
	private Periodo periodicidade;
	private String descricao;

	public Renda() {
	}
	
	public Renda(float valor) {
		this.valor = valor;
	}
	
	public Periodo getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(Periodo periodicidade) {
		this.periodicidade = periodicidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
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
