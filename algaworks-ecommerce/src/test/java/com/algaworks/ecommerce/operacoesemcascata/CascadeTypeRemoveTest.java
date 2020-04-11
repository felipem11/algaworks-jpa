package com.algaworks.ecommerce.operacoesemcascata;

import javax.persistence.OneToMany;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;


public class CascadeTypeRemoveTest extends EntityManagerTest{
	@Test
	public void removerItensOrfaos() {
//		Quando --> @OneToMany(mappedBy = "pedido", orphanRemoval = true)
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		Assert.assertFalse(pedido.getItens().isEmpty());
		
		entityManager.getTransaction().begin();
		pedido.getItens().remove(0);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		
		Assert.assertTrue(pedidoVerificacao.getItens().isEmpty());
	}
	//	@Test
	public void removerRelacaoProdutoCategoria() {
		Produto produto = entityManager.find(Produto.class, 1);
		
		Assert.assertFalse(produto.getCategorias().isEmpty());
		
		entityManager.getTransaction().begin();
		produto.getCategorias().clear();
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		
		Assert.assertTrue(produtoVerificacao.getCategorias().isEmpty());
	}
//	@Test
	public void removerPedidoEitens() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		entityManager.getTransaction().begin();
		entityManager.remove(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		
		Assert.assertNull(pedidoVerificacao);
	}
	
//	@Test
	public void removerItemPedidoEPedido() {
		
		ItemPedido itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1, 1));
		
		entityManager.getTransaction().begin();
		entityManager.remove(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
		
		Assert.assertNull(itemPedidoVerificacao);
	}
	
	

}
