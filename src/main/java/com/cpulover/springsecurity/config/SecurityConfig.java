package com.cpulover.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// reference to security data source
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// use jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);

		// add users for in-memory authentication
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		auth.inMemoryAuthentication().withUser(users.username("cpulover1").password("pass1").roles("EMPLOYEE"))
//				.withUser(users.username("cpulover2").password("pass2").roles("EMPLOYEE", "MANAGER"))
//				.withUser(users.username("cpulover3").password("pass3").roles("EMPLOYEE", "ADMIN"));

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// .anyRequest().authenticated() // any requests to the app must be
				// authenticated
				.antMatchers("/").hasRole("EMPLOYEE").antMatchers("/leaders/**").hasRole("MANAGER")
				.antMatchers("/systems/**").hasRole("ADMIN").and().formLogin() // customize the form login
				.loginPage("/showLoginPage") // declare request mapping (need to define in Controller) to show form
				.loginProcessingUrl("/authenticateTheUser") // login form should POST data to this URL for processing
															// (no need to define in Controller)
				.permitAll() // allow everyone to see the login page
				.and().logout().permitAll() // add logout support
				.and().exceptionHandling().accessDeniedPage("/access-denied"); // declare path for access denied page
	}

}
