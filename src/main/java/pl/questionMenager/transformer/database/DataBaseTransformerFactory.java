package pl.questionMenager.transformer.database;

import org.hibernate.SessionFactory;
import pl.questionMenager.transformer.ConnetionFactory;

public class DataBaseTransformerFactory implements ConnetionFactory {

    @Override
    public SessionFactory connect() {
        return HibernateConfig.getSessionFactory();
    }

}
