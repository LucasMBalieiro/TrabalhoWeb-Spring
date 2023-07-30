package br.ufscar.dc.dsw.Trabalho2.validation;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.dc.dsw.Trabalho2.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.Trabalho2.models.Usuario;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private IUsuarioDAO dao;

	@Override
	public boolean isValid(String Email, ConstraintValidatorContext context) {
		if (dao != null) {
			Usuario usuario = dao.findByEmail(Email);
			return usuario == null;
		} else {
			return true;
		}

	}
}