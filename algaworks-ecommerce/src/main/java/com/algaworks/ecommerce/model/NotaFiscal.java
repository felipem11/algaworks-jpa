package com.algaworks.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table (name = "nota_fiscal")
public class NotaFiscal {
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "pedido_id")
	private Integer id;
	
	
//	@JoinTable(name = "pedido_nota_fiscal", // cria a tabela pedido_nota_fiscal 
//			joinColumns = @JoinColumn(name = "nota_fiscal_id", unique = true), // 
//			inverseJoinColumns = @JoinColumn(name = "pedido_id", unique = true))
	//optional indicar que sempre vai ter o atributo
	//forçando fazer o inner join ao invés de Left outer join que é menos performatico
	@OneToOne(optional = false)
	@MapsId
	@JoinColumn(name = "pedido_id", nullable = false,
			foreignKey = @ForeignKey(name = "fk_nota_fiscal_pedido"))
	private Pedido pedido;
	
	@Lob
	@Column(nullable = false)
	private byte[] xml;

	@Column(name = "data_emissao", nullable = false)
	private Date dataEmissao;
	
}
