package com.pooja.dongarkar;

import javax.activation.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBConfiguration {

	@Bean
	public DriverManagerDataSource getDataSource() {
		System.out.println("connection 1");

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ds.setUrl("jdbc:sqlserver://DESKTOP-R8MDUCP\\MSSQLSERVER;databaseName=FMS;portNumber=1433;MultipleActiveResultSets=True");
		//ds.setUrl("jdbc:sqlserver://192.168.8.225\\MSSQLSERVER;databaseName=FMS;portNumber=1433");
		//ds.setUrl("jdbc:sqlserver://192.168.2.11\\MSSQLSERVER;databaseName=FMS;portNumber=1433");

		ds.setUsername("sa");
		ds.setPassword("root");
		//ds.setPassword("pd@1234A");
		
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		System.out.println("connection2");
		return new JdbcTemplate((javax.sql.DataSource) getDataSource());
	}


}
