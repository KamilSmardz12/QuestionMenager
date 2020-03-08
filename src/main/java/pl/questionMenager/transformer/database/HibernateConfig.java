package pl.questionMenager.transformer.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import pl.questionMenager.model.Question;
import pl.questionMenager.user.UserProperties;

import java.util.Properties;

final class HibernateConfig {
    private static SessionFactory sessionFactory;

    private HibernateConfig() {
    }

    public static SessionFactory getSessionFactory(PropertiesHibernateConfig propertiesHibernateConfig) {
        if (sessionFactory == null) {
            try {
                //konfiguracja
                Configuration configuration = new Configuration();

                //wlasciwosci
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, propertiesHibernateConfig.getDriver());
                properties.put(Environment.URL, propertiesHibernateConfig.getUrl());
                properties.put(Environment.USER, propertiesHibernateConfig.getUser());
                properties.put(Environment.PASS, propertiesHibernateConfig.getPass());
                properties.put(Environment.SHOW_SQL, propertiesHibernateConfig.getShow_sql());
                properties.put(Environment.DIALECT, propertiesHibernateConfig.getDialect());
                properties.put(Environment.HBM2DDL_AUTO, propertiesHibernateConfig.getHbm2ddl_auto());

                configuration.setProperties(properties);
                //can chain method
                configuration.addAnnotatedClass(Question.class)
                        .addAnnotatedClass(UserProperties.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
