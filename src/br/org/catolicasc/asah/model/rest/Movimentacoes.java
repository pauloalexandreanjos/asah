package br.org.catolicasc.asah.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.asah.model.Movimentacao;

@XmlRootElement
public class Movimentacoes {

	private List<Movimentacao> movimentacoes = new ArrayList<>();

	public Movimentacoes() {
		super();
	}

	public Movimentacoes(List<Movimentacao> movimentacoes) {
		super();
		this.movimentacoes = movimentacoes;
	}

	@XmlTransient
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	@XmlElement(name="link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (Movimentacao movimentacao : getMovimentacoes()) {
			
			Link link = Link.fromPath("movimentacoes/{id}")
					.rel("movimentacao")
					.title(movimentacao.getId().toString())
					.build(movimentacao.getId().toString());
			links.add(link);
		}
		return links;
	}
	
	public void setLinks (List<Link> links) {
		
	}
}
