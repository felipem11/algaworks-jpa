package com.algaworks.ecommerce.mapeamentobasico;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;

public class MapeandoEnumeracoes extends EntityManagerTest{
	
	@Test
	public void testarEnum() {
		Cliente cliente = new Cliente();
		cliente.setId(5);
		cliente.setNome("Chucky");
		cliente.setSexo(SexoCliente.MASCULINO);
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteValidacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteValidacao);
	}

}
