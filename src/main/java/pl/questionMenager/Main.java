package pl.questionMenager;

import pl.questionMenager.controller.Controller;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.model.Question;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Nie generuje się id i metoda save nadpisuje plik
        //JsonTransformer transformObjectToJson = new JsonTransformer();
        //System.out.println(transformObjectToJson.read());
        Crud crud = Controller.create(DataType.JSON);
        crud.create("piesek", "DEmon");
        crud.create("kotek","tosiek");
        crud.create("papuga","ara");
        crud.create("rybka","paletka");
        List<Question> questions = crud.readAll();
        questions.forEach(System.out::println);
        crud.updateAnswer(1,"PIESEK");
        Controller.closeWorking(DataType.JSON);
    }
}
