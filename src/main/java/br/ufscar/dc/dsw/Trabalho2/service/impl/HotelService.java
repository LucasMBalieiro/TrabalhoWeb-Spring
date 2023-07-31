package br.ufscar.dc.dsw.Trabalho2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.Trabalho2.dao.IHotelDAO;
import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IHotelService;

@Service
@Transactional(readOnly = false)
public class HotelService implements IHotelService{

	@Autowired
	IHotelDAO dao;
	
	public void salvar(Hotel hotel) {
		dao.save(hotel);
	}
	
	public void excluir(long id) {
		dao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Hotel> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public Hotel buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Hotel> buscarTodosPorCidade(String cidade){
		return dao.findAllByCidade(cidade);
	}

	public List<String> buscarCidades(){
		List<String> l = new ArrayList<String>();
		List<Hotel> hoteis = this.buscarTodos();

		for(Hotel hotel: hoteis) {
			String cidade = hotel.getCidade();
			if (!l.contains(cidade)) {
				l.add(cidade);
			}
		}
		return l;
	}
}
