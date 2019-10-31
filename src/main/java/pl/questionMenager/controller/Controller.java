package pl.questionMenager.controller;

import pl.questionMenager.model.Question;

public interface Controller {
    void create(String question, String answer);

    void create(String question);

    String read(int id);

    String read(String question);

    void update(int id, String question, String answer);

    void update(int id, String answer);

    void remove(int id);

    void remove(String question);
}
