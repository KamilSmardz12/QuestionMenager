package pl.questionMenager.crud.database;

import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pl.questionMenager.configuration.LoggerConfig;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.model.DifficultyLevel;
import pl.questionMenager.model.Question;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


//TODO sprawdzic roznice miedzy e.getMessage,  a e.getStackTrace().toString() !!!!!!!!!! pododawac obiekty do printu
//TODO dokonczyc logowanie i ładnie sformatować

public class DataBaseCrud implements Crud {

    private SessionFactory sessionFactory;
    private Session session;
    private Logger logger;

    public DataBaseCrud(@NotNull DataType dataType) {
        if (dataType.equals(DataType.H2)) {
            sessionFactory = new DataBaseTransformerFactory().connestH2();
        } else {
            sessionFactory = new DataBaseTransformerFactory().connect();
        }
        //TODO usuwanie
        session = sessionFactory.openSession();
        logger = LoggerConfig.log();
    }

    public Session getSession() {
        return session;
    }

    @Override
    public void create(String question, String answer) {
        create(DifficultyLevel.EMPTY, question, answer);
    }


    @Override
    public void create(DifficultyLevel difficultyLevel, String question) {
        create(difficultyLevel, question, null);
    }

    @Override
    public void create(DifficultyLevel difficultyLevel, String question, String answer) {
        //session.getSession().beginTransaction();
        try {
            session.save(new Question(question, answer, difficultyLevel));
            session.getTransaction().commit();
            logger.info("question have been save to database || " + "question: " + question + " answer: " + answer + " difficultLevel: " + difficultyLevel);
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.warning("something gone wrong, transaction is rollback || " + e.getStackTrace().toString());
        }
    }


    @Override
    public List<Question> readAll() {
        session.beginTransaction();
        List<Question> questions = null;
        try {
            questions = session.createQuery("from Question").getResultList();
            session.getTransaction().commit();
            logger.info("all questions were downloaded from the database");
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.warning("something gone wrong, transaction is rollback" + e.getStackTrace().toString());
        }
        return questions;
    }

    //TODO poprawic !!!!!!!!!!!!
    @Override
    public List<Question> read(DifficultyLevel difficultyLevel) {
        session.beginTransaction();
        List<Question> questions = null;
        try {
            String query = "SELECT q.question, q.answer, q.difficultyLevel FROM Questions q";
            List<Object[]> resultList = session.createSQLQuery(query).list();
            questions = createQuestions(resultList);
            session.getTransaction().commit();
            logger.info("all question with difficult level: " + difficultyLevel + " were downloaded from database");
        } catch (Exception e) {
            logger.warning(e.getStackTrace() + "");
        }
        return questions;
    }

    private List<Question> createQuestions( List<Object[]> resultList) {
        List<Question> questions = new LinkedList<>();
        for (Object[] object : resultList) {
            questions.add(new Question(
                    object[0].toString(),
                    object[1].toString(),
                    DifficultyLevel.valueOf(object[2].toString())));
        }
        return questions;
    }

    @Override
    public Question read(int id) {
        session.beginTransaction();
        Question question = null;
        try {
            question = session.get(Question.class, id);
            session.getTransaction().commit();
            logger.info("question with id: " + id + " were downloaded from database || " + question.toString());
        } catch (Exception e) {
            logger.warning("something gone wrong || " + e.getStackTrace());
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public Question readRandomQuestion() {
        Random r = new Random();
        Question question = readAll().get(r.nextInt(readAll().size()));
        logger.info("a random question have been fetch from database || " + question.toString());
        return question;
    }


    //TODO nie dziala
    @Override
    public void remove(int id) {
        session.beginTransaction();
        try {
            Question questionToRemove = session.load(Question.class, id);
            session.delete(questionToRemove);
            session.getTransaction().commit();
            logger.info("question with id:" + id + " were remove form database || " + questionToRemove.toString());
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.warning("something gone wrong, transaction is rollback || " + e.getStackTrace().toString());
        }
    }

    @Override
    public void updateAnswer(int id, String answer) {
        session.beginTransaction();
        try {
            Question questiontoUpdata = session.get(Question.class, id);
            questiontoUpdata.setAnswer(answer);
            session.update(questiontoUpdata);
            session.getTransaction().commit();
            logger.info("question to Update with id: " + id + " were update || " + questiontoUpdata.toString());
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.warning("something gone wrong, transaction is rollback || " + e.getStackTrace().toString());
        }
    }

    @Override
    public void updateQuestion(int id, String question) {
        session.beginTransaction();
        try {
            Question questionToUpdate = session.get(Question.class, id);
            questionToUpdate.setQuestion(question);
            session.update(questionToUpdate);
            session.getTransaction().commit();
            logger.info("question to Update with id: " + id + " were update || " + questionToUpdate.toString());
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.warning("something gone wrong, transaction rollback || " + e.getStackTrace().toString());
        }
    }

    @Override
    public void updateDifficultyLevelAndAnswer(int id, DifficultyLevel difficultyLevel, String answer) {
        session.beginTransaction();
        try {
            Question questionToUpdate = session.get(Question.class, id);
            questionToUpdate.setAnswer(answer);
            questionToUpdate.setDifficultyLevel(difficultyLevel.toString());
            session.update(questionToUpdate);
            session.getTransaction().commit();
            logger.info("question to update with id: " + id + " were update || " + questionToUpdate.toString());
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.warning("something gone wrong, transaction rollback || " + e.getStackTrace().toString());
        }
    }

    @Override
    public void updateDifficultyLevelAndQuestion(int id, DifficultyLevel difficultyLevel, String question) {
        Transaction transaction = session.beginTransaction();
        try {
            Question questionToUpdate = session.get(Question.class, id);
            questionToUpdate.setDifficultyLevel(difficultyLevel.toString());
            questionToUpdate.setQuestion(question);
            session.update(questionToUpdate);
            transaction.commit();
            logger.info("question to update with id: " + id + " were update || " + questionToUpdate.toString());
        } catch (Exception e) {
            transaction.rollback();
            logger.warning("something gone wrong, transaction rollback || " + e.getStackTrace().toString());
        }
    }

    @Override
    public void updateAnswerAndQuestion(int id, String answer, String question) {
        session.beginTransaction();
        try {
            Question questionToUpdate = session.get(Question.class, id);
            questionToUpdate.setAnswer(answer);
            questionToUpdate.setQuestion(question);
            session.update(questionToUpdate);
            session.getTransaction().commit();
            logger.info("question with id: " + id + " were update || " + questionToUpdate.toString());
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.warning("something gone wrong, transaction is rollback || " + e.getStackTrace().toString());
            e.printStackTrace();
        }
    }
}
