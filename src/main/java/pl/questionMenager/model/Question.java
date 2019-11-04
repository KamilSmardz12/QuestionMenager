package pl.questionMenager.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Question {

    @NonNull
    private String question;
    @NonNull
    private String answer;
    private DifficultyLevel difficultyLevel;
}
