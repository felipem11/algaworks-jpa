package com.algaworks.ecommerce.jpql;

import java.util.List;
import java.util.TimeZone;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class FuncoesTest extends EntityManagerTest {
	@Test
	public void aplicarFuncaoAgregacao() {
//		avg, count, min, max, sum
		String jpql = "select avg(p.total) from Pedido p";
		
		TypedQuery<Number> typedQuery = entityManager.createQuery(jpql, Number.class);
		List<Number> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		
		lista.forEach(System.out::println);
	}
	@Test
	public void aplicarFuncaoNativa() {
		String jpql = "select p from Pedido p where function('acima_media_faturamento', p.total) = 1";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		
		List<Pedido> listaPedidos = typedQuery.getResultList();
		Assert.assertFalse(listaPedidos.isEmpty());
	}
	@Test
	public void aplicarFuncaoColecao() {
		String jpql = "select p.id, size(p.itens) from Pedido p where size(p.itens) > 0 group by p.id";
		
		TypedQuery<Object[]> typeQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typeQuery.getResultList();
		Assert.assertNotNull(lista.isEmpty());
		
		lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
		
		/* 
		     select
		        pedido0_.id as col_0_0_,
		        count(itens1_.pedido_id) as col_1_0_ 
		    from
		        pedido pedido0_ cross 
		    join
		        item_pedido itens1_ 
		    where
		        pedido0_.id=itens1_.pedido_id 
		        and (
		            select
		                count(itens2_.pedido_id) 
		            from
		                item_pedido itens2_ 
		            where
		                pedido0_.id=itens2_.pedido_id
		        )>0 
		    group by
		        pedido0_.id 
		  */
	}
	
	@Test
	public void aplicarFuncaoNumerica() {
		String jpql = "select abs(-10), mod(3, 2), sqrt(9) from Pedido p";
//		10, 1, 3.0
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> listaObjetos = typedQuery.getResultList();
		
		Assert.assertFalse(listaObjetos.isEmpty());
		
		listaObjetos.forEach(arr -> System.out.println(arr[0] + ", " + arr[1] + ", " + arr[2] + ", "));
		
		/*
		ABS(-1) returns 1
		ABS(1) returns 1

		SQRT(number)
		Returns the square root of a number.
		SQRT(36) returns 6
		SQRT(1.44) returns 1.2

		MOD(int, int)
		Returns the remainder of the first argument divided by the second. Similar to Java % operator.
		MOD(3,2) returns 1

		SIZE(collection)
		Returns an integer of the number of elements in the given collection.
		SIZE (employee.phoneNumbers) returns the number of phone numbers of Employee entity.

		INDEX(identification_variable)
		Returns an integer value corresponding to the position of its argument in an ordered list. The INDEX function can only be applied to identification variables for which @OrderColumn (check out tutorial here) has been specified on the corresponding collection field/property.
		*/
	}
	
	@Test
	public void aplicarFuncaoString() {
//		concat, length, locate, substring, lower, upper, trim
		
//		String jpql = "select c.nome, concat('Prefix_', c.nome) from Categoria c";
//		Casa - Prefix_Casa
//		Cozinha - Prefix_Cozinha

//		String jpql = "select c.nome, length(c.nome) from Categoria c";
//		Casa - 4
//		Cozinha - 7
		String jpql = "select c.nome, length(c.nome) from Categoria c";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> listaCategoria = typedQuery.getResultList();
		
		Assert.assertFalse(listaCategoria.isEmpty());
		
		listaCategoria.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
	}
	
	@Test
	public void aplicarFuncaoData() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//		String jpql = "select current_date, current_time, current_timestamp from Pedido p";
//		2020-04-11, 13:30:00, 2020-04-12 13:30:00.0
//		String jpql = "select year(current_timestamp), year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao) from Pedido p";
		String jpql = "select hour(p.dataCriacao), minute(p.dataCriacao), second(p.dataCriacao) from Pedido p";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> listaPedidos = typedQuery.getResultList();
		
		Assert.assertFalse(listaPedidos.isEmpty());
		
		listaPedidos.forEach(arr -> System.out.println(arr[0] + ", " + arr[1] + ", " + arr[2]));
	}

}
