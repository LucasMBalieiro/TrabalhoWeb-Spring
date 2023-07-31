package br.ufscar.dc.dsw.Trabalho2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import br.ufscar.dc.dsw.Trabalho2.security.UsuarioDetailsServiceImpl;

@Configuration
public class WebSecurityConfig{

  @Bean
  public UserDetailsService userDetailsService() {
    return new UsuarioDetailsServiceImpl();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authz -> authz
				.requestMatchers("/", "/layout", "/error", "/listaHoteis").permitAll()
				.requestMatchers("/login/**", "/image/**").permitAll()
				.requestMatchers("/Admin/**").hasRole("ADM")
				.requestMatchers("/Hotel/**").hasRole("HOT")
				.requestMatchers("/Site/**").hasRole("SIT")
				.anyRequest().authenticated())
		.formLogin(login -> login.loginPage("/login").permitAll())
		.logout((logout) -> logout.logoutSuccessUrl("/").permitAll());
		
		return http.build();
	}
}