package com.algaworks.ecommerce.mapeamentoavancado;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;

public class ElementCollectionTest extends EntityManagerTest{
	
	@Test
	public void aplicarTags() {
		Produto produto = entityManager.find(Produto.class, 1);
		
		entityManager.getTransaction().begin();
		
		produto.setTags(Arrays.asList("ebook","livro-digital"));
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificao = entityManager.find(Produto.class, produto.getId());	
		
		Assert.assertFalse(produtoVerificao.getTags().isEmpty());
		
	}
	
	@Test
	public void aplicarAtributos() {
		Produto produto = entityManager.find(Produto.class, 1);
		
		Atributo atributo = new Atributo();
		atributo.setNome("Tamanho Tela");
		atributo.setValor("5\"");
		entityManager.getTransaction().begin();
		
		produto.setAtributos(Arrays.asList(atributo, new Atributo("Altura","10cm")));
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificao = entityManager.find(Produto.class, produto.getId());	
		
		Assert.assertFalse(produtoVerificao.getAtributos().isEmpty());
		
	}
	
	@Test
	public void aplicarContato() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		entityManager.getTransaction().begin();
		cliente.setContatos(Collections.singletonMap("Mae", "1199995555"));
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Assert.assertEquals("1199995555", cliente.getContatos().get("Mae"));
		
	}

}
