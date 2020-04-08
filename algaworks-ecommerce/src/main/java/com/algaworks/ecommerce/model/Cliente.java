package com.algaworks.ecommerce.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@SecondaryTable(name = "cliente_detalhe", 
		pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"),
		foreignKey = @ForeignKey(name = "fk_cliente_detalhe_cliente"))
@Entity
@Table(name = "cliente",
		uniqueConstraints = { @UniqueConstraint(name = "unq_cpf", columnNames = { "cpf" })},
		indexes = { @Index(name = "idx_nome", columnList = "nome")})
public class Cliente extends EntidadeBaseInteger{
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 14, nullable = false	)
	private String cpf;
	
	@Setter(AccessLevel.NONE)
	@Transient //JPA irá ignorar esse atributo
	private String primeiroNome;
	
	@Column(table = "cliente_detalhe", length = 30, nullable = false)
	@Enumerated (EnumType.STRING)
	private SexoCliente sexo;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedido;

	@Column(name = "data_nascimento", table = "cliente_detalhe")
	private LocalDate dataNascimento;
	
	@ElementCollection
	@CollectionTable(name = "cliente_contato", 
			joinColumns = @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_cliente_cliente_contato")))
	@MapKeyColumn(name = "tipo")
	@Column(name = "descricao")
	private Map<String, String> contatos;
	
	
	@PostLoad
	private void configurarPrimeiroNome() {
		if (nome != null && !nome.isBlank()) {
			int index = nome.indexOf(" ");
			if (index > -1) {
				primeiroNome = nome.substring(0, index);
			}
		}
	}

}
