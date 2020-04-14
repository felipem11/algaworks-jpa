package com.algaworks.ecommerce.jpql;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;

public class PassandoParametrosTest extends EntityManagerTest{
	
	@Test
	public void passarParametro() {
		String jpql = "select p from Pedido p join fetch p.pagamento pag "
					+ "where p.id = ?1 and pag.status = :status";
		int idPedido = 1;
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		typedQuery.setParameter(1, idPedido);
		typedQuery.setParameter("status" , StatusPagamento.PROCESSANDO);
		
		Pedido pedido = typedQuery.getSingleResult();
		
		Assert.assertNotNull(pedido);
	}
	
	@Test
	public void passarParametroDate() {
		String jpql = "select nf from NotaFiscal nf where nf.dataEmissao <= ?1";
		
		TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
		typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);
		
		List<NotaFiscal> notaFiscal = typedQuery.getResultList();
		
		Assert.assertFalse(notaFiscal.isEmpty());
		
	}

}
