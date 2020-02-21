package pl.questionMenager.transformer.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import pl.questionMenager.model.Question;

import java.util.Properties;

final class HibernateConfig {
    private static SessionFactory sessionFactory;

    private HibernateConfig() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                //konfiguracja
                Configuration configuration = new Configuration();

                //wlasciwosci
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/QuestionMenager");
                properties.put(Environment.USER, "user-name");
                properties.put(Environment.PASS, "RootPassword!123456!");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(properties);
                //can chain method
                configuration.addAnnotatedClass(Question.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactoryForTest(){
        if (sessionFactory == null) {
            try {
                //konfiguracja
                Configuration configuration = new Configuration();

                //wlasciwosci
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "org.h2.Driver");
                properties.put(Environment.URL, "jdbc:h2:mem:test");
                properties.put(Environment.USER, "sa");
                properties.put(Environment.PASS, "");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(properties);
                //can chain method
                configuration.addAnnotatedClass(Question.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
