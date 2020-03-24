package com.algaworks.ecommerce.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categoria")
public class Categoria {
	
//	@GeneratedValue(strategy = GenerationType.AUTO) // quem escolhe a forma é o Hibernate
	
//	 Cria uma sequence caso o DB nao seja compativel com o mySql
//	 Compartilha o nome caso vc defina o mesmo para outras entity
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq") //
//	@SequenceGenerator(name = "seq", sequenceName = "sequencia_chave_primaria", allocationSize = 50)
	
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabela")
//	@TableGenerator(name = "tabela", table = "hibernate_sequences",
//					pkColumnName = "sequence_name",
//					pkColumnValue = "categoria",
//					valueColumnName = "next_val",
//					initialValue = 0,
//					allocationSize = 50	)
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto incremento
	private Integer id;
	
	private String nome;
	
	@ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;

    @OneToMany(mappedBy = "categoriaPai")
    private List<Categoria> categorias;
    
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;

}
