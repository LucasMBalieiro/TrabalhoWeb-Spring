package br.ufscar.dc.dsw.Trabalho2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.Trabalho2.models.Usuario;

@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
	
	Usuario findById(long id);

	List<Usuario> findAll();
	
	Usuario save(Usuario u);
	
	void deleteById(long id);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario getUserByEmail(@Param("email") String email);

	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findByEmail(@Param("email") String email);
}
