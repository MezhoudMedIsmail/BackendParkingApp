package tn.esprit.spring.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.spring.SpringApplicationContext;
import tn.esprit.spring.requests.UserLoginRequest;
import tn.esprit.spring.services.UserService;
import tn.esprit.spring.shared.dto.UserDto;

//D�claration de la classe AuthenticationFilter qui �tend UsernamePasswordAuthenticationFilter.
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	// Gestionnaire d'authentification pour traiter les tentatives
	// d'authentification.
	private final AuthenticationManager authenticationManager;

	// Constructeur de la classe qui prend un gestionnaire d'authentification en
	// param�tre.
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	// M�thode pour tenter l'authentification en r�cup�rant les informations
	// d'identification de la demande.
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			// Lecture des informations d'identification (email et mot de passe) de la
			// demande.
			UserLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequest.class);

			// Authentification en utilisant le gestionnaire d'authentification.
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// M�thode appel�e en cas d'authentification r�ussie pour g�n�rer et renvoyer un
	// jeton JWT.
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		// Obtention du nom d'utilisateur � partir de l'authentification r�ussie.
		String userName = ((User) auth.getPrincipal()).getUsername();

		// Obtention du service utilisateur � partir du contexte Spring.
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");

		// R�cup�ration des informations de l'utilisateur � partir du service
		// utilisateur.
		UserDto userDto = userService.getUser(userName);

		// Cr�ation du jeton JWT.
		String token = Jwts.builder().setSubject(userName).claim("role", userDto.getRole())
				.claim("id", userDto.getUserId())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();

		// Ajout du jeton JWT dans les en-t�tes de la r�ponse.
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("user_id", userDto.getUserId());
		// �criture du jeton dans le corps de la r�ponse au format JSON.
		res.getWriter().write("{\"token\": \"" + token + "\"}");
	}
}
