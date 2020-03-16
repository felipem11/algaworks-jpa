package com.algaworks.ecommerce.iniciandocomjpa;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;

public class PrimeiroCrudTest extends EntityManagerTest{
	@Test
	public void create() {
		Cliente cliente = new Cliente();
		
		entityManager.getTransaction().begin();
//		cliente.setId(3);
		cliente.setNome("Freddy Krueger");
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		
		Assert.assertNotNull(clienteVerificacao);
	}
	
	@Test
	public void search() {
		Cliente cliente = entityManager.find(Cliente.class, 2);
		
		Assert.assertNotNull(cliente);
		Assert.assertEquals("Jack Torrance", cliente.getNome());
	}
	
	@Test
	public void update() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		entityManager.getTransaction().begin();
		cliente.setNome("Michael Myers, Halloween");
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.getReference(Cliente.class, 1);
		
		Assert.assertNotNull(clienteVerificacao);
		Assert.assertEquals(cliente.getNome(), clienteVerificacao.getNome());
	}
	
	@Test
	public void delete() {
		Cliente cliente = entityManager.find(Cliente.class, 4);
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		
		Assert.assertNull(entityManager.find(Cliente.class, 4));
		
	}

}
