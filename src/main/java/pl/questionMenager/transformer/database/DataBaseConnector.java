package pl.questionMenager.transformer.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.questionMenager.model.Question;

 class DataBaseConnector {

    private DataBaseConnector(){}

     static SessionFactory createSessionFactory(){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Question.class);
        conf.buildSessionFactory();

        return  conf.buildSessionFactory();
    }

}
