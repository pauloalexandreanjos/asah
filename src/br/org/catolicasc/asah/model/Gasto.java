package br.org.catolicasc.asah.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Gasto implements IBean {

	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	
	@OneToMany
	private List<Parcela> parcelas;
	private Date data;
	
	public void setDescricao(String login) {
		this.descricao = login;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
