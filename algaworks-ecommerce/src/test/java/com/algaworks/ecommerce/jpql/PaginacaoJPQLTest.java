package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

public class PaginacaoJPQLTest extends EntityManagerTest {
	
	@Test
	public void paginarResults() {
		String jpql = "select c from Categoria c order by c.nome";
		
		TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);
		
		// FIRST_RESULT = MAX_RESULTS * (pagina - 1)
		typedQuery.setFirstResult(4);
		typedQuery.setMaxResults(2);
		
		List<Categoria> listaCategoria = typedQuery.getResultList();
		
		Assert.assertFalse(listaCategoria.isEmpty());
		
		listaCategoria.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));
		
	}

}
