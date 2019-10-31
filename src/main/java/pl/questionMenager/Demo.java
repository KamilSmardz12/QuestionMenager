package pl.questionMenager;

import pl.questionMenager.controller.Controller;
import pl.questionMenager.transformer.DataType;
import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.transformer.file.JsonTransformerFactory;
import pl.questionMenager.view.CrudView;

public class Demo {

    private TransformerFactory transformerFactory;
    private Controller controller;
    private CrudView view;

    public Demo(DataType dataType) {
        checkDataType(dataType);
    }

    private void checkDataType(DataType dataType) {
        if (dataType == DataType.JSON) {
            this.transformerFactory = new JsonTransformerFactory();
//            this.crudQuestion = transformerFactory.
        } else if (dataType == DataType.DATABASE) {
            this.transformerFactory = new DataBaseTransformerFactory();
        }
    }
}
