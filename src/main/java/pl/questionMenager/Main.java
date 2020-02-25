package pl.questionMenager;

import pl.questionMenager.controller.Controller;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.model.DifficultyLevel;
import pl.questionMenager.model.Question;

import java.lang.reflect.Method;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Crud crud = controller.createConnectionWithData(DataType.HIBERNATE);
        System.out.println(crud.read(1));

    }
}
