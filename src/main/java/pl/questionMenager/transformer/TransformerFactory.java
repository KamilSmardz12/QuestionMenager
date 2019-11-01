package pl.questionMenager.transformer;

import pl.questionMenager.model.Question;

import java.util.List;
import java.util.Map;

public interface TransformerFactory {

    void save(List<Question> questions);

    Map<Integer, Question> read();
}
