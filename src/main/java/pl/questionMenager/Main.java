package pl.questionMenager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import pl.questionMenager.controller.Controller;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.crud.database.DataBaseCrud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.model.DifficultyLevel;
import pl.questionMenager.model.Question;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.user.Privilege;
import pl.questionMenager.user.User;
import pl.questionMenager.user.UserProperties;
import pl.questionMenager.view.ClientMessage;

import javax.persistence.Parameter;
import java.lang.reflect.Method;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.start();
/*
        User user = new User(new DataBaseTransformerFactory().connect());
        UserProperties userProperties = user.getUserProperties("admin", "admin");
        System.out.println(userProperties.getPrivileges());*/
    }
}
