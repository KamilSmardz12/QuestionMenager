package pl.questionMenager.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Question {

    @NonNull
    private Integer id;
    @NonNull
    private String question;
    private String answer;

    public Question(Integer id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }
}
