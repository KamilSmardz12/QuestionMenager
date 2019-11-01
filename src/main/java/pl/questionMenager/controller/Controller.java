package pl.questionMenager.controller;

import pl.questionMenager.model.DifficultyLevel;

import java.util.List;

public interface Controller {
    void create(String question);

    void create(String question, String answer);

    void create(DifficultyLevel difficultyLevel, String question);

    void create(DifficultyLevel difficultyLevel, String question, String answer);

    List<String> read(DifficultyLevel difficultyLevel);

    String read(int id);

    void remove(int id);

    void updateAnswer(int id, String answer);

    void updateQuestion(int id, String question);

    void updateDifficultyLevelAndAnswer(int id, DifficultyLevel difficultyLevel, String answer);

    void updateDifficultyLevelAndQuestion(int id, DifficultyLevel difficultyLevel, String question);

    void updateAnswerAndQuestion(int id, String answer, String question);
}
