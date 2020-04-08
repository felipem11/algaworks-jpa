package com.algaworks.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "estoque")
public class Estoque extends EntidadeBaseInteger{
	
	//optional indicar que sempre vai ter o atributo
	//forçando fazer o inner join ao invés de Left outer join que é menos performatico
	@OneToOne(optional = false)
	@JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_estoque_produto"))
	private Produto produto;
	
	private Integer quantidade;

}
