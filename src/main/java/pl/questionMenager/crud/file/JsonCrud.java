package pl.questionMenager.crud.file;

import lombok.extern.slf4j.Slf4j;
import pl.questionMenager.crud.Crud;
import pl.questionMenager.model.DifficultyLevel;
import pl.questionMenager.model.Question;

import java.util.*;
import java.util.stream.Collectors;

//TODO ograć puste pola (tam gdzie null)
public class JsonCrud implements Crud {

    private Map<Integer, Question> questions;

    public JsonCrud(Map<Integer, Question> questions) {
        this.questions = questions;
    }

    @Override
    public void create(String question) {
        create(DifficultyLevel.EMPTY, question, null);
    }

    @Override
    public void create(String question, String answer) {
        create(DifficultyLevel.EMPTY, question, answer);
    }

    @Override
    public void create(DifficultyLevel difficultyLevel, String question) {
        create(difficultyLevel, question, null);
    }

    @Override
    public void create(DifficultyLevel difficultyLevel, String question, String answer) {
        questions.put(maxIdPlusOne(), new Question(difficultyLevel, question, answer));
    }

    @Override
    public List<Question> readAll() {
        return new LinkedList<>(questions.values());
    }

    @Override
    public List<Question> read(DifficultyLevel difficultyLevel) {
        return questions.values().stream()
                .filter(q -> q.getDifficultyLevel().equals(difficultyLevel))
                .collect(Collectors.toList());
    }

    @Override
    public Question read(int id) {
        return questions.entrySet().stream()
                .filter(q -> q.getKey().equals(id))
                .map(Map.Entry::getValue)
                .findFirst()
                .get();
    }

    @Override
    public Question readRandomQuestion() {
        Question question = null;

        int number = drawingARandomeNumberFromTheMap();

        for (Map.Entry<Integer, Question> entry : questions.entrySet()) {
            if (entry.getKey().equals(number)) {
                Question q = entry.getValue();
                question = new Question(
                        q.getDifficultyLevel(),
                        q.getQuestion(),
                        q.getAnswer());
            }
        }

        return question;
    }

    @Override
    public void updateAnswer(int id, String answer) {
        questions.entrySet().stream()
                .filter(q -> q.getKey().equals(id))
                .forEach(q -> q.getValue().setAnswer(answer));
    }

    @Override
    public void updateQuestion(int id, String question) {
        questions.entrySet().stream()
                .filter(q -> q.getKey().equals(id))
                .forEach(q -> q.getValue().setQuestion(question));
    }

    @Override
    public void updateDifficultyLevelAndAnswer(int id, DifficultyLevel difficultyLevel, String answer) {
        questions.entrySet().stream()
                .filter(q -> q.getKey().equals(id))
                .forEach(q -> {
                    Question question = q.getValue();
                    question.setDifficultyLevel(difficultyLevel);
                    question.setAnswer(answer);
                });
    }

    @Override
    public void updateDifficultyLevelAndQuestion(int id, DifficultyLevel difficultyLevel, String question) {
        questions.entrySet().stream()
                .filter(q -> q.getKey().equals(id))
                .forEach(v -> {
                    Question value = v.getValue();
                    value.setDifficultyLevel(difficultyLevel);
                    value.setQuestion(question);
                });
    }

    @Override
    public void updateAnswerAndQuestion(int id, String answer, String question) {
        questions.entrySet().stream()
                .filter(q -> q.getKey().equals(id))
                .forEach(v -> {
                    Question value = v.getValue();
                    value.setAnswer(answer);
                    value.setQuestion(question);
                });
    }

    private int drawingARandomeNumberFromTheMap() {
        Random r = new Random();
        int bound = questions.size() + 1;
        int number = r.nextInt(bound);

        while (!isInQuestion(number)) {
            number = r.nextInt(bound);
        }

        return number;
    }


    private boolean isInQuestion(int id) {
        return questions.containsKey(id);
    }

    @Override
    public void remove(int id) {
        questions.keySet()
                .removeIf(q -> q.equals(id));
    }

    private Integer maxIdPlusOne() {
        Integer integer = questions.keySet().stream()
                .max(Integer::compareTo)
                .get();
        return ++integer;
    }
}
