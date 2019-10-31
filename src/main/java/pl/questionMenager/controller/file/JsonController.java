package pl.questionMenager.controller.file;

import pl.questionMenager.controller.Controller;
import pl.questionMenager.model.Question;

import java.util.Map;

public class JsonController implements Controller {

    private final Map<Integer, Question> questions;

    public JsonController(Map<Integer, Question> questions) {
        this.questions = questions;
    }

    @Override
    public void create(String question, String answer) {
        questions.put(maxIdPlusOne(), new Question(maxIdPlusOne(), question, answer));
    }

    @Override
    public void create(String question) {
       create(question, null);
    }

    @Override
    public String read(int id) {
        return questions.values().stream()
                .filter(q -> q.getId().equals(id))
                .map(Question::getQuestion)
                .toString();
    }

    @Override
    public String read(String question) {
        return questions.values().stream()
                .filter(q -> q.getQuestion().equals(question))
                .map(Question::getQuestion)
                .toString();
    }

    @Override
    public void update(int id, String question, String answer) {
        questions.values().stream()
                .filter(q -> q.getId().equals(id))
                .forEach(q -> {
                    q.setQuestion(question);
                    q.setAnswer(answer);
                });
    }

    @Override
    public void update(int id, String answer) {
        questions.values().stream()
                .filter(q -> q.getId().equals(id))
                .forEach(q -> q.setAnswer(answer));
    }

    @Override
    public void remove(int id) {
        questions.values()
                .removeIf(q -> q.getId().equals(id));
    }

    @Override
    public void remove(String question) {
        questions.values()
                .removeIf(q -> q.getQuestion().equalsIgnoreCase(question));
    }

    //TODO zrobić jakieś mądre generowanie id do pliku
    private Integer maxIdPlusOne() {
        return questions.values().stream()
                .map(Question::getId)
                .max(Integer::compareTo)
                .get();
    }
}
