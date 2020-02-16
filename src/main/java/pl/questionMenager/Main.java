package pl.questionMenager;

import pl.questionMenager.controller.Controller;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.transformer.file.JsonTransformer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonTransformer transformObjectToJson = new JsonTransformer();
        System.out.println(transformObjectToJson.read());
        Crud crud = Controller.create(DataType.JSON);
        crud.create("dassda", "asdasdasdsdadas");
        Controller.closeWorking(DataType.JSON);
    }
}
