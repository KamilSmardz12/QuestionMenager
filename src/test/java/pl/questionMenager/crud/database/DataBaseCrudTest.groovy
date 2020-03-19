package pl.questionMenager.crud.database


import pl.questionMenager.crud.Crud
import pl.questionMenager.model.DataType
import pl.questionMenager.model.DifficultyLevel
import pl.questionMenager.model.Question
import spock.lang.Specification

class DataBaseCrudTest extends Specification {

    static Crud crud = new DataBaseCrud(DataType.H2)

    def setup() {
        crud.getSession().beginTransaction()
        crud.getSession().createSQLQuery("DELETE TABLE Questions")
        crud.getSession().getTransaction().commit()
    }

    def "Create method should save question(question: test 1,answer: test 1) "() {
        given: "expected question"
        Question expectedQuestion = new Question("test 1", "test 2")
        expectedQuestion.setIdQuestion(7)

        when: "save question to database"
        crud.create("test 1", "test 2")

        then: "assertion"
        Question question = crud.read(7)
        assert question == expectedQuestion
    }

    def "create method should save question(question: test 1, difficultyLevel: EMPTY)"() {
        given:
        crud.getSession().beginTransaction()
        crud.getSession().createSQLQuery("DELETE  TABLE Questions")
        crud.getSession().getTransaction().commit()
        Question question = new Question("test 1", null, DifficultyLevel.EMPTY)
        question.setIdQuestion(7)

        when:
        crud.create(DifficultyLevel.EMPTY, "test 1")

        then:
        Question readQuestion = crud.read(7)
        assert readQuestion == question
    }


    def "read method should return question object with id = #id , answer = #answers , difficult Level = #difficultyLevel and question = #questions"() {
        expect:
        Question expected = crud.read(id)
        expected.answer == answers
        expected.difficultyLevel == difficultyLevel
        expected.question == questions

        where:
        id | answers       | difficultyLevel | questions
        1  | "odpowiedz 1" | "EMPTY"         | "pytanie 1"
        2  | "odpowiedz 2" | "EMPTY"         | "pytanie 2"
        3  | "odpowiedz 3" | "EMPTY"         | "pytanie 3"
        4  | "odpowiedz 4" | "EMPTY"         | "pytanie 4"
        5  | "odpowiedz 5" | "EMPTY"         | "pytanie 5"
        6  | "odpowiedz 6" | "EMPTY"         | "pytanie 6"
    }

    def "should read all questions from database"() {
        given:
        def allQuestions = crud.readAll().asList()

        expect:
        allQuestions.get(id - 1).question == questions
        allQuestions.get(id - 1).difficultyLevel == difficultyLevel
        allQuestions.get(id - 1).answer == answers

        where:
        id || answers       || difficultyLevel || questions
        1  || "odpowiedz 1" || "EMPTY"         || "pytanie 1"
        2  || "odpowiedz 2" || "EMPTY"         || "pytanie 2"
        3  || "odpowiedz 3" || "EMPTY"         || "pytanie 3"
        4  || "odpowiedz 4" || "EMPTY"         || "pytanie 4"
        5  || "odpowiedz 5" || "EMPTY"         || "pytanie 5"
        6  || "odpowiedz 6" || "EMPTY"         || "pytanie 6"
    }

    //TODO mock dla metody i test na porównanie obiektów!
    def "ReadRandomQuestion"() {
        when:
        def question = crud.readRandomQuestion()
        Question expectedQuestion = new Question()
        Random r = Mock(Random)
        r.nextInt() << 1
        then:
        question == expectedQuestion
    }

    def "Remove"() {
        given:
        def questions = crud.readAll().asList()
        when:
        crud.remove(1)
        then:
        crud.readAll().asList().size() != questions.size()

    }

    def "Remove 1"() {
        given:
        def questions = crud.readAll().asList()
        when:
        for (int i = 1; i <= questions.size(); i++) {
            crud.remove(i)
        }
        then:
        print crud.readAll().asList()
        crud.readAll().asList().isEmpty()
    }

    def "UpdateAnswer"() {
        when:
        crud.updateAnswer(1, "updata odpowiedz 1")
        then:
        crud.read(1).answer == "updata odpowiedz 1"
    }

    def "UpdateQuestion"() {
        when:
        crud.updateQuestion(1, "update pytanie 1")
        then:
        crud.read(1).question == "update pytanie 1"
    }

    def "UpdateDifficultyLevelAndAnswer"() {
        when:
        crud.updateDifficultyLevelAndAnswer(1, DifficultyLevel.HARD, "update odpowiedz 1")
        then:
        crud.read(1).difficultyLevel == DifficultyLevel.HARD.toString() && crud.read(1).answer == "update odpowiedz 1"
    }

    def "UpdateDifficultyLevelAndQuestion"() {
        when:
        crud.updateDifficultyLevelAndQuestion(1, DifficultyLevel.AVERAGE, "update pytanie 1")
        then:
        crud.read(1).difficultyLevel == DifficultyLevel.AVERAGE.toString() && crud.read(1).question == "update pytanie 1"
    }

    def "UpdateAnswerAndQuestion"() {
        when:
        crud.updateAnswerAndQuestion(1, "update odpowiedz 1", "update pytanie 1")
        then:
        crud.read(1).answer == "update odpowiedz 1" && crud.read(1).question == "update pytanie 1"
    }
}
