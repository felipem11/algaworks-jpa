package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table (name = "pedido")
public class Pedido {
	@EqualsAndHashCode.Include
	@Id
	private Integer Id;
	
	@Column(name = "data_pedido")
	private LocalDateTime dataPedido;
	
	@Column(name = "data_conclusao")
	private LocalDateTime dataConclusao;
	
	@Column(name = "nota_fiscal_id")
	private Integer notaFiscalId;
	
	private StatusPedido status;
	
	private BigDecimal total;

}
