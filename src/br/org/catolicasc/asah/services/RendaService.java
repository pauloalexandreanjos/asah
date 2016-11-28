package br.org.catolicasc.asah.services;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import br.org.catolicasc.asah.dao.JpaDaoFactory;
import br.org.catolicasc.asah.dao.RendaDao;
import br.org.catolicasc.asah.model.Renda;
import br.org.catolicasc.asah.model.exceptions.RendaJaExisteException;
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
	
	@POST
	public Response criarRenda(Renda renda) {
		
		try {
			rendaDao.salva(renda);
		} catch (RendaJaExisteException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}
		
		URI uri = UriBuilder.fromPath("cervejas/{id}").build(renda.getId());

		return Response.created(uri).entity(renda).build();
	}
	
	@PUT
	@Path("{id}")
	public void atualizarCerveja(@PathParam("id") Long id, Renda renda) {
		encontreRenda(id);
		rendaDao.atualiza(renda);
	}

	@DELETE
	@Path("{id}")
	public void apagarCerveja(@PathParam("id") Long id) {
		rendaDao.remove(encontreRenda(id));
	}
}