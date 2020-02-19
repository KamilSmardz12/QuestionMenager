package pl.questionMenager.transformer.database;

import org.hibernate.SessionFactory;
import pl.questionMenager.transformer.ConnetionFactory;
import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.model.Question;

import java.util.List;
import java.util.Map;

public class DataBaseTransformerFactory implements ConnetionFactory {

    @Override
    public SessionFactory connect() {
        return HibernateConfig.getSessionFactory();
    }

    @Override
    public SessionFactory connestTEST() {
        return HibernateConfig.getSessionFactoryForTest();
    }
}
