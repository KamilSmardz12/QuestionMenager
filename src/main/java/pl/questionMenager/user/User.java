package pl.questionMenager.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.questionMenager.configuration.LoggerConfig;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class User {

    private final Session session;
    private final Logger log = LoggerConfig.log();

    public User(SessionFactory sessionFactory) {
        session = sessionFactory.openSession();
    }

    //TODO napisac testy i sprawdzic
    public UserProperties getUserProperties(String login, String password) {
        UserProperties userProperties = null;
        session.beginTransaction();
        try {
            String query = "SELECT u.privileges FROM User AS u WHERE u.login = " + "\"" + login + "\"" + " AND" + " u.password = " + "\"" + password + "\"";
            List list = session.createSQLQuery(query).list();
            if (list.isEmpty()) {
                log.warning("The privilege of user is empty!!");
            } else {
                Optional<Integer> optionalPrivileges = Optional.of((Integer) list.get(0));
                if (optionalPrivileges.isPresent()) {
                    userProperties = UserProperties.builder()
                            .login(login)
                            .password(password)
                            .name("")
                            .privileges(optionalPrivileges.get())
                            .build();
                }
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return userProperties;
    }

    private Privilege setPrivileges(String privileges) {
        return privileges == null ? Privilege.DEFAULT : Privilege.valueOf(privileges);
    }
}
