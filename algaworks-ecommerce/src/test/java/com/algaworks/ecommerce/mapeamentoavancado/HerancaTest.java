package com.algaworks.ecommerce.mapeamentoavancado;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pagamento;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.SexoCliente;
import com.algaworks.ecommerce.model.StatusPagamento;

public class HerancaTest extends EntityManagerTest{
	
	@Test
	public void salvarCliente() {
		Cliente cliente = new Cliente();
		
		cliente.setNome("Carrie White");
		cliente.setDataNascimento(LocalDate.of(1974, 4, 6));
		cliente.setSexo(SexoCliente.FEMININO);
		cliente.setCpf("43504244070");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		
		Assert.assertNotNull(clienteVerificacao);
		
	}
	
	@Test
	public void incluirPagamentoPagamento() {
		Pedido pedido = entityManager.find(Pedido.class, 2);
		
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
	public void buscarPagamento() {
		@SuppressWarnings("unchecked")
		List<Pagamento> pagamentos = entityManager
				.createQuery("select p from Pagamento p")
				.getResultList();
		
		Assert.assertFalse(pagamentos.isEmpty());
	}

}
