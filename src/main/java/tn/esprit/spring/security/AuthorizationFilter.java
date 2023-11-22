package tn.esprit.spring.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

//Déclaration de la classe AuthorizationFilter qui étend BasicAuthenticationFilter.
public class AuthorizationFilter extends BasicAuthenticationFilter {

	// Constructeur de la classe qui prend un gestionnaire d'authentification en
	// paramètre.
	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	// Méthode appelée pour filtrer les demandes entrantes.
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// Récupération de l'en-tête d'authentification de la demande.
		String header = req.getHeader(SecurityConstants.HEADER_STRING);

		// Vérification de la présence du préfixe du jeton JWT dans l'en-tête.
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			// Si le préfixe n'est pas présent, la demande est transmise au filtre suivant
			// dans la chaîne.
			chain.doFilter(req, res);
			return;
		}

		// Obtention de l'objet d'authentification à partir de la demande.
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		// Définition de l'objet d'authentification dans le contexte de sécurité.
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Transmission de la demande au filtre suivant dans la chaîne.
		chain.doFilter(req, res);
	}

	// Méthode privée pour extraire et vérifier le jeton JWT à partir de la demande.
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		// Récupération du jeton JWT de l'en-tête d'authentification.
		String token = request.getHeader(SecurityConstants.HEADER_STRING);

		// Vérification de la présence du jeton.
		if (token != null) {
			// Suppression du préfixe du jeton.
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

			// Extraction du nom d'utilisateur à partir du jeton JWT.
			String user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token).getBody()
					.getSubject();

			// Vérification de la présence du nom d'utilisateur.
			if (user != null) {
				// Retourne un objet d'authentification avec le nom d'utilisateur et une liste
				// vide d'autorisations.
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}

			// Retourne null si le nom d'utilisateur n'est pas présent dans le jeton.
			return null;
		}

		// Retourne null si le jeton n'est pas présent dans l'en-tête.
		return null;
	}
}
