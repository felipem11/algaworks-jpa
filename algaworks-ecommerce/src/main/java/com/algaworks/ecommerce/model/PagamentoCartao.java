package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@DiscriminatorValue("cartao") //Valor da coluna tipo_pagamento
@Table (name = "pagamento_cartao") //Está assumindo a tabela da Herança Pagamento
public class PagamentoCartao extends Pagamento{
	
	@Column(name = "numero_cartao", length = 16)
	private String numeroCartao;
	
}
