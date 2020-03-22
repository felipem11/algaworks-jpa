package com.algaworks.ecommerce.relacionamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class AutoRelacionamentoTest extends EntityManagerTest{
	
	@Test
	public void verificarAutoRelacionamento() {
		Categoria categoriaPai = new Categoria();
		categoriaPai.setNome("Eletronicos");
		
		Categoria categoria = new Categoria();
		categoria.setNome("Smatphone");
		categoria.setCategoriaPai(categoriaPai);
		
		entityManager.getTransaction().begin();
		entityManager.persist(categoriaPai);
		entityManager.persist(categoria);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
		Assert.assertNotNull(categoriaVerificacao.getCategoriaPai());

		Categoria categoriaPaiVerificacao = entityManager.find(Categoria.class, categoriaPai.getId());
		Assert.assertFalse(categoriaPaiVerificacao.getCategorias().isEmpty());
		
	}
	

}
