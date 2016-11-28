package br.org.catolicasc.asah.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.asah.model.Sonho;

@XmlRootElement
public class Sonhos {

	private List<Sonho> sonhos = new ArrayList<>();

	public Sonhos() {
		super();
	}

	public Sonhos(List<Sonho> sonhos) {
		super();
		this.sonhos = sonhos;
	}

	@XmlTransient
	public List<Sonho> getSonhos() {
		return sonhos;
	}

	public void setSonhos(List<Sonho> sonhos) {
		this.sonhos = sonhos;
	}

	@XmlElement(name = "link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (Sonho sonhos : getSonhos()) {

			Link link = Link.fromPath("sonhos/{id}").rel("sonho").title(sonhos.getId().toString())
					.build(sonhos.getId().toString());
			links.add(link);
		}
		return links;
	}

	public void setLinks(List<Link> links) {
	}
}
