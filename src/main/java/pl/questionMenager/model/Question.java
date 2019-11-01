package pl.questionMenager.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Question {

    private DifficultyLevel difficultyLevel;
    @NonNull
    private String question;
    private String answer;

    public Question(DifficultyLevel difficultyLevel, String question, String answer) {
        this.difficultyLevel = difficultyLevel;
        this.question = question;
        this.answer = answer;
    }
}
