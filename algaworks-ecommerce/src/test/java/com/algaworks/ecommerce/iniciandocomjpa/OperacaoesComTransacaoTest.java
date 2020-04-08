package com.algaworks.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacaoesComTransacaoTest extends EntityManagerTest{
	
	@Test
	public void impedirOperacaoComBancoDeDados() {
		Produto produto = entityManager.find(Produto.class, 1);
		
		entityManager.detach(produto); //remove o objeto produto da memoria do entityManager
										// deixando de ser gerenciado
		
		
		entityManager.getTransaction().begin();
		produto.setNome("Kindle PaperWhite 2ª geração"); // nao ira ter efeito 
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, 1);
		
		Assert.assertEquals("Kindle", produtoVerificacao.getNome());
	}
	
	@Test
	public void mostrarDiferencaoPersistMerge() {
		Produto produtoPersist = new Produto();

//		produtoPersist.setId(5);
		produtoPersist.setNome("Smartphone One Plus");
		produtoPersist.setDescricao("O processador mais rapido");
		produtoPersist.setPreco(new BigDecimal(2000));
		produtoPersist.setDataCriacao(LocalDateTime.now());
		
		entityManager.getTransaction().begin();
		entityManager.persist(produtoPersist);  // o Persist cria uma referencia ao objeto Gerenciado pelo JPA produtoPersist
		produtoPersist.setNome("Smartphone Two Plus");
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
		Assert.assertNotNull(produtoVerificacaoPersist);
		
		Produto produtoMerge = new Produto();

//		produtoMerge.setId(6);
		produtoMerge.setNome("Notebook Dell");
		produtoMerge.setDescricao("O melhor da categoria");
		produtoMerge.setPreco(new BigDecimal(2800));
		produtoMerge.setDataCriacao(LocalDateTime.now());
		
		entityManager.getTransaction().begin();
//		entityManager.merge(produtoMerge);  // o merge cria uma COPIA do objetoprodutoMerge
//		produtoMerge.setNome("Smartphone Two Plus"); // isso nao irá alterar o DB
		produtoMerge = entityManager.merge(produtoMerge);  // agora sim o merge retorna a copia do objeto gerenciado e o proximo funciona
		produtoMerge.setNome("Notebook Dell 2"); // alterar o DB
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
		Assert.assertNotNull(produtoVerificacaoMerge);
	}
	
	@Test
	public void inserirObjetoComMerge() {
		Produto produto = new Produto();

//		produto.setId(4);
		produto.setNome("Microfone Rode Videmic");
		produto.setDescricao("A melhor qualidade de som");
		produto.setPreco(new BigDecimal(1000));
		produto.setDataCriacao(LocalDateTime.now());
		
		entityManager.getTransaction().begin();
		Produto produtoSalvo = entityManager.merge(produto); // o merge retorna o objeto que foi feito o merge, assim retorna tb o Id
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produtoSalvo.getId());
		Assert.assertNotNull(produtoVerificacao);
	}
	
	@Test
	public void atualizaObjetoGerenciado() {
		Produto produto = entityManager.find(Produto.class, 1);
		
		
		entityManager.getTransaction().begin();
		produto.setNome("Kindle PaperWhite 2ª geração");
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, 1);
		
		Assert.assertEquals(produto.getNome(), produtoVerificacao.getNome());
	}
	@Test
	public void atualizarObjeto() {
		Produto produto = new Produto();
		
		produto.setId(1);
		produto.setNome("Kindle PaperWhite");
		produto.setDescricao("Conheca o novo Kindle");
		produto.setPreco(new BigDecimal(599));
		
		entityManager.getTransaction().begin();
		entityManager.merge(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		
		Assert.assertEquals("Kindle PaperWhite", produtoVerificacao.getNome());
	}
	
	@Test
	public void removerObjeto() {
		Produto produto = entityManager.find(Produto.class, 3);
		
		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
		
//		entityManager.clear();  //Não é necessário na asserção para operação de remoção
		
		Produto produtoVerificacao = entityManager.find(Produto.class, 3);
		Assert.assertNull(produtoVerificacao);
	}
	
	@Test
	public void inserirOPrimeiroObjeto() {
		Produto produto = new Produto();

//		produto.setId(2);
		produto.setNome("Camera Canon");
		produto.setDescricao("A melhor definicao para suas fotos");
		produto.setPreco(new BigDecimal(5000));
		produto.setDataCriacao(LocalDateTime.now());
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}
	
	@Test
	public void abrirEFecharATransacao() {
		Produto produto = new Produto(); //somente para o metodo nao mostrar erros

		entityManager.getTransaction().begin();
		
//		entityManager.persist(produto);
//		entityManager.merge(produto);
//		entityManager.remove(produto);
		
		entityManager.getTransaction().commit();
	}

}
