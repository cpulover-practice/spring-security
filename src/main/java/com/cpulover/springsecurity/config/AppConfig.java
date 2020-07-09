package com.cpulover.springsecurity.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.cpulover.springsecurity")
@PropertySource("classpath:persistence-mysql.properties") // to read props file
public class AppConfig {

	// setup a variable to hold the properties
	@Autowired
	private Environment evn;

	// setup a logger for diagnostic
	private Logger logger = Logger.getLogger(getClass().getName());

	// Define a bean for View Resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	// define a bean for security data source
	@Bean
	public DataSource securityDataSource() {
		// Create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		// Set the JDBC driver
		try {
			securityDataSource.setDriverClass(evn.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		// Log the connection props
		logger.info(">>> jdbc.url=" + evn.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user=" + evn.getProperty("jdbc.user"));

		// Set database connection properties
		securityDataSource.setJdbcUrl(evn.getProperty("jdbc.url"));
		securityDataSource.setUser(evn.getProperty("jdbc.user"));
		securityDataSource.setPassword(evn.getProperty("jdbc.password"));

		// Set connection pool props
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));		
		
		return securityDataSource;
	}

	// Helper method converting environment properties to int
	private int getIntProperty(String propName) {
		String propVal = evn.getProperty(propName);
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}
}
