package pl.questionMenager.crud.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.model.DifficultyLevel;
import pl.questionMenager.model.Question;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;

import java.util.List;

public class DataBaseCrud implements Crud {

    private SessionFactory sessionFactory;

    public DataBaseCrud() {
        sessionFactory = new DataBaseTransformerFactory().connect();
    }

    @Override
    public void create(String question, String answer) {
        Session session = createSession();
        session.beginTransaction();

        try {
            session.save(
                    new Question(
                            question,
                            answer)
            );
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void create(DifficultyLevel difficultyLevel, String question) {

    }

    @Override
    public void create(DifficultyLevel difficultyLevel, String question, String answer) {

    }

    @Override
    public List<Question> readAll() {
        Session sesion = createSession();
        sesion.beginTransaction();
        List<Question> questions = null;
        try {
            questions = sesion.createQuery("from Question").getResultList();
            sesion.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            sesion.getTransaction().rollback();
        } finally {
            sesion.close();
        }
        return questions;
    }

    @Override
    public List<Question> read(DifficultyLevel difficultyLevel) {
        return null;
    }

    @Override
    public Question read(int id) {
        return null;
    }

    @Override
    public Question readRandomQuestion() {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void updateAnswer(int id, String answer) {

    }

    @Override
    public void updateQuestion(int id, String question) {

    }

    @Override
    public void updateDifficultyLevelAndAnswer(int id, DifficultyLevel difficultyLevel, String answer) {

    }

    @Override
    public void updateDifficultyLevelAndQuestion(int id, DifficultyLevel difficultyLevel, String question) {

    }

    @Override
    public void updateAnswerAndQuestion(int id, String answer, String question) {

    }

    private Session createSession() {
        return sessionFactory.getCurrentSession();
    }
}
