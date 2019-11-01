package pl.questionMenager.crud.database;

import pl.questionMenager.crud.Crud;
import pl.questionMenager.model.DifficultyLevel;
import pl.questionMenager.model.Question;

import java.util.List;

public class DataBaseCrud implements Crud {

    @Override
    public void create(String question) {

    }

    @Override
    public void create(String question, String answer) {

    }

    @Override
    public void create(DifficultyLevel difficultyLevel, String question) {

    }

    @Override
    public void create(DifficultyLevel difficultyLevel, String question, String answer) {

    }

    @Override
    public List<Question> read(DifficultyLevel difficultyLevel) {
        return null;
    }

    @Override
    public Question read(int id) {
        return null;
    }

    @Override
    public Question readRandomQuestion() {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void updateAnswer(int id, String answer) {

    }

    @Override
    public void updateQuestion(int id, String question) {

    }

    @Override
    public void updateDifficultyLevelAndAnswer(int id, DifficultyLevel difficultyLevel, String answer) {

    }

    @Override
    public void updateDifficultyLevelAndQuestion(int id, DifficultyLevel difficultyLevel, String question) {

    }

    @Override
    public void updateAnswerAndQuestion(int id, String answer, String question) {

    }
}
