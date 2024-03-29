package com.algaworks.ecommerce.mapeamentoavancado;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class MapsidTest extends EntityManagerTest{
	
	@Test
	public void inserirPagamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setPedido(pedido);
		notaFiscal.setXml(SalvandoArquivoTest.carregarNotaFiscal());
		notaFiscal.setDataEmissao(new Date());
		
		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		
		Assert.assertNotNull(notaFiscalVerificacao);
		Assert.assertEquals(notaFiscalVerificacao.getId(), pedido.getId());
	}
	
	@Test
	public void inserirItemPedido() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId());
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(1);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		ItemPedido itemPedidoverificacao = entityManager.find(
				ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
		
		Assert.assertNotNull(itemPedidoverificacao);

		Assert.assertEquals(itemPedidoverificacao.getId().getPedidoId(), pedido.getId());
		Assert.assertEquals(itemPedidoverificacao.getId().getProdutoId(), produto.getId());
		
		
				
	}

}
