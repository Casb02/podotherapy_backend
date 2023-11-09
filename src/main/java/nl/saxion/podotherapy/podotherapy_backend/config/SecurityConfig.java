package nl.saxion.podotherapy.podotherapy_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private static final String[] PUBLIC = new String[] {
			"/test/**",
			"/auth/**"
		};

	private static final String[] ALLOW_EVERYTHING = new String[] {
			"/**"
		};

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	/**
	 * Configures the security filter chain for the application.
	 *
	 * @param httpSecurity the HttpSecurity object used to configure security settings
	 * @return the configured SecurityFilterChain object
	 * @throws Exception if an error occurs during configuration
	 */
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers(ALLOW_EVERYTHING).permitAll()
				.requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN") 	// If UserDetails.getAuthorities return [ADMIN, ...]
				//.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")		// If UserDetails.getAuthorities return [ROLE_ADMIN, ...] 
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	/**
	 * Creates and returns a BCryptPasswordEncoder object for password encoding.
	 *
	 * @return the BCryptPasswordEncoder object
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Creates and returns an AuthenticationManager object for handling authentication requests.
	 *
	 * @param config the AuthenticationConfiguration object containing the necessary configuration options
	 * @return the AuthenticationManager object
	 * @throws Exception if an error occurs while creating the AuthenticationManager
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	/**
	 * Creates and returns a WebSecurityCustomizer object for customizing web security configurations.
	 *
	 * @return the WebSecurityCustomizer object
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/pg-web-console/**"));
	}

	/**
	 * Creates and returns an AuthenticationProvider object for authentication purposes.
	 *
	 * @return the AuthenticationProvider object
	 */
	private AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}
