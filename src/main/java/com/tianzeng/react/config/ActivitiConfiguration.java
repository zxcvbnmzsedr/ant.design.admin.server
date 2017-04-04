package com.tianzeng.react.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by tianzeng on 2017-04-04.
 */
@Configuration
public class ActivitiConfiguration {
    @Bean
    public ProcessEngineConfiguration processEngineConfiguration(DataSource dataSource){
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setDatabaseType("mysql");
        configuration.setActivityFontName("宋体");
        configuration.setLabelFontName("宋体");
        return configuration;
    }
}
