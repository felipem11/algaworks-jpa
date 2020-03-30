package com.algaworks.ecommerce.mapeamentoavancado;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class DatalhesColumnTest extends EntityManagerTest{
	
	@Test
	public void impedirInsercaoDaColunaAtualizacao() {
		Produto produto = new Produto();
		produto.setDescricao("Samsung J4");
		produto.setDataCriacao(LocalDateTime.now());
		produto.setPreco(new BigDecimal(540));
		produto.setDataCriacao(LocalDateTime.now());
		produto.setDataUltimaAtualizacao(LocalDateTime.now());
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setDataUltimaAtualizacao(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.persist(pedido);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		
		Assert.assertNotNull(produtoVerificacao.getDataCriacao());
		Assert.assertNull(produtoVerificacao.getDataUltimaAtualizacao());
		
		Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
		Assert.assertNull(pedidoVerificacao.getDataUltimaAtualizacao());
	}
	
	@Test
	public void impedirAtualizacaoDaColunaCriacao() {
		entityManager.getTransaction().begin();

		Produto produto = entityManager.find(Produto.class, 1);
		produto.setPreco(BigDecimal.TEN);
		produto.setDataCriacao(LocalDateTime.now());
		produto.setDataUltimaAtualizacao(LocalDateTime.now());
		
		Pedido pedido = entityManager.find(Pedido.class, 1);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setDataUltimaAtualizacao(LocalDateTime.now());
		
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		
		// Asserts de produtos
		Assert.assertNotEquals(produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS), 
							   produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
		
		Assert.assertEquals(produtoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS), 
							produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
		
		// Asserts de pedido
		Assert.assertNotEquals(pedidoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS), 
								pedido.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
		
		Assert.assertEquals(pedidoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS), 
							pedido.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
	}

}
