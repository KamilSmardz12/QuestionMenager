package pl.questionMenager.transformer.database;

import org.hibernate.SessionFactory;
import pl.questionMenager.transformer.ConnetionFactory;

public class DataBaseTransformerFactory implements ConnetionFactory {


    @Override
    public SessionFactory connect() {

        return HibernateConfig.getSessionFactory(PropertiesHibernateConfig.builder()
                .url("jdbc:mysql://localhost:3306/QuestionMenager")
                .driver("com.mysql.jdbc.Driver")
                .dialect("org.hibernate.dialect.MySQL5Dialect")
                .user("user-name")
                .pass("RootPassword!123456!")
                .show_sql("true")
                .hbm2ddl_auto("update")
                .build()
        );
    }

    @Override
    public SessionFactory connestH2() {
        return HibernateConfig.getSessionFactory(PropertiesHibernateConfig.builder()
                .url("jdbc:h2:mem:test")
                .driver("org.h2.Driver")
                .dialect("org.hibernate.dialect.H2Dialect")
                .user("sa")
                .pass("")
                .show_sql("true")
                .hbm2ddl_auto("create") //ihbm2ddl_auto have to be set for "create", otherwise hibernate won't import import.sql file
                .build()
        );
    }
}
