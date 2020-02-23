package pl.questionMenager.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserChecker {
    private final Session session;
    private User user;

    public UserChecker(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    public User getUser(String login, String password) {
        session.beginTransaction();
        try {
            String query = "FROM User WHERE User.login = " + login + " User.password =" + password;
            user = (User) session.createQuery(query).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        if (userChecker())
            System.out.println("User not exist. You must crate new user!");
        return user;
    }

    public boolean userChecker() {
        return user == null;
    }
}
