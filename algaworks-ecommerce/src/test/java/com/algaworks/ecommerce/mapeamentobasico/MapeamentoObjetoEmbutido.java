package com.algaworks.ecommerce.mapeamentobasico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class MapeamentoObjetoEmbutido extends EntityManagerTest{
	
	@Test
	public void analisarMapeamentoEmbutido() {
		EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
		endereco.setCep("01234-000");
		endereco.setBairro("bairro");
		endereco.setCidade("Sao Paulo");
		endereco.setEstado("SP");
		endereco.setNumero("123");
		endereco.setLogradouro("Alameda EverGreen");
		endereco.setComplemento("Casa 2");
		
		Pedido pedido = new Pedido();
		pedido.setId(1);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(new BigDecimal(1000));
		pedido.setEndereco(endereco);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificao = entityManager.find(Pedido.class, 1);
		
		Assert.assertNotNull(pedidoVerificao);
		Assert.assertNotNull(pedidoVerificao.getEndereco());
		Assert.assertNotNull(pedidoVerificao.getEndereco().getCep());
		
	}

}
