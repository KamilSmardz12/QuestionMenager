package pl.questionMenager;

import pl.questionMenager.JsonFormater.Transformer;
import pl.questionMenager.JsonFormater.TransformerFactory;
import pl.questionMenager.model.Question;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Question> questions = null;
        Transformer transformObjectToJson = new Transformer("/home/rafal/Dokumenty/PROJEKTY JAVA/QuestionMenager/src/resources/json/questions.json");
        questions = transformObjectToJson.readJsonFromFile();
        System.out.println(questions);
    }
}
