package com.algaworks.ecommerce.relacionamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class RemovendoEntidadesRelacionadasTest extends EntityManagerTest{
	
	@Test
	public void verificarRelacionamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		Assert.assertFalse(pedido.getItens().isEmpty());
		
		entityManager.getTransaction().begin();
		pedido.getItens().forEach(i -> entityManager.remove(i));
		entityManager.remove(pedido);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNull(pedidoVerificacao);
		
		
		
		
	}
	

}
