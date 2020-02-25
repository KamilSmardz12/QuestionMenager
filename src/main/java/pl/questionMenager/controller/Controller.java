package pl.questionMenager.controller;

import org.hibernate.SessionFactory;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.crud.database.DataBaseCrud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.transformer.file.JsonTransformerFactory;
import pl.questionMenager.user.DataToLogin;
import pl.questionMenager.user.Privilege;
import pl.questionMenager.user.User;
import pl.questionMenager.user.UserProperties;
import pl.questionMenager.view.View;

import static pl.questionMenager.controller.PobieraczDanych.logIn;
import static pl.questionMenager.utils.TransformerUtils.isDataBaseTEST;
import static pl.questionMenager.utils.TransformerUtils.isJsonData;

public class Controller {

    private static TransformerFactory transformerFactory;
    private static SessionFactory sessionFactory;
    private static pl.questionMenager.crud.Crud crud;
    private static View view;
    private static UserProperties userProperties;

    public  void closeConnection(DataType dataType) {
        if (isJsonData(dataType)) {
            transformerFactory.save(crud.readAll());
        } else if (isDataBaseTEST(dataType)) {
            sessionFactory.close();
        } else {
            sessionFactory.close();
        }
    }

    private void createSuitableConnection(DataType dataType) {
        if (isJsonData(dataType)) {
            transformerFactory = new JsonTransformerFactory();
            crud = new JsonCrud(transformerFactory.read());
        } else {
            sessionFactory = new DataBaseTransformerFactory().connect();
            crud = new DataBaseCrud(dataType);
        }
    }

    private UserProperties getUser(String login, String password,SessionFactory sessionFactory){
        return new User(sessionFactory).getUserProperties(login,password);
    }

    public void start(){
        view.welcome();
        view.login();
        DataToLogin dataToLogin = logIn();
        UserProperties user = getUser(dataToLogin.getLogin(), dataToLogin.getPassword(), sessionFactory);
        switch (user.getPrivileges()){
            case USER_WITH_PRIVILEGES_TO_ADD:
                view.interfejsDlaUD();
                break;
            case ADMIN:
                view.interfejsDlaADMINA();
                break;
            default:
                view.defaulte();
        }
        view.selectData();

    }
}
