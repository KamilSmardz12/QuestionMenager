package pl.questionMenager.transformer;

import pl.questionMenager.model.Question;

import java.util.Map;

public interface Transformer {

    void save(Map<Integer, Question> mapOfQuestion);

    Map<Integer, Question> read();
}
