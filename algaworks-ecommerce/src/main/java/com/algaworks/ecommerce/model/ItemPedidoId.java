package com.algaworks.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ItemPedidoId implements Serializable{
	
	@EqualsAndHashCode.Include
	@Column(name = "pedido_id")
	private Integer pedidoId;

	@EqualsAndHashCode.Include
	@Column(name = "produto_id")
	private Integer produtoId;
	
	

}
