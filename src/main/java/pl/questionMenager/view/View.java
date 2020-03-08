package pl.questionMenager.view;

import pl.questionMenager.crud.Crud;

import java.util.Scanner;

public class View {
    private Scanner sc = new Scanner(System.in);

    //TODO
    public void login() {
        System.out.println(ClientMessage.LOGIN.getMessage());
    }

    //TODO
    public void goodBye() {
        System.out.println(ClientMessage.GOODBYE);
    }

    //TODO
    public void selectData() {
        System.out.println(ClientMessage.SELECT_THE_CONNECTION_TYPE.getMessage());

    }

    //TODO
    public void welcome() {
        System.out.println(ClientMessage.WELCOME.getMessage());
    }

    public void defaulte(Crud crud) {
        System.out.println("wybierz opcje");
        System.out.println("1");
        switch (sc.nextInt()) {
            case 1:
                System.out.println("podaj id pytania:");
                System.out.println(crud.read(sc.nextInt()));
                break;
            case 2:
                break;
        }
    }
}
