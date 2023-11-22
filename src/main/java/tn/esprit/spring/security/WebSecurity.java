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
//D�claration de la classe WebSecurity qui configure la s�curit� de l'application.
public class WebSecurity extends WebSecurityConfigurerAdapter {

	// Service utilisateur pour la gestion des d�tails des utilisateurs.
	private final UserService userDetailsService;

	// Encodeur de mot de passe BCrypt.
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	// Constructeur de la classe qui prend le service utilisateur et l'encodeur
	// BCrypt en param�tres.
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	// Configuration des r�gles de s�curit� HTTP.
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll().anyRequest().authenticated()
				.and().addFilter(getAuthenticationFilter()).addFilter(new AuthorizationFilter(authenticationManager()))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	// M�thode pour configurer le filtre d'authentification personnalis�.
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
