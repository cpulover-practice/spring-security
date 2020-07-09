package com.cpulover.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// add users for in-memory authentication
		UserBuilder users=User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser(users.username("cpulover1").password("pass1").roles("EMPLOYEE"))
			.withUser(users.username("cpulover2").password("pass2").roles("MANAGER"))
			.withUser(users.username("cpulover3").password("pass3").roles("ADMIN"));

	}

}
