package br.ufscar.dc.dsw.Trabalho2.validation;

import br.ufscar.dc.dsw.Trabalho2.dao.IPromocaoDAO;
import br.ufscar.dc.dsw.Trabalho2.models.Promocao;
import jakarta.validation.ConstraintValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class UniqueDateValidation implements ConstraintValidator<ValidPromocao, Promocao> {
    @Autowired
    private IPromocaoDAO dao;

    @Override
    public boolean isValid(Promocao promocao, ConstraintValidatorContext context) {
        if (dao != null) {

            final boolean[] isValid = {true};

            List<Promocao> promos = dao.findAllByHotel(promocao.getHotel());
            promos.forEach(promoI -> {
                if (promoI.getDataInicio().equals(promocao.getDataInicio()) && promoI.getDataFim().equals(promocao.getDataFim())) {
                    isValid[0] = false;
                }
            });

            // Se a validação falhar, o método isValid retorna false
            // e a mensagem de erro é exibida na página no campo "hour"
            if (!isValid[0]) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addPropertyNode("hour").addConstraintViolation();
            }

            return isValid[0];
        } else {
            return true;
        }

    }
}
