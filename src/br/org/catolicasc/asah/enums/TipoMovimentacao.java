package br.org.catolicasc.asah.enums;

public enum TipoMovimentacao {

	POSITIVO(1),
	NEGATIVO(2);
	
	TipoMovimentacao(int id) {
		this.id = id;
	}
	
	int id;
}
