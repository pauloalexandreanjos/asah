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
import br.org.catolicasc.asah.dao.MetaDao;
import br.org.catolicasc.asah.model.Meta;
import br.org.catolicasc.asah.model.exceptions.MetaJaExisteException;
import br.org.catolicasc.asah.model.rest.Metas;

@Path("/metas")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class MetaService {

	private MetaDao metaDao = JpaDaoFactory.getInstance().getMetaDao();
	
	private static final int TAMANHO_PAGINA = 10;
	
	@GET
	@Path("{id}")
	public Meta encontreMeta(@PathParam("id") Long id) {
		Meta meta = metaDao.buscaPorld(id);
		if (meta!= null)
			return meta;
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	public Metas listeTodasAsMetas(@QueryParam("pagina") int pagina) {
		List<Meta> metas = metaDao.listaPaginada(pagina,
				TAMANHO_PAGINA);
		return new Metas(metas);
	}
	
	@POST
	public Response criarMeta(Meta meta) {
		
		try {
			metaDao.salva(meta);
			
		} catch (MetaJaExisteException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}
		
		URI uri = UriBuilder.fromPath("metas/{id}").build(meta.getId());

		return Response.created(uri).entity(meta).build();	
	}
	
	@PUT
	@Path("{id}")
	public void atualizarMeta(@PathParam("id") Long id, Meta meta) {
		encontreMeta(id);
		meta.setId(id);
		metaDao.atualiza(meta);
	}

	@DELETE
	@Path("{id}")
	public void apagarMeta(@PathParam("id") Long id) {
		metaDao.remove(encontreMeta(id));
	}
}
