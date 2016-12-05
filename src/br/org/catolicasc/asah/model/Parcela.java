package br.org.catolicasc.asah.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.org.catolicasc.asah.adapter.DateAdapter;

@NamedQuery(name="Parcela.listarParcelasGasto", query="select p from Parcela p where p.gasto.id = :gasto")

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Parcela implements IBean{

	@Id
	@GeneratedValue
	private Long id;
	private float valor;
	
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date data;
	
	@ManyToOne
	private Gasto gasto;
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	public float getValor() {
		return this.valor;
	}
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	public Gasto getGasto() {
		return gasto;
	}
	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}
	
}