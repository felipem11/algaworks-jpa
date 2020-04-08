package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@DiscriminatorValue("boleto") //nome do valor da coluna tipo_pagamento
@Table(name = "pagamento_boleto") //Está assumindo a tabela da Herança Pagamento
public class PagamentoBoleto extends Pagamento{
	

	@Column(name = "codigo_barras", length = 100)
	private String codigoBarras;
	
}
