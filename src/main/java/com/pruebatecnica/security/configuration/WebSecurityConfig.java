package com.pruebatecnica.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pruebatecnica.security.bean.AuthenticationTokenFilter;
import com.pruebatecnica.security.component.JwtAuthenticationEntryPoint;


@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//punto de entrada
	@Autowired
	@Qualifier("JwtAuthenticationEntryPoint")
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	@Qualifier("UserUserDetailServiceImpl")
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		
		authenticationManagerBuilder
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
		
	}
	
	@Bean
	public AuthenticationTokenFilter authenticationTokenFilter(){
		return new AuthenticationTokenFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
			
			//no necesitamos CSRF porque nuestro token es invulnerable
			.csrf().disable()
		
			//Es necesario para analizar los roles de los usuarios en cada peticion rest
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
			
			//No crear sesion
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			
			.authorizeRequests()
			
			.antMatchers(
				HttpMethod.GET,
				"/",
				"/*.html",
				"/**/*.html",
				"/**/*.css",
				"/**/*.js"
				
			).permitAll()
			.antMatchers("/security/login/**", "/v2/api-docs", "/resources/webjars/**").permitAll()
			.anyRequest().authenticated();
		
			//Filtro de seguridad basado en JWT personalizado
			http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	
			//deshabilitar el almacenamiento en caché de página
			http.headers().cacheControl();
			
	
	}
	
	
	
	
}
