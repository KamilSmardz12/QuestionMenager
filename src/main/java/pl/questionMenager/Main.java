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

        Crud crud = Controller.create(DataType.HIBERNATE);
        crud.remove(1);
        crud.remove(2);
        crud.remove(3);
        crud.remove(4);
        List<Question> questionList = crud.readAll();
        System.out.println(questionList.size());
        Controller.closeWorking(DataType.HIBERNATE);

    }
}
