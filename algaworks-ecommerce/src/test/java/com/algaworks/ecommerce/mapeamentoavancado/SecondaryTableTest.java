package com.algaworks.ecommerce.mapeamentoavancado;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;

public class SecondaryTableTest extends EntityManagerTest{
	@Test
	public void salvarCliente() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		cliente.setNome("Hannibal Lecter");
		cliente.setSexo(SexoCliente.MASCULINO);
		cliente.setDataNascimento(LocalDate.of(1938, 1, 30));
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		
		Assert.assertNotNull(clienteVerificacao.getSexo());
	}
}
