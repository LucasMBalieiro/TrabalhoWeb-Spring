package br.ufscar.dc.dsw.Trabalho2;

import java.time.LocalDate;
import java.math.BigDecimal;

import br.ufscar.dc.dsw.Trabalho2.dao.*;
import br.ufscar.dc.dsw.Trabalho2.models.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Trabalho2Application {

	public static void main(String[] args) {
		SpringApplication.run(Trabalho2Application.class, args);
	}
	@Bean
	public CommandLineRunner demo(BCryptPasswordEncoder encoder, IUsuarioDAO usuarioDAO, IHotelDAO hotelDAO, ISiteReservaDAO siteDAO, IPromocaoDAO promocaoDAO) {
		return (args) -> {
			try {
				Usuario a1 = new Usuario(
						"admin@admin",
						encoder.encode("admin123"));
				usuarioDAO.save(a1);

				Hotel h1 = new Hotel("vitor.orsin@gmail.com",
						encoder.encode("123"),
						"77.888.999/0001-10",
						"Repouso Amimir",
						"São Carlos");
				hotelDAO.save(h1);

				SiteReserva s1 = new SiteReserva(
						"trivago@gmail.com",
						encoder.encode("12tri"),
						"www.trivago.com.br",
						"Trivago",
						"(16) 3382-9922");
				siteDAO.save(s1);

				Promocao p1 = new Promocao(
						h1,
						s1,
						new BigDecimal("500.00"),
						LocalDate.of(2022, 12, 18),
						LocalDate.of(2023, 1, 18));
				promocaoDAO.save(p1);

//			Locacao l1 = new Locacao();
//			l1.setClient(u2);
//			l1.setRentalCompany(u3);
//			l1.setDate(LocalDate.of(2024, 1, 1));
//			l1.setHour(LocalTime.of(12, 0));
//			locacaoDAO.save(l1);
//
//			Locacao l2 = new Locacao();
//			l2.setClient(u2);
//			l2.setRentalCompany(u3);
//			l2.setDate(LocalDate.of(2024, 1, 1));
//			l2.setHour(LocalTime.of(13, 0));
//			locacaoDAO.save(l2);

			}
			catch(Exception e){

			}
		};
	}
}
