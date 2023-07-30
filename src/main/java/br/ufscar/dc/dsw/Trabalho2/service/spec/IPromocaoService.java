package br.ufscar.dc.dsw.Trabalho2.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.models.Promocao;



public interface IPromocaoService {

	Promocao buscarPorId(long id);

	List<Promocao> buscarTodosPorHotel(Hotel h);
	
	void salvar(Promocao promocao);
}
