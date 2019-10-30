package pl.questionMenager

import pl.questionMenager.JsonFormater.FileTransformer
import pl.questionMenager.model.Question
import spock.lang.Specification

class TransformObjectToJsonTest extends Specification {

    def "test saveJsonToFile"() {
        given:
        FileTransformer transformObjectToJson = new FileTransformer()
        Map<Integer,Question> mapOfQuestion = Mock()

        when:
        transformObjectToJson.saveToFile(mapOfQuestion)

        then:
        assert jsonFile.exists() == true
    }

    def "if read jeson from file return list of question"() {
        given:
        FileTransformer transformObjectToJson = new FileTransformer();

        when:
        def shouldReturnListOfQuestion = transformObjectToJson.readJsonFromFile()

        then:
        assert shouldReturnListOfQuestion instanceof List<Question>
    }
}
