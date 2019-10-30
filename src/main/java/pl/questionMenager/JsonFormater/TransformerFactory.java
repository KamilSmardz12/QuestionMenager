package pl.questionMenager.JsonFormater;

import pl.questionMenager.model.Question;

import java.util.List;
import java.util.Map;

public class TransformerFactory {

    private final String filePath;
    private final Transformer transformer;

    public TransformerFactory(String filePath) {
        this.filePath = filePath;
        transformer = new Transformer(filePath);
    }

    public Map<Integer, Question> read() {
        return transformer.readJsonFromFile();
    }

    public void save(Map<Integer,Question> questions) {
        transformer.saveToFile(questions);
    }
}
