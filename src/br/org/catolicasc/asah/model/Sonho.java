package br.org.catolicasc.asah.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.org.catolicasc.asah.adapter.DateAdapter;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sonho implements IBean {

	@Id
	@GeneratedValue
	private Long  id;
	private Float valor;
	private String descricao;
	
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date previsao;
	
	@OneToOne
	private Meta meta;

	public Sonho() {
		super();
	}

	public Sonho(Float valor, String descricao, Date previsao, Meta meta) {
		super();
		this.valor = valor;
		this.descricao = descricao;
		this.previsao = previsao;
		this.meta = meta;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getPrevisao() {
		return previsao;
	}

	public void setPrevisao(Date previsao) {
		this.previsao = previsao;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
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
