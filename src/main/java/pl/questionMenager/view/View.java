package pl.questionMenager.view;

import pl.questionMenager.controler.file.CrudQuestionFactory;
import pl.questionMenager.transformer.DataType;
import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.transformer.file.JsonTransformerFactory;

public class View {

    private TransformerFactory transformerFactory;
    private CrudQuestionFactory crudQuestion;

    public View(DataType dataType) {
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
