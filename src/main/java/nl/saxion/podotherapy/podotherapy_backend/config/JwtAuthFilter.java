package nl.saxion.podotherapy.podotherapy_backend.config;

import java.io.IOException;
import java.util.Date;

import nl.saxion.podotherapy.podotherapy_backend.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The JwtAuthFilter class is a filter that intercepts all requests and checks if the request contains a valid JWT token.
 * If the token is valid, the user is authenticated and the request is allowed to continue.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Performs authentication and authorization checks for each incoming request.
	 * If the request is not authenticated, it checks for a valid JWT token in the Authorization header.
	 * If a valid token is found, it retrieves the user details from the token and creates an authentication object.
	 * Finally, it sets the authentication object in the security context so that it can be used for further authorization checks.
	 *
	 * @param request the HTTP request
	 * @param response the HTTP response
	 * @param filterChain the filter chain
	 * @throws ServletException if there is a servlet-related exception
	 * @throws IOException if there is an I/O related exception
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
		
			final String authorization = request.getHeader("Authorization");
			if (authorization != null && authorization.startsWith("Bearer ")) {
		
				final String token = authorization.substring(7);
				final Claims claims = jwtService.getClaims(token);
				if (claims.getExpiration().after(new Date())) {

					final String username = claims.getSubject();
					final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					
					final UsernamePasswordAuthenticationToken authToken =
							new UsernamePasswordAuthenticationToken(
									userDetails, null, userDetails.getAuthorities());
				
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
				
			}

		}

		//If the request is authenticated, or if there is no Authorization header, continue with the request
		filterChain.doFilter(request, response);
	}

}
