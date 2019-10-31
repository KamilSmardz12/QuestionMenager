package pl.questionMenager.controler.file;

import pl.questionMenager.model.Question;

import java.util.Map;

public class CrudQuestionFactory {
    Map<Integer, Question> questions;
    CrudJson crudQuestion;

    public CrudQuestionFactory(Map<Integer, Question> questions) {
        this.questions = questions;
        this.crudQuestion = new CrudJson(questions);
    }


}
