package dev.mehdizebhi.jooqwithflywaymigrationdemo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    private HikariDataSource dataSource;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    private void initDataSource() {
        HikariConfig hc = new HikariConfig();

        // set standard params
        hc.setJdbcUrl(datasourceUrl);
        hc.setUsername(datasourceUsername);
        hc.setPassword(datasourcePassword);

        hc.addDataSourceProperty( "cachePrepStmts", "true");
        hc.addDataSourceProperty( "prepStmtCacheSize", "250" );
        hc.addDataSourceProperty( "prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(hc);
    }

    @Bean
    public DSLContext dslContext() {
        initDataSource();

        Settings settings = new Settings()
                .withAttachRecords(false)
                .withExecuteLogging(true);

        org.jooq.Configuration conf = new DefaultConfiguration()
                .set(SQLDialect.MYSQL)
                .set(new DataSourceConnectionProvider(dataSource))
                .set(settings);

        return DSL.using(conf);
    }

    /*@Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(datasourceUrl, datasourceUsername, datasourcePassword)
                .locations("db/migration")
                .load();

        flyway.migrate();

        return flyway;
    }*/
}
