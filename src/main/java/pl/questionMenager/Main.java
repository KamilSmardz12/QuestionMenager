package pl.questionMenager;

import pl.questionMenager.controller.file.JsonController;
import pl.questionMenager.transformer.file.JsonTransformer;
import pl.questionMenager.transformer.Transformer;
import pl.questionMenager.model.Question;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Question> questions = new HashMap<>();
        Transformer transformObjectToJson = new JsonTransformer();
        questions = transformObjectToJson.read();
        System.out.println(questions);
        JsonController controller = new JsonController(questions);
        controller.updateAnswer(1, "Moja nowa odp");
        Question question = controller.readRandomQuestion();
        System.out.println(question);
//        questions.put(1, new Question(1, "q", "a"));
//        transformObjectToJson.save(questions);
        System.out.println(questions);
    }
}
