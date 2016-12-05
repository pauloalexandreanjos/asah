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
import br.org.catolicasc.asah.dao.ParcelaDao;
import br.org.catolicasc.asah.model.Parcela;
import br.org.catolicasc.asah.model.exceptions.ParcelaJaExisteException;
import br.org.catolicasc.asah.model.rest.Parcelas;

@Path("/parcelas")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class ParcelaService {

	private ParcelaDao parcelaDao = JpaDaoFactory.getInstance().getParcelaDao();
	
	private static final int TAMANHO_PAGINA = 10;
	
	@GET
	@Path("{id}")
	public Parcela encontreParcela(@PathParam("id") Long id) {
		Parcela parcela = parcelaDao.buscaPorld(id);
		if (parcela!= null)
			return parcela;
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	public Parcelas listeTodosAsParcelas(@QueryParam("pagina") int pagina) {
		List<Parcela> movimentacoes = parcelaDao.listaPaginada(pagina,
				TAMANHO_PAGINA);
		return new Parcelas(movimentacoes);
	}
	
	@GET
	@Path("gasto/{id}")
	public Parcelas listeParcelasGasto(@PathParam("id") Long gasto) {
		List<Parcela> movimentacoes = parcelaDao.listarParcelasGasto(gasto);
		return new Parcelas(movimentacoes);
	}
	
	@POST
	public Response criarParcela(Parcela parcela) {
		
		try {
			parcelaDao.salva(parcela);
			
		} catch (ParcelaJaExisteException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}
		
		URI uri = UriBuilder.fromPath("parcelas/{id}").build(parcela.getId());

		return Response.created(uri).entity(parcela).build();	
	}
	
	@PUT
	@Path("{id}")
	public void atualizarParcela(@PathParam("id") Long id, Parcela parcela) {
		encontreParcela(id);
		parcelaDao.atualiza(parcela);
	}

	@DELETE
	@Path("{id}")
	public void apagarParcela(@PathParam("id") Long id) {
		parcelaDao.remove(encontreParcela(id));
	}
}
