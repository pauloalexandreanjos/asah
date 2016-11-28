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
import br.org.catolicasc.asah.dao.SonhoDao;
import br.org.catolicasc.asah.model.Sonho;
import br.org.catolicasc.asah.model.exceptions.SonhoJaExisteException;
import br.org.catolicasc.asah.model.rest.Sonhos;

@Path("/sonhos")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class SonhoService {

	private SonhoDao sonhoDao = JpaDaoFactory.getInstance().getSonhoDao();
	
	private static final int TAMANHO_PAGINA = 10;
	
	@GET
	@Path("{id}")
	public Sonho encontreSonho(@PathParam("id") Long id) {
		Sonho sonho = sonhoDao.buscaPorld(id);
		if (sonho!= null)
			return sonho;
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	public Sonhos listeTodosOsSonhos(@QueryParam("pagina") int pagina) {
		List<Sonho> movimentacoes = sonhoDao.listaPaginada(pagina,
				TAMANHO_PAGINA);
		return new Sonhos(movimentacoes);
	}
	
	@POST
	public Response criarSonhos(Sonho sonho) {
		
		try {
			sonhoDao.salva(sonho);
			
		} catch (SonhoJaExisteException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}
		
		URI uri = UriBuilder.fromPath("sonhos/{id}").build(sonho.getId());

		return Response.created(uri).entity(sonho).build();	
	}
	
	@PUT
	@Path("{id}")
	public void atualizarSonho(@PathParam("id") Long id, Sonho sonho) {
		encontreSonho(id);
		sonhoDao.atualiza(sonho);
	}

	@DELETE
	@Path("{id}")
	public void apagarSonho(@PathParam("id") Long id) {
		sonhoDao.remove(encontreSonho(id));
	}
}