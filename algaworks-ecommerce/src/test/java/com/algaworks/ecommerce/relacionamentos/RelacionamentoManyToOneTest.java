package com.algaworks.ecommerce.relacionamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class RelacionamentoManyToOneTest extends EntityManagerTest{
	
	@Test
	public void verificarRelacionamento() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(BigDecimal.TEN);
		pedido.setCliente(cliente);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		
		Assert.assertNotNull(pedidoVerificacao.getCliente());
		
	}
	
	@Test
	public void verificarRelacionamentoItemPedido() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(BigDecimal.TEN);
		pedido.setCliente(cliente);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId());
		itemPedido.setPrecoProduto(new BigDecimal(500));
		itemPedido.setQuantidade(3);
//		itemPedido.setProdutoId(produto.getId());	//Idclas
//		itemPedido.setPedidoId(pedido.getId());		//IdClass
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		
		
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		ItemPedido itemPedidoVerificacao = entityManager.find(
				ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
		
		Assert.assertNotNull(pedidoVerificacao.getCliente());
		Assert.assertNotNull(itemPedidoVerificacao.getProduto());
		Assert.assertNotNull(itemPedidoVerificacao.getPedido());
		
	}

}
