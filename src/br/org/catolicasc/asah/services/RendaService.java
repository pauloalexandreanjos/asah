package br.org.catolicasc.asah.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import br.org.catolicasc.asah.dao.JpaDaoFactory;
import br.org.catolicasc.asah.dao.RendaDao;
import br.org.catolicasc.asah.model.Renda;
import br.org.catolicasc.asah.model.rest.Rendas;

@Path("/rendas")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class RendaService {

	private RendaDao rendaDao = JpaDaoFactory.getInstance().getRendaDao();

	private static final int TAMANHO_PAGINA = 10;

	@GET
	@Path("{id}")
	public Renda encontreRenda(@PathParam("id") Long idRenda) {
		Renda renda = rendaDao.buscaPorld(idRenda);
		if (renda != null)
			return renda;

		throw new WebApplicationException(Status.NOT_FOUND);

	}

	@GET
	public Rendas listeTodasAsRendas(@QueryParam("pagina") int pagina) {
		List<Renda> cervejas = rendaDao.listaPaginada(pagina,
				TAMANHO_PAGINA);
		return new Rendas(cervejas);
	}
	
}
