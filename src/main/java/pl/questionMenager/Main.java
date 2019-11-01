package pl.questionMenager;

import pl.questionMenager.controller.Controller;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.model.Question;

public class Main {

    public static void main(String[] args) {

        Crud controller = Controller.create(DataType.JSON);
        controller.updateAnswer(1, "Moja nowa odp");
        Question q = controller.readRandomQuestion();
        System.out.println(q);

    }
}
