package br.org.catolicasc.asah.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public abstract class AbstractBean implements IBean{

	@Id
	@GeneratedValue
	private Long id;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
