package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
//@EntityListeners({ GenericoListener.class})
@Table (name = "produto",
		uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = { "nome" })},
		indexes = { @Index(name = "idx_nome", columnList = "nome")})
public class Produto extends EntidadeBaseInteger{
	
	
	@Column(name = "data_criacao", updatable = false, nullable = false)
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_ultima_atualizacao", insertable = false)
	private LocalDateTime dataUltimaAtualizacao;
	
	
	@Column(length = 100, nullable = false)
	private String nome;
	
//	@Lob	// descricao longtext(no caso do mySql) 
	@Column(columnDefinition = "varchar(275) not null default 'descricao'")
	private String descricao;
	
	@Column(precision = 10, scale = 2) // preco decimal(10,2)
	private BigDecimal preco;
	
	@ManyToMany // owner
	@JoinTable(name = "produto_categoria", //tabela intermadiária para relacionar categorias e produtos
			joinColumns = @JoinColumn (name = "produto_id", 
					foreignKey = @ForeignKey(name = "fk_produto_categoria_produto")),  //nome da coluna da intermediária
			inverseJoinColumns = @JoinColumn (name = "categoria_id",
					foreignKey = @ForeignKey(name = "fk_produto_categoria_categoria"))) // idem
	private List<Categoria> categorias;
	
	@OneToOne(mappedBy = "produto")
	private Estoque estoque;
	
//	Cria uma tabela para trabalhar com lista de elementos primitivos como String, Int e  etc
	@ElementCollection
	@CollectionTable(name = "produto_tag",
				joinColumns = @JoinColumn(name = "produto_id", 
						foreignKey = @ForeignKey(name = "fk_produto_produto_tag"))) // nome da coluna na tabela produto_tag
	@Column(name = "tag", length = 50, nullable = false) // nome da coluna na tabela produto_tag
	private List<String> tags;
	
	
	@ElementCollection
	@CollectionTable(name = "produto_atributo",
				joinColumns = @JoinColumn(name = "produto_id", 
						foreignKey = @ForeignKey(name = "fk_produto_produto_atributo")))
	private List<Atributo> atributo;
	
	@Lob
//	@ElementCollection
//	@CollectionTable(name = "produto_foto",
//				joinColumns = @JoinColumn(name = "produto_id"))
	private byte[] foto;
}
