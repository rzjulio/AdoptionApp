package org.expeditors.mexicoapps.adoptionapp.jconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class ConfigurationDB {
    @Bean
    public DataSource dataSource() {
        String url = "jdbc:postgresql://localhost:5433/adoptapp";
        String user = "larku";
        String pw = "larku";
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, pw);
        return dataSource;
    }

}