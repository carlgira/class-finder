package com.carlgira.finder;

import com.carlgira.finder.repository.ClassStoreRepository;
import com.carlgira.finder.repository.JarRepository;
import com.carlgira.finder.repository.ProductRepository;
import com.carlgira.finder.repository.VersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

    public static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:" + System.getProperty("sqlite.store.file"));
        return dataSourceBuilder.build();
    }

    @Bean
    public CommandLineRunner importUtil(ProductRepository productRepository, ClassStoreRepository classStoreRepository, JarRepository jarRepository, VersionRepository versionRepository) {
        return new ImportUtil(productRepository, classStoreRepository, jarRepository, versionRepository);
    }
}
