package com.prodata.spsv1;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "com.prodata,presampling")
public class DBConfiguration {
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	//	ds.setUrl("jdbc:sqlserver://DESKTOP-6NNO3VL\\MSSQLSERVER;databaseName=spsv1;portNumber=1433");
		ds.setUrl("jdbc:sqlserver://192.168.6.211\\MSSQLSERVER;databaseName=spsv1;portNumber=1433");
		// ds.setUrl("jdbc:sqlserver://DESKTOP-C3M686E\\MSSQLSERVER;databaseName=spsv1;portNumber=1433");
		ds.setUsername("sa");
		//ds.setPassword("root");
		ds.setPassword("pd@1234A");
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {

		return new JdbcTemplate((javax.sql.DataSource) getDataSource());
	}
}
