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
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private HikariDataSource dataSource;

    @Value("${db.url}")
    private String datasourceUrl;

    @Value("${db.username}")
    private String datasourceUsername;

    @Value("${db.password}")
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
                .withExecuteLogging(false);

        org.jooq.Configuration conf = new DefaultConfiguration()
                .set(SQLDialect.POSTGRES)
                .set(new DataSourceConnectionProvider(dataSource))
                .set(settings);

        return DSL.using(conf);
    }
}
