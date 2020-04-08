package com.algaworks.ecommerce.mapeamentoavancado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class SalvandoArquivoTest extends EntityManagerTest{
	
	@Test
	public void salvarXmlNota() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		entityManager.getTransaction().begin();
		
		
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setXml(carregarNotaFiscal());
		notaFiscal.setPedido(pedido);
		
//		pedido.setNotaFiscal(notaFiscal);
		pedido.setStatus(StatusPedido.PAGO);

		entityManager.persist(notaFiscal);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		
		Assert.assertNotNull(pedidoVerificacao.getNotaFiscal());
		Assert.assertTrue(pedidoVerificacao.getNotaFiscal().getXml().length > 0);
	}
	
	@Test
	public void salvarFotoProduto() {
		Produto produto = entityManager.find(Produto.class, 1);
		
		entityManager.getTransaction().begin();
		
		produto.setFoto(carregarFoto());

		entityManager.getTransaction().commit();
		
		entityManager.clear();
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		
		Assert.assertNotNull(produtoVerificacao.getFoto());
//		Assert.assertTrue(produtoVerificacao.getFoto().length > 0);
		Assert.assertTrue(produtoVerificacao.getFoto().length > 0);
	}
	
	private static byte[] carregarFoto() {
		return carregarArquivo("/kindle3.jpg");
    }

    public static byte[] carregarNotaFiscal() {
        return carregarArquivo("/nota-fiscal.xml");
    }

    private static byte[] carregarArquivo(String nome) {
        try {
            return SalvandoArquivoTest.class.getResourceAsStream(nome).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
