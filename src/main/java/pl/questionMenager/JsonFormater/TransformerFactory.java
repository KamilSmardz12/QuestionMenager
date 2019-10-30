package pl.questionMenager.JsonFormater;

import pl.questionMenager.model.Question;

import java.util.Map;

public class TransformerFactory {

    private final FileTransformer fileTransformer;

    public TransformerFactory(String filePath) {
        fileTransformer = new FileTransformer(filePath);
    }

    public Map<Integer, Question> read() {
        return fileTransformer.readJsonFromFile();
    }

    public void save(Map<Integer,Question> questions) {
        fileTransformer.saveToFile(questions);
    }
}
