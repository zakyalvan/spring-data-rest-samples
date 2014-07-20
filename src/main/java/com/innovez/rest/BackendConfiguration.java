package com.innovez.rest;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableSpringConfigured
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.innovez.rest.backend")
@PropertySource("classpath:/META-INF/spring/application.properties")
@ComponentScan(basePackages="com.innovez.rest.backend")
public class BackendConfiguration {
	@Autowired
	private JdbcDataSourceConnectionParameters connectionParameters;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	@Bean(name="dataSource")
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(connectionParameters.getDriverClassName());
		dataSource.setUrl(connectionParameters.getUrl());
		dataSource.setUsername(connectionParameters.getUsername());
		dataSource.setPassword(connectionParameters.getPassword());
		return dataSource;
	}
	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource());
		dataSourceInitializer.setDatabasePopulator(databasePopulator());
		return dataSourceInitializer;
	}
	@Bean
	public ResourceDatabasePopulator databasePopulator() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("/META-INF/spring/data.sql"));
		databasePopulator.setContinueOnError(true);
		return databasePopulator;
	}
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("com.innovez.rest.backend");
		return entityManagerFactoryBean;
	}
	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
		return jpaVendorAdapter;
	}
	@Bean(name="transactionManager")
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}
	
	
	@Component
	public static class JdbcDataSourceConnectionParameters {
		@Value("${innovez.jdbc.connection.url}")
		private String url;
		@Value("${innovez.jdbc.connection.driver}")
		private String driverClassName;
		@Value("${innovez.jdbc.connection.username}")
		private String username;
		@Value("${innovez.jdbc.connection.password}")
		private String password;
		
		public String getUrl() {
			return url;
		}
		public String getDriverClassName() {
			return driverClassName;
		}
		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
	}
}
