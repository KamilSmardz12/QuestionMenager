package pl.questionMenager

import pl.questionMenager.controler.CrudQuestion
import pl.questionMenager.model.Question
import spock.lang.Specification
import spock.lang.Unroll

class CrudFileTest extends Specification {

    @Unroll
    def "check if create method create new Question"() {
        given:
        List<Question> questions = [new Question(currentId, question, answer)]
        CrudQuestion crud = new CrudQuestion(questions)

        when:
        crud.create(question, answer)

        then:
        assert crud.questions == questions
        assert crud.questions.get(--currentId).id == questions.get(currentId).id

        where:
        currentId | question    | answer
        1         | "question1" | "answer1"
        1         | "question2" | "answer2"
        1         | "question3" | "answer3"
    }

    def "check if id is correct"() {
        given:
        List<Question> questions = [new Question(1, "question1", "answer1"),
                                    new Question(2, "question2", "answer2"),
                                    new Question(3, "question3", "answer3"),
                                    new Question(4, "question4", "answer4"),
                                    new Question(5, "question5", "answer5"),
                                    new Question(6, "question6", "answer6"),
                                    new Question(7, "question7", "answer7"),
                                    new Question(8, "question8", "answer8"),
                                    new Question(9, "question9", "answer9")]
        CrudQuestion crud = new CrudQuestion(questions)

        when:
        crud.create("question1", "answer1")
        crud.create("question2", "answer2")
        crud.create("question3", "answer3")
        crud.create("question4", "answer4")
        crud.create("question5", "answer5")
        crud.create("question6", "answer6")
        crud.create("question7", "answer7")
        crud.create("question8", "answer8")
        crud.create("question9", "answer9")

        then:
        assert crud.questions.get(8).id == questions.get(8).id
        assert crud.questions.get(5) == questions.get(5)

    }

}
