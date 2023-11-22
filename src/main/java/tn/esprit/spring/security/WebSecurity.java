package tn.esprit.spring.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.esprit.spring.services.UserService;

@EnableWebSecurity
//Déclaration de la classe WebSecurity qui configure la sécurité de l'application.
public class WebSecurity extends WebSecurityConfigurerAdapter {

	// Service utilisateur pour la gestion des détails des utilisateurs.
	private final UserService userDetailsService;

	// Encodeur de mot de passe BCrypt.
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	// Constructeur de la classe qui prend le service utilisateur et l'encodeur
	// BCrypt en paramètres.
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	// Configuration des règles de sécurité HTTP.
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll().anyRequest().authenticated()
				.and().addFilter(getAuthenticationFilter()).addFilter(new AuthorizationFilter(authenticationManager()))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	// Méthode pour configurer le filtre d'authentification personnalisé.
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}

	// Configuration du gestionnaire d'authentification pour utiliser le service
	// utilisateur et l'encodeur BCrypt.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
}
