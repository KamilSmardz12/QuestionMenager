package pl.questionMenager.controler;

import pl.questionMenager.model.Question;

import java.util.List;

public class CrudQuestionFactory {
    List<Question> questionList;
    CrudQuestion crudQuestion;

    public CrudQuestionFactory(List<Question> questionList) {
        this.questionList = questionList;
        this.crudQuestion = new CrudQuestion(questionList);
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
