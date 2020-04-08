package com.algaworks.ecommerce.relacionamentos;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.mapeamentoavancado.SalvandoArquivoTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;

public class RelacionamentoOneToOneTest extends EntityManagerTest{
	
	@Test
	public void verificarRelacionamentoPedidoPagamento() {
		Pedido pedido = entityManager.find(Pedido.class, 3);
		
		PagamentoCartao pagamentoCartao = new PagamentoCartao();
		pagamentoCartao.setNumeroCartao("1234567890");
		pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoCartao.setPedido(pedido);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pagamentoCartao);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificao = entityManager.find(Pedido.class, pedido.getId());
		
		Assert.assertNotNull(pedidoVerificao.getPagamento());
		
	}
	
	@Test
	public void verificarRelacionamentoPedidoNotaFiscal() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setXml(SalvandoArquivoTest.carregarNotaFiscal());
		notaFiscal.setPedido(pedido);
		
		entityManager.getTransaction().begin();
		entityManager.persist(notaFiscal);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificao = entityManager.find(Pedido.class, pedido.getId());
		
		Assert.assertNotNull(pedidoVerificao.getNotaFiscal());
		
	}
	

}
