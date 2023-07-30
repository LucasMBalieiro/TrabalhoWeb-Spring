package br.ufscar.dc.dsw.Trabalho2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.Trabalho2.dao.IHotelDAO;
import br.ufscar.dc.dsw.Trabalho2.models.entities.Hotel;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IHotelService;

@Service
@Transactional(readOnly = false)
public class HotelService implements IHotelService{

	@Autowired
	IHotelDAO dao;
	
	public void salvar(Hotel hotel) {
		dao.save(hotel);
	}
	
	public void excluir(Long id) {
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
}
