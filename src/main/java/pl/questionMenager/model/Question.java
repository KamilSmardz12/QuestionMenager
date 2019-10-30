package pl.questionMenager.model;

import com.sun.istack.internal.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {

    @NotNull
    private Integer id;
    @NotNull
    private String question;
    private String answer;

    public Question(Integer id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public Question(){}
}
