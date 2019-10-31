package pl.questionMenager.controler;

import pl.questionMenager.controler.file.CrudQuestionFactory;
import pl.questionMenager.transformer.DataType;
import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.transformer.file.JsonTransformerFactory;

public class Controller {
    private TransformerFactory transformerFactory;
    private CrudQuestionFactory crudQuestion;

    public Controller(DataType dataType) {
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
