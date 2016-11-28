package br.org.catolicasc.asah.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.asah.model.Gasto;

@XmlRootElement
public class Gastos {

	private List<Gasto> gastos = new ArrayList<>();

	public Gastos() {
		super();
	}

	public Gastos(List<Gasto> gastos) {
		super();
		this.gastos = gastos;
	}

	@XmlTransient
	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}
	
	@XmlElement(name="link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (Gasto gasto : getGastos()) {
			
			Link link = Link.fromPath("gastos/{id}")
					.rel("gasto")
					.title(gasto.getDescricao())
					.build(gasto.getDescricao());
			links.add(link);
		}
		return links;
	}
	
	public void setLinks (List<Link> links) {
		
	}
}
