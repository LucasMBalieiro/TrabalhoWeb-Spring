package br.ufscar.dc.dsw.Trabalho2.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.models.SiteReserva;


public interface ISiteResService {

	Hotel buscarPorId(Long id);

	List<SiteReserva> buscarTodos();

	void salvar(SiteReserva email);

	void excluir(long id);
}
