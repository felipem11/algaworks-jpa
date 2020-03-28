package listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.algaworks.ecommerce.model.Pedido;

import service.NotaFiscalService;

public class GerarNotaFiscalListener {
	
	private NotaFiscalService notaFiscalService = new NotaFiscalService();
	
	@PrePersist
	@PreUpdate
	public void gerar(Pedido pedido) {
		if (pedido.isPago() && pedido.getNotaFiscal() == null) {
			notaFiscalService.gerar(pedido);
		}
		
	}
	

}
