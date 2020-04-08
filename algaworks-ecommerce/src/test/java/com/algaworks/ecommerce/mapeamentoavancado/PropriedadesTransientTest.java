package com.algaworks.ecommerce.mapeamentoavancado;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;


public class PropriedadesTransientTest extends EntityManagerTest{
	
	@Test
	public void validarPrimeiroNome() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Assert.assertTrue(cliente.getPrimeiroNome().equals("Michael"));
	}

}
