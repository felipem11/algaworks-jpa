package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;

public class SubQueriesTest extends EntityManagerTest {
	@Test
	public void pesquisarComExists() {
		String jpql = "select p from Produto p where exists "
				+ "		(select 1 from ItemPedido i2 join i2.produto p2 where p2 = p)";
		
		TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
		List<Produto> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(p -> System.out.println("Produto: " + p.getId() + ", " + p.getNome()));
	}
	
	@Test
	public void pesquisarComIN() {
		String jpql = "select p from Pedido p where p.id in "
				+ "		(select p2.id from Pedido p2 "
				+ "		join p2.itens i2 join i2.produto prod2 where prod2.preco > 100)";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(p -> System.out.println("Pedidos: " + p.getId()));
	}
	
	@Test
	public void pesquisarComINExercicio() {
		String jpql = "select p from Pedido p join p.itens i join i.produto prod "
				+ " where prod.id in "
				+ "		(select prod2.id from Produto prod2 join prod2.categorias cat "
				+ "where cat.id = 1)";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		List<Pedido> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(p -> System.out.println("Pedidos: " + p.getId()));
	}
	
	@Test
	public void pesquisarSubQuery() {
//		Bons clientes, compraram mais 400 na loja
		String jpql1 = "select c from Cliente c where "
				+ " 100 < (select sum(total) from Pedido p where p.cliente = c )";
		TypedQuery<Cliente> typedQuery1 = entityManager.createQuery(jpql1, Cliente.class);
		List<Cliente> lista1 = typedQuery1.getResultList();
		Assert.assertFalse(lista1.isEmpty());

		
//		Bons clientes, compraram mais 400 na loja
		String jpql2 = "select c from Cliente c where "
				+ " 100 < (select sum(total) from c.pedidos)";
		TypedQuery<Cliente> typedQuery2 = entityManager.createQuery(jpql2, Cliente.class);
		List<Cliente> lista2 = typedQuery2.getResultList();
		Assert.assertFalse(lista2.isEmpty());
		
		
//		Todos pedidos acima da media de vendas
		String jpql3 = "select p from Pedido p where "
				+ " total > (select avg(total) from Pedido)";
		TypedQuery<Pedido> typedQuery3 = entityManager.createQuery(jpql3, Pedido.class);
		List<Pedido> lista3 = typedQuery3.getResultList();
		Assert.assertFalse(lista3.isEmpty());

		
		//		o produto ou os produtos mais baratos
		String jpql4 = "select p from Produto p where "
					+ " p.preco = (select min(preco) from Produto) ";
		TypedQuery<Produto> typedQuery4 = entityManager.createQuery(jpql4, Produto.class);
		List<Produto> lista4 = typedQuery4.getResultList();
		Assert.assertFalse(lista4.isEmpty());
		
		System.out.println("\nlista1");
		lista1.forEach(c -> System.out.println("lista1: " + c.getId() + ", " + c.getNome()));
		System.out.println("\nlista2");
		lista2.forEach(c -> System.out.println("lista2: " + c.getId() + ", " + c.getNome()));
		System.out.println("\nlista3");
		lista3.forEach(p -> System.out.println("lista3: " + p.getId() + ", " + p.getTotal()));
		System.out.println("\nlista4");
		lista4.forEach(p -> System.out.println("lista4: " + p.getId() + ", " + p.getPreco()));
		
	}

}
