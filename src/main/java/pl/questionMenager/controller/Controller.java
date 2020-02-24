package pl.questionMenager.controller;

import org.hibernate.SessionFactory;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.crud.database.DataBaseCrud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.transformer.file.JsonTransformerFactory;
import pl.questionMenager.user.User;
import pl.questionMenager.user.UserProperties;
import pl.questionMenager.view.CrudView;

import static pl.questionMenager.utils.TransformerUtils.isDataBaseTEST;
import static pl.questionMenager.utils.TransformerUtils.isJsonData;

public class Controller {

    private static TransformerFactory transformerFactory;
    private static SessionFactory sessionFactory;
    private static Crud crud;
    private static CrudView view;
    private static UserProperties userProperties;

    public  Crud createConnectionWithData(DataType dataType) {
        checkDataType(dataType);
        return crud;
    }

    public  void closeConnection(DataType dataType) {
        if (isJsonData(dataType)) {
            transformerFactory.save(crud.readAll());
        } else if (isDataBaseTEST(dataType)) {
            sessionFactory.close();
        } else {
            //TODO bez sensu, laczysz i od razu zamykasz getCurrentSession()?????
            sessionFactory.close();
        }
    }

    private void checkDataType(DataType dataType) {
        if (isJsonData(dataType)) {
            transformerFactory = new JsonTransformerFactory();
            crud = new JsonCrud(transformerFactory.read());
        } else {
            sessionFactory = new DataBaseTransformerFactory().connect();
            crud = new DataBaseCrud(dataType);
        }
    }
    
    private UserProperties getUser(String login, String password){
        return new User(sessionFactory).getUserProperties(login,password);
    }

    public static void start(){

    }
}
