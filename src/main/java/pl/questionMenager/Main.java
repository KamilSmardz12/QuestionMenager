package pl.questionMenager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.questionMenager.controller.Controller;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.model.DifficultyLevel;
import pl.questionMenager.model.Question;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.user.Privilege;
import pl.questionMenager.user.UserProperties;

import java.lang.reflect.Method;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.start();

    }
}
