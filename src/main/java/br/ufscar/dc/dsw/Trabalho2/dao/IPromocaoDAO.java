package br.ufscar.dc.dsw.Trabalho2.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.models.Promocao;

@SuppressWarnings("unchecked")
public interface IPromocaoDAO extends CrudRepository<Promocao, Long>{

	Promocao findById(long id);
	
	List<Promocao> findAllByHotel(Hotel email);
	
	Promocao save(Promocao promocao);
}
