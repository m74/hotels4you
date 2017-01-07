package ru.com.m74.hotels4you.config;

import com.mysql.jdbc.Driver;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author mixam
 * @since 06.01.17 1:35
 */
@Configuration
//@Order(5)
public class DB {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl(System.getProperty("db.url"));
        dataSource.setUsername(System.getProperty("db.user"));
        dataSource.setPassword(System.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    @DependsOn(value = "dataSource")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    @DependsOn(value = "dataSource")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    /**
     * Liquibase migrations bean
     *
     * @return
     * @throws IOException
     */
    @Bean
    public SpringLiquibase migrations() throws IOException {
        LoggerFactory.getLogger("Configuration:DB").debug("process migrations");

        SpringLiquibase runner = new SpringLiquibase();
        runner.setDataSource(dataSource());
        runner.getResourceLoader();
        runner.setChangeLog("classpath:migrations/db.changelog-master.xml");
        return runner;
    }

}
