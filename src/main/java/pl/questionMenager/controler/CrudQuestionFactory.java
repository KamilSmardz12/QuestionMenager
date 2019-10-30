package pl.questionMenager.controler;

import pl.questionMenager.model.Question;

import java.util.List;
import java.util.Map;

public class CrudQuestionFactory {
    Map<Integer, Question> questions;
    CrudQuestion crudQuestion;

    public CrudQuestionFactory(Map<Integer, Question> questions) {
        this.questions = questions;
        this.crudQuestion = new CrudQuestion(questions);
    }

    public Question create(String question, String answer){
        return crudQuestion.create(question,answer);
    }

    public void remove(int id){
        crudQuestion.remove(id);
    }

    public void remove(String question){
        crudQuestion.remove(question);
    }


}
