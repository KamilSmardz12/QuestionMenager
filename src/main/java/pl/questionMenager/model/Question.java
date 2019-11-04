package pl.questionMenager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Question {

    @NonNull
    private String question;
    @NonNull
    private String answer;
    private DifficultyLevel difficultyLevel;

    public Question(@NonNull String question, @NonNull String answer) {
        this.question = question;
        this.answer = answer;
        this.difficultyLevel = DifficultyLevel.EMPTY;
    }
}
