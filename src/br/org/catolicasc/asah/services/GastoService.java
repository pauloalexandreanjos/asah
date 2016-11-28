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

import br.org.catolicasc.asah.dao.GastoDao;
import br.org.catolicasc.asah.dao.JpaDaoFactory;
import br.org.catolicasc.asah.model.Gasto;
import br.org.catolicasc.asah.model.exceptions.GastoJaExisteException;
import br.org.catolicasc.asah.model.rest.Gastos;

@Path("/gastos")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class GastoService {

	private GastoDao gastoDao = JpaDaoFactory.getInstance().getGastoDao();
	
	private static final int TAMANHO_PAGINA = 10;
	
	@GET
	@Path("{id}")
	public Gasto encontreGasto(@PathParam("id") Long id) {
		Gasto gasto = gastoDao.buscaPorld(id);
		if (gasto!= null)
			return gasto;
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	public Gastos listeTodosOsGastos(@QueryParam("pagina") int pagina) {
		List<Gasto> gastos = gastoDao.listaPaginada(pagina,
				TAMANHO_PAGINA);
		return new Gastos(gastos);
	}
	
	@POST
	public Response criarGasto(Gasto gasto) {
		
		try {
			gastoDao.salva(gasto);
			
		} catch (GastoJaExisteException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}
		
		URI uri = UriBuilder.fromPath("gastos/{id}").build(gasto.getId());

		return Response.created(uri).entity(gasto).build();	
	}
	
	@PUT
	@Path("{id}")
	public void atualizarGasto(@PathParam("id") Long id, Gasto gasto) {
		gastoDao.atualiza(encontreGasto(id));
	}

	@DELETE
	@Path("{id}")
	public void apagarGasto(@PathParam("id") Long id) {
		gastoDao.remove(encontreGasto(id));
	}
}
