package br.ufscar.dc.dsw.Trabalho2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.Trabalho2.dao.IPromocaoDAO;
import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.models.Promocao;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IPromocaoService;

@Service
@Transactional(readOnly = false)
public class PromocaoService implements IPromocaoService{

	@Autowired
	IPromocaoDAO dao;
	
	public void salvar(Promocao promocao) {
		dao.save(promocao);
	}
	
	@Transactional(readOnly = true)
	public Promocao buscarPorId(long id) {
		return dao.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Promocao> buscarTodosPorHotel(Hotel h) {
		return dao.findAllByHotel(h);
	}
}
