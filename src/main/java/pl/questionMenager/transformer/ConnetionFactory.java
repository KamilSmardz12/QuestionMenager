package pl.questionMenager.transformer;

import org.hibernate.SessionFactory;

public interface ConnetionFactory {

    SessionFactory connect();
    SessionFactory connestH2();

}
