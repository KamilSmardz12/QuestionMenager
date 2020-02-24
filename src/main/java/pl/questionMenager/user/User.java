package pl.questionMenager.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class User {

    private final Session session;

    public User(SessionFactory sessionFactory) {
        session = sessionFactory.openSession();
    }

    public UserProperties getUserProperties(String login, String password) {
        String privileges = null;
        session.beginTransaction();
        try {
            String query = "SELECT u.privileges FROM User AS u WHERE u.login = " + login + " u.password =" + password;
            privileges = (String) session.createQuery(query).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return UserProperties.builder()
                .login(login)
                .password(password)
                .name("")
                .privileges(setPrivileges(privileges))
                .build();
    }

    private Privilege setPrivileges(String privileges) {
        return privileges == null ? Privilege.DEFAULT : Privilege.valueOf(privileges);
    }
}
