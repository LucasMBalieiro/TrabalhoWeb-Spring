package br.ufscar.dc.dsw.Trabalho2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.Trabalho2.models.SiteReserva;

@SuppressWarnings("unchecked")
public interface ISiteReservaDAO extends CrudRepository<SiteReserva, Long>{
	
	SiteReserva findById(long id);
	
	List<SiteReserva> findAll();
	
	SiteReserva save(SiteReserva siteReserva);

	void deleteById(long id);
	
    @Query("SELECT e FROM SiteReserva e WHERE e.nome = :nome")
    SiteReserva getSiteByNome(@Param("nome") String nome);
}
