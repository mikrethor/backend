package fr.ablx.daycare.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ablx.daycare.controllers.AppErrorController;
import fr.ablx.daycare.crypto.CryptoUtils;
import fr.ablx.daycare.jpa.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//Put spring config above all classes in order to allow scan
@SpringBootApplication(scanBasePackages = {"fr.ablx.daycare.objects", "fr.ablx.daycare.controllers",
        "fr.ablx.daycare.errors", "fr.ablx.daycare.jpa", "fr.ablx.daycare.config"})
@EnableJpaRepositories(basePackages = "fr.ablx.daycare.jpa", entityManagerFactoryRef = "daycareEmf")
@ComponentScan(basePackages = {"fr.ablx.daycare.objects", "fr.ablx.daycare.controllers", "fr.ablx.daycare.errors",
        "fr.ablx.daycare.jpa", "fr.ablx.daycare.config"})
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private ErrorAttributes errorAttributes;

    // tag::entrypoint[]
    public static void main(String[] args) {
        System.getProperties().put("server.port", 8081);
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public AppErrorController appErrorController() {
        return new AppErrorController();
    }
    // end::entrypoint[]

    @Bean
    public DataSource datasource() {
        BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
        ds.setDriverClassName("org.hsqldb.jdbcDriver");
        ds.setUrl("jdbc:hsqldb:file:target/daycare_bd;create=true");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setShowSql(true);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean daycareEmf(DataSource datasource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("puMoteurAttribution");
        em.setDataSource(datasource);
        em.setPackagesToScan("fr.ablx.daycare.jpa");
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.autocommit", "true");
        properties.setProperty("hibernate.current_session_context_class", "thread");
        properties.setProperty("hibernate.default_schema", "DAYCARE");
        properties.setProperty("hibernate.connection.useUnicode", "true");
        properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        properties.setProperty("javax.persistence.schema-generation.database.action", "drop-and-create");
        properties.setProperty("javax.persistence.schema-generation.scripts.action", "drop-and-create");
        properties.setProperty("javax.persistence.schema-generation.scripts.create-target", "target/sampleCreate.ddl");
        properties.setProperty("javax.persistence.schema-generation.scripts.drop-target", "target/sampleDrop.ddl");
        properties.setProperty("javax.persistence.schema-generation.create-source", "script-then-metadata");
        // Script pour la création du schéma
        properties.setProperty("javax.persistence.schema-generation.create-script-source", "init-db.sql");
        // script pour le chargement des tables
        properties.setProperty("javax.persistence.sql-load-script-source", "init-data.sql");

        em.setJpaProperties(properties);
        return em;
    }

    @Bean
    public CommandLineRunner demo(ChildRepository childRepository, DaySumupRepository daySumupRepository,
                                  EducatorRepository educatorRepository, ParentRepository parentRepository,
                                  DayCareRepository dayCareRepository, UserRepository userRepository, AdminRepository adminRepository) {
        return (args) -> {

            Parent parent1 = parentRepository.findOne(1L);
            Parent parent2 = parentRepository.findOne(2L);

            List<Child> enfants = new ArrayList<>();

            enfants.add(childRepository.findOne(1L));
            enfants.add(childRepository.findOne(2L));

            parent1.setChildren(enfants);
            parent2.setChildren(enfants);

            parentRepository.save(parent1);
            parentRepository.save(parent2);

            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);

            String password = "test";
            String salt = CryptoUtils.getInstance().getSalt(20);
            String encryptedString = CryptoUtils.getInstance().encryption(password, salt);
            // Update les mots de passe de test lors du run
            for (User u : userRepository.findAll()) {
                u.setPassword(encryptedString);
                u.setSalt(salt);
                userRepository.save(u);
            }

            dayCareRepository.save(new Daycare("Ma garderie2"));

        };
    }

    @Bean
    public ObjectMapper mapperJsonObject() {

        return new ObjectMapper();
    }

}
