package pl.questionMenager;

import pl.questionMenager.controller.Controller;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.transformer.file.JsonTransformer;
import pl.questionMenager.model.Question;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Nie generuje się id i metoda save nadpisuje plik
        JsonTransformer transformObjectToJson = new JsonTransformer();
        System.out.println(transformObjectToJson.read());
        Crud crud = Controller.create(DataType.JSON);
        crud.create("Rafal", "Rafal");
        Controller.closeWorking(DataType.JSON);
    }
}
