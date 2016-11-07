package br.org.catolicasc.asah.enums;

public enum Periodo {

	SEMANAL(1),
	QUINZENAL(2),
	MENSAL(3),
	BIMESTRAL(4),
	TRIMESTRAL(5),
	ANUAL(6);
	
	Periodo(int id) {
		this.id = id;
	}
	
	int id;
}
