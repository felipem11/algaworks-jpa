package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class JoinTest extends EntityManagerTest{
	
	@Test
	public void usarJoinFetch() {
		String jpql = "select p from Pedido p "
				+ " left join fetch p.pagamento "
				+ " join fetch p.cliente "
				+ " left join fetch p.notaFiscal";
		
		TypedQuery<Pedido> typeQuery = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> listaPedido = typeQuery.getResultList();
		
		Assert.assertTrue(listaPedido.size() >= 3);
	}
	
	@Test
	public void fazerLeftJoin() {
		String jpql = "select p from Pedido p left join p.pagamento pag on pag.status = 'PROCESSANDO'";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> listaPedido = typedQuery.getResultList();
		
		Assert.assertFalse(listaPedido.isEmpty());
		
		listaPedido.forEach(p -> System.out.println(p.getId() + ", " + p.getStatus()));
	}
	
	@Test
	public void fazerJoin() {
		String jpql = "select p from Pedido p join p.pagamento pag";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> listaPedido = typedQuery.getResultList();
		
		Assert.assertNotNull(listaPedido.get(0).getPagamento());
		
		listaPedido.forEach(p -> System.out.println(p.getId() + ", " + p.getPagamento().getStatus()));
	}
	
	@Test
	public void fazerJoin2() {
		String jpql = "select p, i, prod from Pedido p join p.itens i join i.produto prod where p.status = 'AGUARDANDO'";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> listaPedido = typedQuery.getResultList();
		
		Assert.assertNotNull(listaPedido.size() == 3);
		
		listaPedido.forEach(p -> System.out.println(p[0].getClass().getSimpleName() + ", " + 
									p[1].getClass().getSimpleName() + ", " + 
									p[2].getClass().getSimpleName()));
	}

}
