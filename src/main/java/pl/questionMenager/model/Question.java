package pl.questionMenager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Question {

    @NonNull
    private String question;
    private String answer;
    private DifficultyLevel difficultyLevel;
}
