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
    private Session session;

    public DataBaseCrud() {
        sessionFactory = new DataBaseTransformerFactory().connect();
        session = sessionFactory.openSession();
    }

    @Override
    public void create(String question, String answer) {
        Transaction transaction = session.beginTransaction();

        try {
            session.save(new Question(question, answer));
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /*
    TODO modyfikator dostepu? Zeby zwyklu user nie wrzucil prostego pytania
    TODO i dal, ze jest to mega trudne pytanie (DifficultyLevel.HARD)
     */
    @Override
    public void create(DifficultyLevel difficultyLevel, String question) {
        Transaction transaction = session.beginTransaction();

        try {
            session.save(new Question(question, null, difficultyLevel));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void create(DifficultyLevel difficultyLevel, String question, String answer) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new Question(question, answer, difficultyLevel));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public List<Question> readAll() {
        session.beginTransaction();
        List<Question> questions = null;
        try {
            questions = session.createQuery("from Question").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return questions;
    }

    @Override
    public List<Question> read(DifficultyLevel difficultyLevel) {
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
            e.printStackTrace();
        }
        return collect;
    }

    @Override
    public Question read(int id) {
        Transaction transaction = session.beginTransaction();
        Question question = null;
        try {/*
            List<Question> questionList = readAll();
            Optional<Question> first = questionList.stream()
                    .filter(q -> q.getIdQuestion().equals(id))
                    .findFirst();
            question = first.get();*/
            question = session.get(Question.class, id);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public Question readRandomQuestion() {

        return null;
    }

    @Override
    public void remove(int id) {
        Transaction transaction = session.beginTransaction();
        try {
            Question questionToRemove = session.load(Question.class, id);
            session.delete(questionToRemove);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateAnswer(int id, String answer) {
        Transaction transaction = session.beginTransaction();
        try {
            Question question = session.get(Question.class, id);
            question.setAnswer(answer);
            session.update(question);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuestion(int id, String question) {
        Transaction transaction = session.beginTransaction();
        try {
            Question question1 = session.get(Question.class, id);
            question1.setQuestion(question);
            session.update(question1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateDifficultyLevelAndAnswer(int id, DifficultyLevel difficultyLevel, String answer) {
        Transaction transaction = session.beginTransaction();
        try {
            Question question = session.get(Question.class, id);
            question.setAnswer(answer);
            question.setDifficultyLevel(difficultyLevel.toString());
            session.update(question);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateDifficultyLevelAndQuestion(int id, DifficultyLevel difficultyLevel, String question) {
        Transaction transaction = session.beginTransaction();
        try {
            Question question1 = session.get(Question.class, id);
            question1.setDifficultyLevel(difficultyLevel.toString());
            question1.setQuestion(question);
            session.update(question1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateAnswerAndQuestion(int id, String answer, String question) {
        Transaction transaction = session.beginTransaction();
        try {
            Question question1 = session.get(Question.class, id);
            question1.setAnswer(answer);
            question1.setQuestion(question);
            session.update(question1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
