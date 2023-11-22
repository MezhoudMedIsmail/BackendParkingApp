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

//D�claration de la classe AuthorizationFilter qui �tend BasicAuthenticationFilter.
public class AuthorizationFilter extends BasicAuthenticationFilter {

	// Constructeur de la classe qui prend un gestionnaire d'authentification en
	// param�tre.
	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	// M�thode appel�e pour filtrer les demandes entrantes.
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// R�cup�ration de l'en-t�te d'authentification de la demande.
		String header = req.getHeader(SecurityConstants.HEADER_STRING);

		// V�rification de la pr�sence du pr�fixe du jeton JWT dans l'en-t�te.
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			// Si le pr�fixe n'est pas pr�sent, la demande est transmise au filtre suivant
			// dans la cha�ne.
			chain.doFilter(req, res);
			return;
		}

		// Obtention de l'objet d'authentification � partir de la demande.
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		// D�finition de l'objet d'authentification dans le contexte de s�curit�.
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Transmission de la demande au filtre suivant dans la cha�ne.
		chain.doFilter(req, res);
	}

	// M�thode priv�e pour extraire et v�rifier le jeton JWT � partir de la demande.
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		// R�cup�ration du jeton JWT de l'en-t�te d'authentification.
		String token = request.getHeader(SecurityConstants.HEADER_STRING);

		// V�rification de la pr�sence du jeton.
		if (token != null) {
			// Suppression du pr�fixe du jeton.
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

			// Extraction du nom d'utilisateur � partir du jeton JWT.
			String user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token).getBody()
					.getSubject();

			// V�rification de la pr�sence du nom d'utilisateur.
			if (user != null) {
				// Retourne un objet d'authentification avec le nom d'utilisateur et une liste
				// vide d'autorisations.
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}

			// Retourne null si le nom d'utilisateur n'est pas pr�sent dans le jeton.
			return null;
		}

		// Retourne null si le jeton n'est pas pr�sent dans l'en-t�te.
		return null;
	}
}
