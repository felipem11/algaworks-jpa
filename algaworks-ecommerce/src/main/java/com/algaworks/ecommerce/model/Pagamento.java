package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING) //para single table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //Cria uma tabela unica pra pagamento 
//@Inheritance(strategy = InheritanceType.JOINED) //Cria 3 tabelas separadas para Pagamento e cada Entidade que herdar Pagamento 
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //Cria tabelas separadas para cada Entidade que herdar Pagamento 
@Table(name = "pagamento")
@Entity
public abstract class Pagamento extends EntidadeBaseInteger{
	
	//optional indicar que sempre vai ter o atributo
	//forçando fazer o inner join ao invés de Left outer join que é menos performatico
	@OneToOne(optional = false)
	@JoinColumn(name = "pedido", nullable = false,
			foreignKey = @ForeignKey(name = "fk_pagamento_pedido"))
	@MapsId
	private Pedido pedido;
	
	@Enumerated (EnumType.STRING)
	@Column(length = 30, nullable = false)
	private StatusPagamento status;
	
	

}
