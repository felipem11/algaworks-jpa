package com.algaworks.ecommerce.relacionamentos;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Produto;

public class RelacionamentoManyToManyTest extends EntityManagerTest{
	
	@Test
	public void verificarRelacionamentoProdutoCategoria() {
		Categoria categoria1 = entityManager.find(Categoria.class, 1);
		Categoria categoria2 = entityManager.find(Categoria.class, 2);
		Categoria categoria3 = entityManager.find(Categoria.class, 3);
		
		Produto produto1 = entityManager.find(Produto.class, 1);
		Produto produto2 = entityManager.find(Produto.class, 2);
		
		entityManager.getTransaction().begin();
		produto1.setCategorias(Arrays.asList(categoria1, categoria2));
		produto2.setCategorias(Arrays.asList(categoria1, categoria2, categoria3));
		entityManager.persist(produto1);
		entityManager.persist(produto2);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto1.getId());
		Categoria categoriaverificao = entityManager.find(Categoria.class, categoria2.getId());
		
		Assert.assertTrue(produtoVerificacao.getCategorias().size() > 1);
		Assert.assertTrue(categoriaverificao.getProdutos().size() > 1);
		
		
	}
	

}
