package com.spring.learn.config;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.spring.learn.services")
@EnableJpaRepositories("com.spring.learn.repository")
//@EnableTransactionManagement enables annotation-driven transaction management capability. 
//To handle transaction at DAO layer, we annotate DAO class or method with @Transactional annotation.
@EnableTransactionManagement  
@PropertySource("classpath:database.properties")
public class DataBaseJPAConfig {
	@Autowired
    private Environment env;	
	
////	Create entity manager factory bean using the following class.
////	org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean 
////	It creates a JPA EntityManagerFactory according to the standard container bootstrap contract of JPA. 
////	In this way we get a shared EntityManagerFactory in our spring application context. 
////	To use it in DAO we just do dependency injection to get its instance. 
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setDataSource(getDataSource());
		lcemfb.setJpaProperties(getJPAProperties());
		lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
		lcemfb.setPackagesToScan("com.spring.learn.domain");
		return lcemfb;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	@Bean
	public Properties getJPAProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		return properties;
	}
	
////	Create data source using BasicDataSource class from the commons.dbcp2 library. 
////	Using this class we configure database driver class name, URL, username and password.
	@Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
	    dataSource.setUrl(env.getProperty("database.url"));
	    dataSource.setUsername(env.getProperty("database.username"));
	    dataSource.setPassword(env.getProperty("database.password"));
	    return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return adapter;
	}

	
	
}

