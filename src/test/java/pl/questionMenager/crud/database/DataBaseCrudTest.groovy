package pl.questionMenager.crud.database

import org.hibernate.SessionFactory
import pl.questionMenager.controller.Controller
import pl.questionMenager.crud.Crud
import pl.questionMenager.model.DataType
import pl.questionMenager.model.DifficultyLevel
import pl.questionMenager.model.Question
import pl.questionMenager.transformer.database.HibernateConfig
import spock.lang.Shared
import spock.lang.Specification

import java.awt.List

class DataBaseCrudTest extends Specification {

    def @Shared
    Crud crud

    def setupSpec() {
        crud = Controller.create(DataType.DATABASETEST)
    }

    def cleanupSpec() {
        Controller.closeWorking(DataType.DATABASETEST)
    }


    def "Create method should save question(question: test 1,answer: test 1) "() {
        given: "expected question"
        Question expectedQuestion = new Question("test 1", "test 2")
        expectedQuestion.setIdQuestion(1)

        when: "save question to database"
        crud.create("test 1", "test 2")

        then: "assertion"
        Question question = crud.read(1)
        assert question == expectedQuestion
    }

    def "create method should save question(question: test 1, difficultyLevel: EMPTY)"() {
        given:
        Question question = new Question("test 1", null, DifficultyLevel.EMPTY)
        question.setIdQuestion(1)

        when:
        crud.create(DifficultyLevel.EMPTY, "test 1")

        then:
        Question readQuestion = crud.read(1)
        assert readQuestion == question
    }

    def "TestCreate1"() {
        given:
        Question question = new Question(1, "pytanie 1", "odpowiedz 1", "EMPTY")

        when:
        Question readQuestion = crud.read(1)

        then:
        assert question == readQuestion
    }

    def "ReadAll"() {

        given:
        List expected = Arrays.asList(
                new Question("question 1", "answer 1"),
                new Question('pytanie 1','odpowiedz 1',DifficultyLevel.EMPTY)
        )
        crud.create("question 1", "answer 1")
        when:
        List questions = crud.readAll()
        then:
        assert questions == expected
    }

    def "should read object with id"() {
        given:
        /*crud.create("test 1", "test 1")
        crud.create("test 2", "test 2")*/
        when:
        Question question = crud.read(2)
        then:
        question.equals(new Question(2, "test 2", "test 2", "EMPTY"))
    }

    def "TestRead"() {
        /*given:
        Question question1 = new Question(1,"pytanie 1","odpowiedz 1","EMPTY")
        when:
        Question question = crud.read(1)
        then:
        question.equals(question1)
*/
    }

    def "ReadRandomQuestion"() {
    }

    def "Remove"() {
    }

    def "UpdateAnswer"() {
    }

    def "UpdateQuestion"() {
    }

    def "UpdateDifficultyLevelAndAnswer"() {
    }

    def "UpdateDifficultyLevelAndQuestion"() {
    }

    def "UpdateAnswerAndQuestion"() {
    }
}
