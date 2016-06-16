package com.svenskeknappen.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Autowired
	UserDetailsService authenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/index", "/home", "/css/**", "/js/**","/img/**", "/register", "/fonts/**").permitAll()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest()
				.authenticated().and().formLogin().loginPage("/login").permitAll()
				.and().logout().logoutSuccessUrl("/login?logout").permitAll()
				.and().exceptionHandling().accessDeniedPage("/403");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService);// .passwordEncoder(passwordEncoder());
	}

	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {
	// ShaPasswordEncoder encoder = new ShaPasswordEncoder();
	// auth.userDetailsService(authenticationService).passwordEncoder(encoder);
	// }

	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth
	// .jdbcAuthentication()
	// .dataSource(this.dataSource)
	// .usersByUsernameQuery("select email,password, enabled from users where
	// email=?")
	// .authoritiesByUsernameQuery("select email, role from user_roles where
	// email=?");
	// /*
	// .inMemoryAuthentication()
	// .withUser("user").password("password").roles("USER");
	// */}
}
