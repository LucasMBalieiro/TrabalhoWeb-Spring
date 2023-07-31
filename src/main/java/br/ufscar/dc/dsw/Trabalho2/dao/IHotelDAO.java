package br.ufscar.dc.dsw.Trabalho2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.Trabalho2.models.Hotel;

@SuppressWarnings("unchecked")
public interface IHotelDAO extends CrudRepository<Hotel, Long>{

	Hotel findById(long id);
	
	List<Hotel> findAll();
	
	Hotel save(Hotel hotel);
	
	void deleteById(long id);
	
	@Query("SELECT h FROM Hotel h WHERE h.cidade = :cidade")
    public Hotel getHotelByCidade(@Param("cidade") String cidade);

	List<Hotel> findAllByCidade(String cidade);
}
