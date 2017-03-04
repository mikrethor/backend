package fr.ablx.daycare.config;

import fr.ablx.daycare.controllers.AppErrorController;
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
import java.util.ArrayList;
import java.util.Date;
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
        em.setPackagesToScan(new String[]{"fr.ablx.daycare.jpa"});
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
    public CommandLineRunner demo(ChildRepository childRepository, DaySumupRepository daySumupRepository, EducatorRepository educatorRepository, ParentRepository parentRepository, DayCareRepository dayCareRepository) {
        return (args) -> {

            Daycare daycare = new Daycare("Ma garderie");
            daycare = dayCareRepository.save(daycare);

            Child arthur = new Child("Arthur", "Bouclet");
            Child louis = new Child("Louis", "Bouclet");


            arthur.setDaycare(daycare);
            louis.setDaycare(daycare);



            Educator educ1 = new Educator("Marie-Josée", "YMCA");
            Educator educ2 = new Educator("Bérengère", "Courtaux Bouclet");
            Educator educ3 = new Educator("Joe", "Tribiani");

            educ1.setDaycare(daycare);
            educ2.setDaycare(daycare);
            educ3.setDaycare(daycare);

            educ1 = educatorRepository.save(educ1);
            educ2 = educatorRepository.save(educ2);
            educ3 = educatorRepository.save(educ3);




            DaySumup daySumup1 = new DaySumup();
            daySumup1.setMood(Mood.BAD);
            daySumup1.setSleep(Sleep.GOOD);
            daySumup1.setAppetite(Appetite.GOOD);
            daySumup1.setComment("Best day ever Arthur");
            daySumup1.setDay(new Date());
            daySumup1.setChild(arthur);
          //  daySumup1.setEducator(educ1);


            DaySumup daySumup2 = new DaySumup();
            daySumup2.setMood(Mood.BAD);
            daySumup2.setSleep(Sleep.GOOD);
            daySumup2.setAppetite(Appetite.GOOD);
            daySumup2.setComment("Best day ever Louis");
            daySumup2.setDay(new Date());
         //   daySumup2.setEducator(educ2);
            daySumup2.setChild(louis);


            louis = childRepository.save(louis);
            arthur = childRepository.save(arthur);

            daySumup1 = daySumupRepository.save(daySumup1);
            daySumup2 = daySumupRepository.save(daySumup2);


            List<DaySumup> sumupsArthur = new ArrayList<>();

            sumupsArthur.add(daySumup1);

            List<DaySumup> sumupsLouis = new ArrayList<>();

            sumupsLouis.add(daySumup2);
            arthur.setDaySumups(sumupsArthur);
            louis.setDaySumups(sumupsLouis);

            arthur = childRepository.save(arthur);
            louis = childRepository.save(louis);

            Parent parent1 = new Parent("Xavier", "Bouclet");
            Parent parent2 = new Parent("Bérengère", "Bouclet");

            List<Child> enfants=new ArrayList<>();

            enfants.add(arthur);
            enfants.add(louis);

            parent1.setChildren(enfants);
            parent2.setChildren(enfants);

            parent1.setDaycare(daycare);
            parent2.setDaycare(daycare);

            parent1 = parentRepository.save(parent1);
            parent2 = parentRepository.save(parent2);



        };
    }

}
