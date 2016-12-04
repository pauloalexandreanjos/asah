package br.org.catolicasc.asah.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.asah.model.Renda;

@XmlRootElement
public class Rendas {

	private List<Renda> rendas = new ArrayList<>();

	public Rendas() {
	}
	
	public Rendas(List<Renda> rendas) {
		this.rendas = rendas;
	}
	
	@XmlTransient
	public List<Renda> getRendas() {
		return rendas;
	}
	
	public void setRendas(List<Renda> rendas) {
		this.rendas = rendas;
	}
	
	@XmlElement(name="link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (Renda renda : getRendas()) {
			
			Link link = Link.fromPath("rendas/{id}")
					.rel("renda")
					.title(renda.getId().toString())
					.build(renda.getId().toString());
			links.add(link);
		}
		return links;
	}
	
	public void setLinks (List<Link> links) {
		
	}
}
