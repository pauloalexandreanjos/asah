package br.org.catolicasc.asah.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.asah.model.Parcela;

@XmlRootElement
public class Parcelas {

	private List<Parcela> parcelas = new ArrayList<>();

	public Parcelas() {
		super();
	}

	public Parcelas(List<Parcela> parcelas) {
		super();
		this.parcelas = parcelas;
	}

	@XmlTransient
	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	@XmlElement(name = "link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (Parcela parcela : getParcelas()) {

			Link link = Link.fromPath("parcelas/{id}").rel("parcela").title(parcela.getId().toString())
					.build(parcela.getId().toString());
			links.add(link);
		}
		return links;
	}

	public void setLinks(List<Link> links) {

	}
}
