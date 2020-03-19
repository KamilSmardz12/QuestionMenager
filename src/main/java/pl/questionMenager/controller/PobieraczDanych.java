package pl.questionMenager.controller;

import pl.questionMenager.model.DataType;
import pl.questionMenager.user.DataToLogin;

import java.util.Scanner;

public class PobieraczDanych {

    private static final Scanner sc = new Scanner(System.in);

    public static DataToLogin logIn() {
        System.out.println("podaj login: ");
        String login = sc.next();
        System.out.println("podaj haslo: ");
        String password = sc.next();
        return DataToLogin.builder()
                .login(login)
                .password(password)
                .build();
    }

    public static DataType checkDataType() {
        DataType dataType;
        switch (sc.nextInt()) {
            case 1:
                dataType = DataType.JSON;
                break;
            case 2:
                dataType = DataType.HIBERNATE;
                break;
            default:
                dataType = DataType.HIBERNATE;
        }
        return dataType;
    }
}
