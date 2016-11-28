package br.org.catolicasc.asah.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.org.catolicasc.asah.model.Meta;

@XmlRootElement
public class Metas {

	private List<Meta> metas = new ArrayList<>();

	public Metas() {
		super();
	}

	public Metas(List<Meta> metas) {
		super();
		this.metas = metas;
	}

	@XmlTransient
	public List<Meta> getMetas() {
		return metas;
	}

	public void setMetas(List<Meta> metas) {
		this.metas = metas;
	}

	@XmlElement(name="link")
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<>();
		for (Meta meta : getMetas()) {
			
			Link link = Link.fromPath("metas/{id}")
					.rel("meta")
					.title(meta.getDescricao())
					.build(meta.getDescricao());
			links.add(link);
		}
		return links;
	}
	
	public void setLinks (List<Link> links) {
		
	}
}
