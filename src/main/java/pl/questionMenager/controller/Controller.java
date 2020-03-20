package pl.questionMenager.controller;

import org.hibernate.SessionFactory;
import pl.questionMenager.crud.database.DataBaseCrud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.transformer.file.JsonTransformerFactory;
import pl.questionMenager.user.DataToLogin;
import pl.questionMenager.user.User;
import pl.questionMenager.user.UserProperties;
import pl.questionMenager.view.View;

import java.util.Scanner;

import static pl.questionMenager.controller.PobieraczDanych.logIn;
import static pl.questionMenager.utils.TransformerUtils.isJsonData;

public class Controller {

    private TransformerFactory transformerFactory;
    private SessionFactory sessionFactory;
    private pl.questionMenager.crud.Crud crud;
    private View view = new View();
    private UserProperties userProperties;
    private boolean loopProgram = true;
    private Scanner sc = new Scanner(System.in);


    public void start() {
        view.welcome();
        view.selectData();
        DataType dataType = PobieraczDanych.checkDataType();
        createSuitableConnection(dataType);

        if (dataType.equals(DataType.HIBERNATE)){
            //view.login();
            DataToLogin dataToLogin = logIn();
            UserProperties user = getUser(dataToLogin.getLogin(), dataToLogin.getPassword(), sessionFactory);
            if (user != null) {
                while (loopProgram) {
                    switch (user.getPrivileges()) {
                        case 1: //user
                            view.userInterface(crud);
                            break;
                        case 2: //user with right do add question
                            view.userInterfaceWithRightToAdd(crud);
                            break;
                        case 3: //admin
                            view.adminInterface(crud);
                            break;
                        default:
                            //view.defaulte(crud);
                    }
                    stillWorking();
                }
            } else {
                System.out.println("nie znaleziono uzytkownika");
                start();
            }
        } else {
            System.out.println("for file interface");
        }


    }

    //TODO zautomatyzowac, nazwa klasy.class w if albo null ??
    public void closeConnection(DataType dataType) {
        if (isJsonData(dataType)) {
            transformerFactory.save(crud.readAll());
        } else {
            sessionFactory.close();
        }
    }


    private void createSuitableConnection(DataType dataType) {
        if (isJsonData(dataType)) {
            transformerFactory = new JsonTransformerFactory();
            crud = new JsonCrud(transformerFactory.read());
        } else if (DataType.HIBERNATE == dataType) {
            sessionFactory = new DataBaseTransformerFactory().connect();
            crud = new DataBaseCrud(dataType);
        } else {
            sessionFactory = new DataBaseTransformerFactory().connestH2();
            crud = new DataBaseCrud(dataType);
        }
    }

    private UserProperties getUser(String login, String password, SessionFactory sessionFactory) {
        return new User(sessionFactory).getUserProperties(login, password);
    }

    private void stillWorking() {
        System.out.println("jezeli chcesz zakonczyc program wpisz TAK. Natomiast NIE jesli chcesz aby program dalej działał");
        if (sc.nextLine().toLowerCase().equals("tak")) {
            this.loopProgram = false;
            view.goodBye();
        } else {
            this.loopProgram = true;
        }
    }


}
