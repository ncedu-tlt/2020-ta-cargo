package com.netcracker.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.SocketUtils;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;
import ru.yandex.qatools.embed.postgresql.PostgresExecutable;
import ru.yandex.qatools.embed.postgresql.PostgresProcess;
import ru.yandex.qatools.embed.postgresql.PostgresStarter;
import ru.yandex.qatools.embed.postgresql.config.AbstractPostgresConfig;
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig;
import ru.yandex.qatools.embed.postgresql.distribution.Version;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static java.lang.String.format;

@Configuration
@EnableTransactionManagement
@Profile("IntegrationTest")
@EnableJpaRepositories("com.netcracker.repository")
public class EmbeddedPostgresConfiguration {

    private static final List<String> DEFAULT_ADDITIONAL_INIT_DB_PARAMS = Collections.singletonList("--nosync");
    private static HikariDataSource dataSource;
    private static PostgresProcess postgresProcess;

    @Bean(destroyMethod = "stop")
    public PostgresProcess postgresProcess() throws IOException {

        String binariesDir = System.getProperty("java.io.tmpdir") + "/postgres_binaries";

        PostgresConfig postgresConfig = postgresConfig() ;

        PostgresStarter<PostgresExecutable, PostgresProcess> runtime =
                PostgresStarter.getInstance(EmbeddedPostgres.cachedRuntimeConfig(Paths.get(binariesDir)));

        PostgresExecutable exec = runtime.prepare(postgresConfig);

        postgresProcess = exec.start();

        return postgresProcess;
    }

    @Bean
    @DependsOn("postgresProcess")
    @Qualifier("testDAtaSource")
    public HikariDataSource dataSource(PostgresConfig config){
        if(dataSource == null){
            HikariDataSource ds = new HikariDataSource();
            ds.setDriverClassName("org.postgresql.Driver");
            ds.setJdbcUrl(format(
                    "jdbc:postgresql://%s:%s/%s",
                    config.net().host(),
                    config.net().port(),
                    config.storage().dbName()));
            ds.setUsername(config.credentials().username());
            ds.setPassword(config.credentials().password());
            ds.setMaximumPoolSize(20);
            ds.setMaxLifetime(10000);
            ds.setMinimumIdle(5);
            ds.setConnectionTimeout(30000);
            ds.setLeakDetectionThreshold(1000);
            dataSource = ds;
        }
        return dataSource;
    }

    @Bean
    public PostgresConfig postgresConfig() throws IOException{
        final PostgresConfig postgresConfig = new PostgresConfig(
                Version.V10_3,
                new AbstractPostgresConfig.Net("localhost", SocketUtils.findAvailableTcpPort()),
                new AbstractPostgresConfig.Storage("postgres"),
                new AbstractPostgresConfig.Timeout(),
                new AbstractPostgresConfig.Credentials("postgres", "0000")
        );
        postgresConfig.getAdditionalInitDbParams().addAll(DEFAULT_ADDITIONAL_INIT_DB_PARAMS);
        return postgresConfig;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("testDAtaSource") HikariDataSource dataSource){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter
                = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();

        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.netcracker.model", "com.netcracker.repository");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        localContainerEntityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return localContainerEntityManagerFactoryBean;
    }

    private Properties getHibernateProperties(){
        Properties ps = new Properties();
        ps.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        ps.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        ps.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        ps.put("hibernate.hbm2ddl.auto", "create");
        ps.put("hibernate.connection.characterEncoding", "UTF-8");
        ps.put("hibernate.connection.charSet", "UTF-8");
        ps.put(AvailableSettings.FORMAT_SQL, "true");
        ps.put(AvailableSettings.SHOW_SQL, "true");
        return ps;
    }
}
