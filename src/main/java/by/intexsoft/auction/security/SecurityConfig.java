package by.intexsoft.auction.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import by.intexsoft.auction.service.AuthenticationService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/login/").permitAll()
		.antMatchers(HttpMethod.POST, "/api/registration/").permitAll()
		.antMatchers(HttpMethod.GET, "/api/lot/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/auction/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/lot/**").hasAnyAuthority("ROLE_USER","ROLE_MANAGER")
		.antMatchers(HttpMethod.POST, "/api/user/**").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.DELETE, "/api/lot/**").hasAuthority("ROLE_USER")
		.antMatchers(HttpMethod.POST, "/api/auction/**").hasAuthority("ROLE_MANAGER")
		.antMatchers(HttpMethod.DELETE, "/api/auction/**").hasAuthority("ROLE_MANAGER")
		.antMatchers(HttpMethod.GET, "/api/trading-day/**").hasAnyAuthority("ROLE_MANAGER","ROLE_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/trading-day/**").hasAuthority("ROLE_ADMIN")
		.anyRequest().authenticated().and()
				.addFilterBefore(new TokenFilter(authenticationService), UsernamePasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}