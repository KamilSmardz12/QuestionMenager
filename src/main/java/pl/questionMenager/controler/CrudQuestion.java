package pl.questionMenager.controler;

import pl.questionMenager.model.Question;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CrudQuestion {

    //private final List<Question> questions;
    private final Map<Integer,Question> questions;

    public CrudQuestion(Map<Integer,Question> questions) {
        this.questions = questions;
    }

    public Question create(String question, String answer) {
        //TODO przy pobieraniu pytań trzeba sprawdzić czy przychodzi pusta lista
        Integer maxPresentId = questions.stream
                .map(Question::getId)
                .max(Comparator.naturalOrder())
                .get();

        return new Question(++maxPresentId, question, answer);
    }

    public Question create(String question) {
        return create(question, null);
    }

    public String readQuestion(int id) {
        return questions.get(id).getQuestion();
    }

    public void update(int id, String newQuestion, String answer) {
        int idInList = lookForId(id);
        Question question = questions.get(idInList);
        question.setQuestion(newQuestion);
        question.setAnswer(answer);

        questions.add(idInList, question);
    }

    public void update(int id, String answer) {

    }

    public void remove(int id) {
        questions.remove(lookForId(id));
    }

    public void remove(String question) {
        Integer questionIdInList = questions.stream()
                .filter(q -> q.getQuestion().equals(question))
                .map(Question::getId)
                .findFirst()
                .orElse(null);

        if (questionIdInList == null) {
            throw new IllegalStateException(String.format("The question with this question: \"%s\"  does not exist", question));
        }

        questions.remove(questionIdInList);
    }

    //bym zaproponował nazwę lookById
    private int lookForId(int id) throws IllegalStateException {
        Integer questionIdInList = questions.stream()
                .filter(q -> q.getId() == id)
                .map(Question::getId)
                .findFirst()
                .orElse(null);

        if (questionIdInList == null) {
            throw new IllegalStateException(String.format("The question with this id number : \"%d\" does not exist", id));
        }

        return questionIdInList;
    }
}
