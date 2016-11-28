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
import br.org.catolicasc.asah.dao.MovimentacaoDao;
import br.org.catolicasc.asah.model.Movimentacao;
import br.org.catolicasc.asah.model.exceptions.MovimentacaoJaExisteException;
import br.org.catolicasc.asah.model.rest.Movimentacoes;

@Path("/movimentacoes")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
		MediaType.APPLICATION_JSON })
public class MovimentacaoService {

	private MovimentacaoDao movimentacaoDao = JpaDaoFactory.getInstance().getMovimentacaoDao();
	
	private static final int TAMANHO_PAGINA = 10;
	
	@GET
	@Path("{id}")
	public Movimentacao encontreMovimentacao(@PathParam("id") Long id) {
		Movimentacao movimentacao = movimentacaoDao.buscaPorld(id);
		if (movimentacao!= null)
			return movimentacao;
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	public Movimentacoes listeTodosAsMovimentacoes(@QueryParam("pagina") int pagina) {
		List<Movimentacao> movimentacoes = movimentacaoDao.listaPaginada(pagina,
				TAMANHO_PAGINA);
		return new Movimentacoes(movimentacoes);
	}
	
	@POST
	public Response criarMovimentacao(Movimentacao movimencatao) {
		
		try {
			movimentacaoDao.salva(movimencatao);
			
		} catch (MovimentacaoJaExisteException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}
		
		URI uri = UriBuilder.fromPath("movimentacoes/{id}").build(movimencatao.getId());

		return Response.created(uri).entity(movimencatao).build();	
	}
	
	@PUT
	@Path("{id}")
	public void atualizarMovimentacao(@PathParam("id") Long id, Movimentacao movimentacao) {
		encontreMovimentacao(id);
		movimentacaoDao.atualiza(movimentacao);
	}

	@DELETE
	@Path("{id}")
	public void apagarMovimentacao(@PathParam("id") Long id) {
		movimentacaoDao.remove(encontreMovimentacao(id));
	}
}
