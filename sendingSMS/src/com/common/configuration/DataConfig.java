package com.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.common.repository")
public class DataConfig {

/*

    @Bean
 //   @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("com.common.entity");
 
        entityManagerFactoryBean.setJpaProperties(hibernateProp());
 
        return entityManagerFactoryBean;
    }
 
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
 
        return transactionManager;
    }
 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
 
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/helpdesk?useUnicode=true&characterEncoding=UTF8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
 
        return dataSource;
    }
 
    private Properties hibernateProp() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }


    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("com/db/migration");
        flyway.setDataSource(dataSource());
        return flyway;

    }*/

}







/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



//package com.common.configuration;
//
//import com.common.model.Department;
//import com.common.model.Employer;
//import com.common.model.Project;
//import com.common.model.Task;
//import java.util.Properties;
//import javax.sql.DataSource;
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
///**
// *
// * @author SEMEN
// */
//@Configuration
//@ComponentScan("com.common")
//@EnableTransactionManagement
//public class DConfig {
//    @Bean(name = "viewResolver")
//    public InternalResourceViewResolver getViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/pages/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
//     
//    
//    @Bean(name = "dataSource")
//    public DataSource getDataSource() {
//    	BasicDataSource dataSource = new BasicDataSource();
//    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//    	dataSource.setUrl("jdbc:mysql://localhost:3306/projects_v2?useUnicode=true&characterEncoding=UTF8");
//        
//    	dataSource.setUsername("root");
//    	dataSource.setPassword("root");
//    	
//    	return dataSource;
//    }
//    
//    
//    private Properties getHibernateProperties() {
//    	Properties properties = new Properties();
//    	properties.put("hibernate.show_sql", "true");
//    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//    	return properties;
//    }
//    
//    @Bean(name = "sessionFactory")
//    public SessionFactory getSessionFactory(DataSource dataSource) {
//    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//    	sessionBuilder.addProperties(getHibernateProperties());
//        //sessionBuilder.addAnnotatedClass(Employer.class);
//    	sessionBuilder.addAnnotatedClasses(Department.class,Employer.class,Project.class,Task.class);
//    	return sessionBuilder.buildSessionFactory();
//    }
//    
//    @Bean(name = "transactionManager")
//    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
//	HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
//        return transactionManager;
//    }
//    
//    
//    
//}
