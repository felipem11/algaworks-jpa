package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	
	@EmbeddedId
	private ItemPedidoId id;

	
	//optional indicar que sempre vai ter o atributo
	//forçando fazer o inner join ao invés de Left outer join que é menos performatico
	@ManyToOne(optional = false)
	@MapsId("pedidoId")
	@JoinColumn(name = "pedido_id")
//	@JoinColumn(name = "pedido_id", insertable = false, updatable = false)
	private Pedido pedido;
	
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "produto_id")
	@MapsId("produtoId")
//	@JoinColumn(name = "produto_id", insertable = false, updatable = false)
	private Produto produto;

	@Column(name = "preco_produto")
	private BigDecimal precoProduto;
	
	private Integer quantidade;
}
