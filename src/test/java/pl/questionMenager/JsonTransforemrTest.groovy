package pl.questionMenager

import pl.questionMenager.model.Question
import pl.questionMenager.transformer.file.JsonTransformer
import spock.lang.Specification

import java.util.stream.IntStream

class JsonTransforemrTest extends Specification {

    //todo good idea to put option that allow us to generate version of file. This will make unit test a little bit easier
    def "should return object in Json Format with our configuration. {\"version\":\"0.0.1\",\"lastUpdate\":\"04-02-20 22:58\",\"questions\":[{\"question\":\"qqq\",\"answer\":\"asdasdasdsdadas\",\"difficultyLevel\":\"EMPTY\"}]}"(){
        given:
        JsonTransformer jsonTransformer = new JsonTransformer()
        List<Question> listOfQuestion = new ArrayList<>()
        for (int i = 0; i <3 ; i ++){
            listOfQuestion.add(new Question("question" + i, "answer" + i))
        }
        when:
        def rootJson = jsonTransformer.fromListOfQuestionToJsonObject(listOfQuestion).toString()
        then:
        rootJson == ""

    }

}
