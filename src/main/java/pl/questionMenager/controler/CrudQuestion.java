package pl.questionMenager.controler;

import pl.questionMenager.model.Question;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

//TODO
public class CrudQuestion {

    //private final List<Question> questions;
    private final Map<Integer, Question> questions;

    public CrudQuestion(Map<Integer, Question> questions) {
        this.questions = questions;
    }

    public Question create(String question, String answer) {
        return null;
    }

    public Question create(String question) {
        return null;
    }

    public String readQuestion(int id) {
        return "";
    }

    public void update(int id, String newQuestion, String answer) {

    }

    public void update(int id, String answer) {

    }

    public void remove(int id) {
    }

    public void remove(String question) {

    }

    //bym zaproponował nazwę lookById
    private int lookForId(int id) throws IllegalStateException {
        return 1;
    }
}
