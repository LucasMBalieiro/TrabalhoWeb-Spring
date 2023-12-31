package br.ufscar.dc.dsw.Trabalho2.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.Trabalho2.models.SiteReserva;


public interface ISiteResService {

	SiteReserva buscarPorId(Long id);

	List<SiteReserva> buscarTodos();

	void salvar(SiteReserva email);

	void excluir(long id);
}
