package br.org.catolicasc.asah.model;

import br.org.catolicasc.asah.enums.TipoMovimentacao;

public class Movimentacao implements IBean{

	private Long id;
	private float valor;
	private TipoMovimentacao tipo;
	
	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
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
