package br.ufscar.dc.dsw.Trabalho2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.Trabalho2.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.Trabalho2.models.Usuario;
import br.ufscar.dc.dsw.Trabalho2.service.spec.IUsuarioService;


@Service
@Transactional(readOnly = false)
public class UsuarioService implements IUsuarioService {
	@Autowired
	IUsuarioDAO dao;
	
	public void salvar(Usuario usuario) {
		dao.save(usuario);
	}

	public void excluir(long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Usuario> buscarTodos() {
		return dao.findAll();
	}
	
	public Usuario buscaUsuario(String email) {
		return dao.getUserByEmail(email);
	}
}
