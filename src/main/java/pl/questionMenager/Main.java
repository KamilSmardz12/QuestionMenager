package pl.questionMenager;

import pl.questionMenager.controller.Controller;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.model.DataType;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        // Nie generuje się id i metoda save nadpisuje plik
        //JsonTransformer transformObjectToJson = new JsonTransformer();
        /*//System.out.println(transformObjectToJson.read());
        Crud crud = Controller.create(DataType.DATABASE);
        crud.create("piesek", "DEmon");
        crud.create("kotek","tosiek");
        crud.create("papuga","ara");
        crud.create("rybka","paletka");
        crud.updateAnswer(1,"PIESEK");
        System.out.println(crud.readRandomQuestion());
        Controller.closeWorking(DataType.DATABASE);*/

    }
}
