package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import listener.GenericoListener;
import listener.GerarNotaFiscalListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@EntityListeners({ GerarNotaFiscalListener.class, GenericoListener.class})
@Table (name = "pedido")
public class Pedido {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	//optional indicar que sempre vai ter o atributo
	//forçando fazer o inner join ao invés de Left outer join que é menos performatico
	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "data_pedido")
	private LocalDateTime dataCriacao;

	@Column(name = "data_ultima_atualizacao")
	private LocalDateTime dataUltimaAtualizacao;
	
	@Column(name = "data_conclusao")
	private LocalDateTime dataConclusao;
	
	//padrao Eager, sempre que a entidade Pedido for carregada ela tb será
	// isso pq é um uma simples entidade mapeada
	@OneToOne(mappedBy = "pedido", fetch = FetchType.EAGER)
	private NotaFiscal notaFiscal;
	
	@Enumerated (EnumType.STRING)
	private StatusPedido status;
	
	private BigDecimal total;
	
	//padrao LAZY qnd quando se está mapeando uma entidade de Coleção
	//só irá no DB se tiver sendo usada como pedido.getItens().isEmpty().
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
	private List<ItemPedido> itens;
	
	@Embedded
	private EnderecoEntregaPedido endereco;
	
	@OneToOne(mappedBy = "pedido")
	private PagamentoCartao pagamento;
	
	public boolean isPago() {
		return StatusPedido.PAGO.equals(status);
	}
	
	public void calcularTotal() {
		if (itens != null) {
			total = itens.stream().map(ItemPedido::getPrecoProduto)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
	}
	
	@PrePersist
	public void aoPersistir() {
		System.out.println("Antes de persistir Pedido");
		dataCriacao = LocalDateTime.now();
		calcularTotal();
	}
	
	@PreUpdate
	public void aoAtualizar() {
		System.out.println("Antes de atualizar Pedido");
		dataUltimaAtualizacao = LocalDateTime.now();
		calcularTotal();
	}
	
	@PostPersist
	public void aposPersistir() {
		System.out.println("Após persistir Pedido");
	}
	
	@PostUpdate
	public void aposAtualizar() {
		System.out.println("Após atualizar Pedido");
	}
	
	@PreRemove
	public void aoRemover() {
		System.out.println("Antes de remover Pedido");
	}
	
	@PostRemove
	public void aposRemover() {
		System.out.println("Antes de remover Pedido");
	}
	
	@PostLoad
	public void aoCarregar() {
		System.out.println("Após carregar o Pedido");
	}

}
