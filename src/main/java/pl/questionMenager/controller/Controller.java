package pl.questionMenager.controller;

import pl.questionMenager.crud.Crud;
import pl.questionMenager.crud.database.DataBaseCrud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.transformer.ConnetionFactory;
import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.transformer.file.JsonTransformerFactory;
import pl.questionMenager.view.CrudView;

import static pl.questionMenager.utils.TransformerUtils.*;

public class Controller {

    private static TransformerFactory transformerFactory;
    private static ConnetionFactory connetionFactory;
    private static Crud crud;
    private static CrudView view;

    public static Crud create(DataType dataType) {
        checkDataType(dataType);
        return crud;
    }

    public static void closeWorking(DataType dataType) {
        if (isJsonData(dataType)) {
            transformerFactory.save(crud.readAll());
        } else if (isDataBaseTEST(dataType)) {
            connetionFactory.connestTEST().close();
        } else {
            //TODO bez sensu, laczysz i od razu zamykasz getCurrentSession()?????
            connetionFactory.connect().close();
        }
    }

    private static void checkDataType(DataType dataType) {
        if (isJsonData(dataType)) {
            transformerFactory = new JsonTransformerFactory();
            crud = new JsonCrud(transformerFactory.read());
        } else {
            connetionFactory = new DataBaseTransformerFactory();
            crud = new DataBaseCrud(dataType);
        }
    }
}
