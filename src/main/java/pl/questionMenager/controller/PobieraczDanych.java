package pl.questionMenager.controller;

import pl.questionMenager.model.DataType;
import pl.questionMenager.user.DataToLogin;

import java.util.Scanner;

public class PobieraczDanych {

    private static final Scanner sc = new Scanner(System.in);

    public static DataToLogin logIn() {
        return DataToLogin.builder()
                .login(sc.nextLine())
                .password(sc.nextLine())
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
