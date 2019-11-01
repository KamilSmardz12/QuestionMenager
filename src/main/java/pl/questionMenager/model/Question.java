package pl.questionMenager.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Question {

    @NonNull
    private String question;
    private String answer;
    private DifficultyLevel difficultyLevel;

    public Question(DifficultyLevel difficultyLevel, String question, String answer) {
        this.difficultyLevel = difficultyLevel;
        this.question = question;
        this.answer = answer;
    }
}
