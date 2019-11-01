package pl.questionMenager.controller;

import pl.questionMenager.crud.Crud;
import pl.questionMenager.crud.database.DataBaseCrud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.transformer.DataType;
import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.transformer.file.JsonTransformerFactory;
import pl.questionMenager.view.CrudView;

public class Controller {

    private static TransformerFactory transformerFactory;
    private static Crud crud;
    private static CrudView view;

    public static Crud create(DataType dataType) {
        checkDataType(dataType);
        return crud;
    }

    private static void checkDataType(DataType dataType) {
        if (dataType == DataType.JSON) {
            transformerFactory = new JsonTransformerFactory();
            crud = new JsonCrud(transformerFactory.read());
        } else if (dataType == DataType.DATABASE) {
            transformerFactory = new DataBaseTransformerFactory();
            crud = new DataBaseCrud();
        }
    }
}
