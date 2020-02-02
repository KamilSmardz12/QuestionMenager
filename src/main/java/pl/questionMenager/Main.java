package pl.questionMenager;

import pl.questionMenager.transformer.file.JsonTransformer;
import pl.questionMenager.model.Question;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Nie generuje się id i metoda save nadpisuje plik
        List<Question> questions = new LinkedList<>();
        JsonTransformer transformObjectToJson = new JsonTransformer();
        System.out.println(transformObjectToJson.read());
        questions.add(new Question("qqq", "aaaa"));
        transformObjectToJson.save(questions);

//        DataBaseCrud dataBaseCrud = new DataBaseCrud();
//        dataBaseCrud.create("g11", "a11");
//        dataBaseCrud.readAll().forEach(System.out::println);

//        String string = null;
//         if("A".equals(string))
//             System.out.println("Hello");
//         if(string.equals("A"))
//             System.out.println("Hello");

    }
}
