package com.algaworks.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;

public class GroupByTest extends EntityManagerTest {
	
	@Test
	public void condicionarAgrupamentoComHaving() {
		String jpql = "select cat.nome, sum(ip.precoProduto ) from ItemPedido ip "
				+ " join ip.produto prod join prod.categorias cat "
				+ " group by cat.id "
				+ " having sum(ip.precoProduto * ip.quantidade) > 0 ";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> listaRelatorio = typedQuery.getResultList();
		
		Assert.assertFalse(listaRelatorio.isEmpty());
		
		listaRelatorio.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
	}
	
	@Test
    public void agruparEFiltrarResultado() {
//         Total de vendas por mês.
//        String jpql = "select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) " +
//                " from Pedido p " +
//                " where year(p.dataCriacao) = year(current_date) " +
//                " group by year(p.dataCriacao), month(p.dataCriacao) ";

//         Total de vendas por categoria.
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
//                " join ip.produto pro join pro.categorias c join ip.pedido p " +
//                " where year(p.dataCriacao) = year(current_date) and month(p.dataCriacao) = month(current_date) " +
//                " group by c.id";

//        Total de vendas por cliente
        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
                " join ip.pedido p join p.cliente c join ip.pedido p " +
                " where year(p.dataCriacao) = year(current_date) and month(p.dataCriacao) >= (month(current_date) - 3) " +
                " group by c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }
	
	@Test
	public void agruparResultado() {
//		Quantidade de produtos por Categoria
//		String jpql = "select c.nome, count(p.id) from Categoria c join c.produtos p group by c.id";

//		Total de vendas por mês
//		String jpql = "select year(p.dataCriacao), month(p.dataCriacao), "
//				+ " function('monthname', p.dataCriacao), sum(p.total) "
//				+ " from Pedido p "
//				+ " group by year(p.dataCriacao), month(p.dataCriacao)";

		//		Total de vendas por categorias
//		String jpql = "select c.nome, sum(p.total) from Pedido p join p.itens i "
//					+ " join i.produto prod join prod.categorias c "
//					+ "group by c.id";
		
//		Total de vendas por Cliente
//		String jpql = "select c.nome, count(p.id) from Pedido p join p.cliente c "
//				+ " group by c.id";
		
//		Total de vendas por dia e por categoria
		String jpql = "select year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao), "
				+ " cat.nome, count(p.id), sum(p.total) from Pedido p join p.itens i "
				+ " join i.produto prod join prod.categorias cat "
				+ " group by year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao), "
				+ " cat.id ";
		
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		
		Assert.assertFalse(lista.isEmpty());
		lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1] + ", " + arr[2] + ", " + arr[3] + ", " + arr[4] + ", " + arr[5]));
//		lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1] + ", " + arr[2] + ", " + arr[3]));
//		lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1] ));

	}

}
