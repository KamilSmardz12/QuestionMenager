package pl.questionMenager;

import pl.questionMenager.controller.Controller;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.model.DifficultyLevel;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {

        Crud crud = Controller.create(DataType.HIBERNATE);
        crud.read(DifficultyLevel.)

    }
}
