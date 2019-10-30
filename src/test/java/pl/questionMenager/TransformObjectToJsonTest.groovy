package pl.questionMenager

import pl.questionMenager.JsonFormater.Transformer
import pl.questionMenager.model.Question
import spock.lang.Specification

import javax.json.JsonObject

class TransformObjectToJsonTest extends Specification {

    def "test saveJsonToFile"() {
        given:
        Transformer transformObjectToJson = new Transformer()
        Map<Integer,Question> mapOfQuestion = Mock()

        when:
        transformObjectToJson.saveToFile(mapOfQuestion)

        then:
        assert jsonFile.exists() == true
    }

    def "if read jeson from file return list of question"() {
        given:
        Transformer transformObjectToJson = new Transformer();

        when:
        def shouldReturnListOfQuestion = transformObjectToJson.readJsonFromFile()

        then:
        assert shouldReturnListOfQuestion instanceof List<Question>
    }
}
