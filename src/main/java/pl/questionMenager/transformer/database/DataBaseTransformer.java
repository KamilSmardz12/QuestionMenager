package pl.questionMenager.transformer.database;

import pl.questionMenager.transformer.Transformer;
import pl.questionMenager.model.Question;

import java.util.Map;

public class DataBaseTransformer implements Transformer {

    @Override
    public void save(Map<Integer, Question> mapOfQuestion) {

    }

    @Override
    public Map<Integer, Question> read() {
        return null;
    }
}
