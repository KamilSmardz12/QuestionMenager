package pl.questionMenager;

import pl.questionMenager.JsonFormater.FileTransformer;
import pl.questionMenager.model.Question;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Question> questions = null;
        FileTransformer transformObjectToJson = new FileTransformer();
        questions = transformObjectToJson.readJsonFromFile();
        System.out.println(questions);
    }
}
