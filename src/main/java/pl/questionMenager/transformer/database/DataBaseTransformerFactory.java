package pl.questionMenager.transformer.database;

import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.model.Question;

import java.util.List;
import java.util.Map;

public class DataBaseTransformerFactory implements TransformerFactory {

    @Override
    public void save(List<Question> questions) {

    }

    @Override
    public Map<Integer, Question> read() {
        return null;
    }
}
