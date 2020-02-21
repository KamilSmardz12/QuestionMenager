package pl.questionMenager.crud.database

import org.hibernate.SessionFactory
import pl.questionMenager.controller.Controller
import pl.questionMenager.crud.Crud
import pl.questionMenager.model.DataType
import pl.questionMenager.model.Question
import pl.questionMenager.transformer.database.HibernateConfig
import spock.lang.Shared
import spock.lang.Specification

class DataBaseCrudTest extends Specification {

    def @Shared Crud crud

    def setupSpec() {
        crud = Controller.create(DataType.DATABASETEST)
    }

    def cleanupSpec() {
        Controller.closeWorking(DataType.DATABASETEST)
    }


    def "Create"() {
        given:
        Question question1 = new Question("test 1", "test 1")
        question1.setIdQuestion(1)

        when:
        crud.create("test 1", "test 1")

        then:
        Question question = crud.read(1)
        question == question1
    }

    def "TestCreate"() {
    }

    def "TestCreate1"() {
    }

    def "ReadAll"() {
    }

    def "Read"() {
    }

    def "TestRead"() {
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
