package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by babu.kannar@indianic.com on 10/12/2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RestAuthenticationEntryPoint unauthorizedHandler;

	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()

				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

				.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

				.antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/images/users/**/profilePic*.*")
				
				.permitAll()
				
				.antMatchers("/api/registration/**", "/api/login/**","/api/getProducts/**","/api/getProductDetail/**")
				
				.permitAll().antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
				
				.anyRequest().authenticated();

		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		httpSecurity.headers().cacheControl();

	}
}