package br.org.catolicasc.asah.test;

import java.util.List;

import br.org.catolicasc.asah.dao.JpaDaoFactory;
import br.org.catolicasc.asah.dao.RendaDao;
import br.org.catolicasc.asah.model.Renda;

public class Teste {

	public static void main(String[] args) {
		
		RendaDao rendaDao = JpaDaoFactory.getInstance().getRendaDao();
		
		List<Renda> lst = rendaDao.listaPaginada(0, 10);
		
		for(Renda r : lst) {
			System.out.println("Id: "+r.getId());
			System.out.println("Valor: "+r.getValor());
		}	
	}
}
