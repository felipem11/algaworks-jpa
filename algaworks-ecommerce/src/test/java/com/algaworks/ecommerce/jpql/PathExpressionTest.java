package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class PathExpressionTest extends EntityManagerTest{
	
	@Test
	public void buscarPedidosComProdutosEspecificos() {
		String jpql = "select p from Pedido p join p.itens i where i.id.produtoId = 1";
//      String jpql = "select p from Pedido p join p.itens i where i.produto.id = 1";
//      String jpql = "select p from Pedido p join p.itens i join i.produto pro where pro.id = 1";
		
		TypedQuery<Pedido> typeQuery = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> listaPedido = typeQuery.getResultList();
		
		Assert.assertFalse(listaPedido.isEmpty());
	}
	@Test
	public void usarPathExpression() {
		String jpql = "select p.cliente.nome from Pedido p";
		
		TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
		
		List<String> listaPedido = typedQuery.getResultList();
		
		Assert.assertFalse(listaPedido.isEmpty());
	}

}
