package pl.questionMenager.transformer.file;

import pl.questionMenager.transformer.TransformerFactory;
import pl.questionMenager.model.Question;

import javax.json.JsonObject;
import java.util.List;
import java.util.Map;

public class JsonTransformerFactory implements TransformerFactory {

    private final JsonTransformer jsonTransformer;

    public JsonTransformerFactory(String filePath) {
        jsonTransformer = new JsonTransformer(filePath);
    }

    public JsonTransformerFactory(){
        this.jsonTransformer = new JsonTransformer();
    }

    @Override
    public Map<Integer, Question> read() {
        return jsonTransformer.read();
    }

    @Override
    public void save(List<Question> questions) {
        jsonTransformer.save(questions);
    }
}
