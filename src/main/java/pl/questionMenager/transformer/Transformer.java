package pl.questionMenager.transformer;

import pl.questionMenager.model.Question;

import java.util.List;
import java.util.Map;

public interface Transformer {

    void save(List<Question> mapOfQuestion);

    Map<Integer, Question> read();
}
