package pl.questionMenager.controller;

import pl.questionMenager.transformer.DataType;
import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.transformer.file.JsonTransformerFactory;
import pl.questionMenager.view.CrudView;

public class DemoController {

    private TransformerFactory transformerFactory;
    private Controller controller;
    private CrudView view;

    public DemoController(DataType dataType) {
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
