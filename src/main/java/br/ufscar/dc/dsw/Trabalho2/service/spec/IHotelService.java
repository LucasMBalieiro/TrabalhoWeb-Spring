package br.ufscar.dc.dsw.Trabalho2.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.Trabalho2.models.Hotel;

public interface IHotelService {

	Hotel buscarPorId(Long id);
	
	List<Hotel> buscarTodos();
	
	void salvar(Hotel email);
	
	void excluir(Long id);
}
