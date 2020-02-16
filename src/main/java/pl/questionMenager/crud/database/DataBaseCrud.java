package pl.questionMenager.crud.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.model.DifficultyLevel;
import pl.questionMenager.model.Question;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataBaseCrud implements Crud {

    private SessionFactory sessionFactory;

    public DataBaseCrud() {
        sessionFactory = new DataBaseTransformerFactory().connect();
    }

    @Override
    public void create(String question, String answer) {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(new Question(question, answer));
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    /*
    TODO modyfikator dostepu? Zeby zwyklu user nie wrzucil prostego pytania
    TODO i dal, ze jest to mega trudne pytanie (DifficultyLevel.HARD)
     */
    @Override
    public void create(DifficultyLevel difficultyLevel, String question) {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(new Question(question, null, difficultyLevel));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void create(DifficultyLevel difficultyLevel, String question, String answer) {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new Question(question, answer, difficultyLevel));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    //TODO sprawdzic.
    @Override
    public List<Question> readAll() {
        Session session = createSession();
        session.beginTransaction();
        List<Question> questions = null;
        try {
            questions = session.createQuery("from Question").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return questions;
    }

    @Override
    public List<Question> read(DifficultyLevel difficultyLevel) {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();
        List<Question> questionList = null;
        List<Question> collect = null;
        try {
            questionList = readAll();
            collect = questionList.stream()
                    .filter(e -> e.getDifficultyLevel().equals(difficultyLevel))
                    .collect(Collectors.toList());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return collect;
    }

    @Override
    public Question read(int id) {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();
        Question question = null;
        try {
            List<Question> questionList = readAll();
            Optional<Question> first = questionList.stream()
                    .filter(q -> q.getIdQuestion().equals(id))
                    .findFirst();
            question = first.get();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return question;
    }

    @Override
    public Question readRandomQuestion() {
        return null;
    }

    //TODO dokonczyc
    @Override
    public void remove(int id) {
        Session session = createSession();
        Transaction transaction = session.beginTransaction();
        try{

            //session.delete();
            transaction.commit();
        }catch (Exception e){
            if (transaction!= null){
                transaction.rollback();
            }
        }
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
