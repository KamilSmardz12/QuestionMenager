package pl.questionMenager.transformer;

import pl.questionMenager.model.Question;

import java.util.Map;

public interface TransformerFactory {

    void save(Map<Integer, Question> mapOfQuestion);

    Map<Integer, Question> read();
}
