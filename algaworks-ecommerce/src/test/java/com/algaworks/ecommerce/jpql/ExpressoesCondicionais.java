package com.algaworks.ecommerce.jpql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;

public class ExpressoesCondicionais extends EntityManagerTest{
	
	@Test
	public void usarExpressaoIN() {
		Cliente cliente1 = entityManager.find(Cliente.class, 1);
		Cliente cliente2 = new Cliente();
		cliente2.setId(2);
		
//		List<Integer> pedidosIN = Arrays.asList(1, 3, 8);
		List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
		
//		String jpql = "select p from Pedido p where p.id in (:pedidos)";
		String jpql = "select p from Pedido p where p.cliente in (:clientes)";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		typedQuery.setParameter("clientes", clientes);
		
		List<Pedido> listaResultado = typedQuery.getResultList();
		
		Assert.assertFalse(listaResultado.isEmpty());
		
		listaResultado.forEach(p -> System.out.println(p.getId()));
		
	}
	
	@Test
	public void usarExpressaoCase() {
		String jpql = "select p.id, "
				+ " case p.status "
				+ " 	when 'PAGO' then 'Está Pago' "
				+ "		when 'CANCELADO' then 'Foi Cancelado' "
				+ "		else 'Está Aguardando' "
				+ " end, "
				+ " case type(p.pagamento) "
				+ "		when PagamentoBoleto then 'Pago com boleto' "
				+ "		when PagamentoCartao then 'Pago com cartão'"
				+ "		else 'Aguardando pagamento'"
				+ " end  from Pedido p left join p.pagamento pag";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> listaRetorno = typedQuery.getResultList();
		
		Assert.assertFalse(listaRetorno.isEmpty());
		
		listaRetorno.forEach(arr -> System.out.println(arr[0] + ", " + arr[1] + ", " + arr[2]));
	}
	
	@Test
	public void usarExpressaoCondicionalLike() {
//		String jpql = "select c from Cliente c where nome like :nome";
		String jpql = "select c from Cliente c where nome like concat(:nome, '%')";
		
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
		String nomeCliente = "P";
		typedQuery.setParameter("nome", nomeCliente);
		
		List<Cliente> listaClientes = typedQuery.getResultList();
		
		Assert.assertFalse(listaClientes.isEmpty());
		
		listaClientes.forEach(c -> System.out.println(c.getNome()));
	}
	
	@Test
	public void usarIsNull() {
		String jpql = "select p from Produto p where p.foto is null";
		TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
		
		List<Produto> listaProdutos = typedQuery.getResultList();
		
		Assert.assertFalse(listaProdutos.isEmpty());
		
		listaProdutos.forEach(p -> System.out.printf("Produto: %s\n", p.getNome()));
		
	}
	
	@Test
	public void usarIsEmpty() {
//		where
//        not (exists (select
//            categoria2_.id ...
		String jpql = "select p from Produto p where p.categorias is empty";
		TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
		
		List<Produto> listaProdutos = typedQuery.getResultList();
		
		Assert.assertFalse(listaProdutos.isEmpty());
		
		listaProdutos.forEach(p -> System.out.println(p.getNome()));
	}
	
	@Test
	public void usarMaiorMenorComData() {
		String jpql = "select p from Pedido p where data_criacao > :dataCriacao";
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		typedQuery.setParameter("dataCriacao", LocalDate.now().minusDays(3));
		
		List<Pedido> listaPedidos = typedQuery.getResultList();
		
		Assert.assertFalse(listaPedidos.isEmpty());
		
		listaPedidos.forEach(p -> System.out.println(p.getId() + " - " + p.getDataCriacao()));
		
	}
	
	@Test
	public void usarBetween() {
		String jpql = "select p from Produto p where p.preco between ?1 and ?2";
		
		TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
		typedQuery.setParameter(1, new BigDecimal(500));
		typedQuery.setParameter(2, new BigDecimal(500));
		
		List<Produto> listaProdutos = typedQuery.getResultList();
		
		Assert.assertNotNull(listaProdutos.isEmpty());
		
	}

}















