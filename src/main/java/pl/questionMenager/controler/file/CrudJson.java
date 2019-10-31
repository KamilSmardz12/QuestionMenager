package pl.questionMenager.controler.file;

import pl.questionMenager.model.Question;

import java.util.Map;

public class CrudJson {

    private final Map<Integer, Question> questions;

    public CrudJson(Map<Integer, Question> questions) {
        this.questions = questions;
    }

    public void create(String question, String answer) {
        questions.put(maxIdPlusOne(), new Question(maxIdPlusOne(), question, answer));
    }

    public void create(String question) {
        questions.put(maxIdPlusOne(), new Question(maxIdPlusOne(), question, null));
    }

    public String readQuestion(int id) {
        return questions.values().stream()
                .filter(q -> q.getId().equals(id))
                .map(Question::getQuestion)
                .toString();
    }

    public void update(int id, String question, String answer) {
        questions.values().stream()
                .filter(q -> q.getId().equals(id))
                .forEach(q -> {
                    q.setQuestion(question);
                    q.setAnswer(answer);
                });
    }

    public void update(int id, String answer) {
        questions.values().stream()
                .filter(q -> q.getId().equals(id))
                .forEach(q -> q.setAnswer(answer));
    }

    public void remove(int id) {
        questions.values()
                .removeIf(q -> q.getId().equals(id));
    }

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
