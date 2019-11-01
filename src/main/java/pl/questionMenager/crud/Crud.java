package pl.questionMenager.crud;

import pl.questionMenager.model.DifficultyLevel;
import pl.questionMenager.model.Question;

import java.util.List;

public interface Crud {
    void create(String question);

    void create(String question, String answer);

    void create(DifficultyLevel difficultyLevel, String question);

    void create(DifficultyLevel difficultyLevel, String question, String answer);

    List<Question> readAll();

    List<Question> read(DifficultyLevel difficultyLevel);

    Question read(int id);

    Question readRandomQuestion();

    void remove(int id);

    void updateAnswer(int id, String answer);

    void updateQuestion(int id, String question);

    void updateDifficultyLevelAndAnswer(int id, DifficultyLevel difficultyLevel, String answer);

    void updateDifficultyLevelAndQuestion(int id, DifficultyLevel difficultyLevel, String question);

    void updateAnswerAndQuestion(int id, String answer, String question);

}
