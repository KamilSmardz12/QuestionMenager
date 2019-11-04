package pl.questionMenager;

import pl.questionMenager.transformer.file.JsonTransformer;
import pl.questionMenager.transformer.Transformer;
import pl.questionMenager.model.Question;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Question> questions = new LinkedList<>();
        Transformer transformObjectToJson = new JsonTransformer();
        transformObjectToJson.read();
        questions.add(new Question("qqq", "aaaa"));
        transformObjectToJson.save(questions);
        System.out.println(questions);
    }
}
