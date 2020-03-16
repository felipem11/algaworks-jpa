package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categoria")
public class Categoria {
	@EqualsAndHashCode.Include
	@Id
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
	
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto incremento
	private Integer id;
	
	private String nome;
	
	@Column(name = "categoria_id")
	private Integer categoriaPaiId;

}
