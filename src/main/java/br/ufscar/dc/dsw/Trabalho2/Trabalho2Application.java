package br.ufscar.dc.dsw.Trabalho2;

import br.ufscar.dc.dsw.Trabalho2.controller.SiteReservaController;
import br.ufscar.dc.dsw.Trabalho2.dao.IHotelDAO;
import br.ufscar.dc.dsw.Trabalho2.dao.IPromocaoDAO;
import br.ufscar.dc.dsw.Trabalho2.dao.ISiteReservaDAO;
import br.ufscar.dc.dsw.Trabalho2.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.Trabalho2.models.Hotel;
import br.ufscar.dc.dsw.Trabalho2.models.Promocao;
import br.ufscar.dc.dsw.Trabalho2.models.SiteReserva;
import br.ufscar.dc.dsw.Trabalho2.models.Usuario;
import org.apache.maven.model.Site;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.Random;

@SpringBootApplication
public class Trabalho2Application {

	public static void main(String[] args) {
		SpringApplication.run(Trabalho2Application.class, args);
	}
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IHotelDAO hotelDAO, ISiteReservaDAO siteDAO, IPromocaoDAO promocaoDAO) {
		return (args) -> {

			Usuario u1 = new Usuario();
			u1.setEmail("admin@admin.com");
			u1.setSenha(encoder.encode("admin"));
			u1.setTipo("ROLE_ADMIN");
			usuarioDAO.save(u1);

			Hotel h1 = new Hotel("hotel1@hotel.com", encoder.encode("123"),"hotel", "52.183.821/0001-04", "Hotel 1", "São Paulo");

			Usuario u2 = new Usuario();
			u2.setEmail(h1.getEmail());
			u2.setSenha(h1.getSenha());
			u2.setTipo("ROLE_HOTEL");
			usuarioDAO.save(u2);
			hotelDAO.save(h1);

			Hotel h2 = new Hotel("hotel2@hotel.com", encoder.encode("123"),"hotel","86.256.076/0001-06", "Hotel 2", "São Paulo");

			Usuario u3 = new Usuario();
			u3.setEmail(h2.getEmail());
			u3.setSenha(h2.getSenha());
			u3.setTipo("ROLE_HOTEL");
			usuarioDAO.save(u3);
			hotelDAO.save(h2);



			SiteReserva s1 = new SiteReserva("site1@site.com", encoder.encode("123"),"site","site1.com.br", "Site 1", "11963169791");

			Usuario u5 = new Usuario();
			u5.setEmail(s1.getEmail());
			u5.setSenha(s1.getSenha());
			u5.setTipo("ROLE_SITE");
			usuarioDAO.save(u5);
			siteDAO.save(s1);

			SiteReserva s2 = new SiteReserva("site2@site.com", encoder.encode("123"),"site" ,"site2.com.br", "Site 2", "11963269791");

			Usuario u6 = new Usuario();
			u6.setEmail(s2.getEmail());
			u6.setSenha(s2.getSenha());
			u6.setTipo("ROLE_SITE");
			usuarioDAO.save(u6);

			siteDAO.save(s2);



			Promocao p1 = new Promocao(s1,h1,1000, new Date(2023-1900,12,13), new Date(2023-1900,12,24));
			promocaoDAO.save(p1);

			//List<Promocao> l1 = new ArrayList<Promocao>();
			//l1.add(p1);
			//h1.setPromocoes(l1);
			//hotelDAO.save(h1);
		};
	}

	private static String gerarCNPJ() {
		Random random = new Random();

		int[] cnpjArray = new int[14];
		for (int i = 0; i < 14; i++) {
			if (i == 2 || i == 6) {
				cnpjArray[i] = '.';
			} else if (i == 10) {
				cnpjArray[i] = '/';
			} else if (i == 13) {
				cnpjArray[i] = '-';
			} else {
				cnpjArray[i] = random.nextInt(10);
			}
		}

		return new String(cnpjArray, 0, 14);
	}
}
