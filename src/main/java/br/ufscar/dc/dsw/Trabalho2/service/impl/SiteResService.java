package br.ufscar.dc.dsw.Trabalho2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.Trabalho2.dao.ISiteReservaDAO;
import br.ufscar.dc.dsw.Trabalho2.models.SiteReserva;
import br.ufscar.dc.dsw.Trabalho2.service.spec.ISiteResService;

@Service
@Transactional(readOnly = false)
public class SiteResService implements ISiteResService{

	@Autowired
	ISiteReservaDAO dao;
	
	public void salvar(SiteReserva siteReserva) {
		dao.save(siteReserva);
	}
	
	public void excluir(long id) {
		dao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public List<SiteReserva> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public SiteReserva buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
}
