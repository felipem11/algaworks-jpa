package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;

public class BasicoJPQLTest extends EntityManagerTest{
	@Test
	public void projetarNoDTO() {
		String jpql = "select new com.algaworks.ecommerce.dto.ProdutoDTO(id, nome) from Produto";
		
		TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(jpql, ProdutoDTO.class);
		List<ProdutoDTO> listaProduto = typedQuery.getResultList();
		
		Assert.assertFalse(listaProduto.isEmpty());
		
		listaProduto.forEach(p -> System.out.println(p.getId() + ", " + p.getNome()));
	}
	@Test
	public void projetarOResultado() {
		String jpql = "select id, nome from Produto";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		List<Object[]> listaProduto = typedQuery.getResultList();
		
		Assert.assertFalse(listaProduto.isEmpty());
		
		listaProduto.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
	}
	@Test
	public void selecionarUmAtributoParaRetorno() {
		String jpql = "select p.nome from Produto p";
		
		TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
		List<String> listaProdutos = typedQuery.getResultList();
		Assert.assertTrue(String.class.equals(listaProdutos.get(0).getClass()));
		
		
		String jpqlCliente = "select p.cliente from Pedido p";
		
		TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);
		List<Cliente> listaCliente = typedQueryCliente.getResultList();
		
		Assert.assertFalse(listaCliente.isEmpty());
		
	}
	@Test
	public void mostrarDiferencaQueries() {
		String jpql = "select p from Pedido p where p.id = 1";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		Pedido pedido1 = typedQuery.getSingleResult();
		Assert.assertNotNull(pedido1);
		
		Query query = entityManager.createQuery(jpql);
		Pedido pedido2 = (Pedido) query.getSingleResult();
		Assert.assertNotNull(pedido2);
		
//		List<Pedido> listaPedidos2 = query.getResultList();
//		Assert.assertFalse(listaPedidos2.isEmpty());
		
	}

}
