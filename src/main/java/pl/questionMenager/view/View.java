package pl.questionMenager.view;

import javafx.util.Pair;
import pl.questionMenager.model.DataType;

import java.util.Scanner;

import static pl.questionMenager.model.DataType.HIBERNATE;
import static pl.questionMenager.model.DataType.JSON;

public class View {


    public void login() {
        System.out.println(ClientMessage.LOGIN);
    }


    public void goodBye() {
        System.out.println(ClientMessage.GOODBYE);
    }

    public void selectData() {
        System.out.println(ClientMessage.SELECT_THE_CONNECTION_TYPE);

    }

    public void welcome(){
        System.out.println(ClientMessage.WELCOME);
    }
}
