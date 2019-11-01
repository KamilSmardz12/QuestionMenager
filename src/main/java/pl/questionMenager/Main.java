package pl.questionMenager;

import pl.questionMenager.controller.Controller;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.transformer.DataType;
import pl.questionMenager.transformer.file.JsonTransformer;
import pl.questionMenager.transformer.Transformer;
import pl.questionMenager.model.Question;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Crud controller = Controller.create(DataType.JSON);
        controller.updateAnswer(1, "Moja nowa odp");
        Question q = controller.readRandomQuestion();
        System.out.println(q);

    }
}
