package br.org.catolicasc.asah.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.org.catolicasc.asah.model.Parcela;

public class ParcelaDao extends JpaDaoBase<Parcela>{

	
	public List<Parcela> listarParcelasGasto(Long gasto) {
		
		TypedQuery<Parcela> query = em.createNamedQuery("Parcela.listarParcelasGasto", Parcela.class);
		query.setParameter("gasto", gasto);
		return query.getResultList();
	} 
	
}