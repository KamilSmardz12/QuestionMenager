package pl.questionMenager.transformer.file;

import lombok.Cleanup;
import pl.questionMenager.TimeTravelClock;
import pl.questionMenager.model.DifficultyLevel;
import pl.questionMenager.transformer.Transformer;
import pl.questionMenager.model.Question;
import pl.questionMenager.utils.TransformerUtils;

import javax.json.*;
import java.io.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static pl.questionMenager.utils.TransformerUtils.*;

public class JsonTransformer implements Transformer {

    private static final String VERSION = "version";
    private static final String LAST_UPDATE = "lastUpdate";
    private static final String QUESTION = "question";
    private static final String QUESTIONS = "questions";
    private static final String ANSWER = "answer";
    private static final String ID = "id";
    private static final String DIFFICULTY_LEVEL = "difficultyLevel";
    private static final Clock CLOCK = new TimeTravelClock(LocalDateTime.now());
    private static final String DATE_TIME_FORMATTER = "dd-MM-yy HH:mm";
    private static final String DEFAULT_FILE_PATH = "src/resources/json/questions.json";

    private final String filePath;
    private String version;

    public JsonTransformer(String filePath) {
        this.filePath = filePath;
        validateFile(filePath);
    }

    public  JsonTransformer() {
        this.filePath = DEFAULT_FILE_PATH;
        validateFile(filePath);
    }

    @Override
    public void save(List<Question> questions) {
        JsonObjectBuilder rootJsonBuilder = Json.createObjectBuilder();
        JsonArrayBuilder questionsArrayBuilder = Json.createArrayBuilder();

        questions.forEach((question) -> {
            JsonObjectBuilder questionJsonBuilder = Json.createObjectBuilder();
            JsonObject questionJson = questionJsonBuilder
                    .add(QUESTION, question.getQuestion())
                    .add(ANSWER, question.getAnswer())
                    .add(DIFFICULTY_LEVEL, question.getDifficultyLevel().toString())
                    .build();

            questionsArrayBuilder.add(questionJson);
        });

        JsonArray questionsArrayJson = questionsArrayBuilder.build();

        JsonObject rootJson = rootJsonBuilder
                .add(VERSION, calculateVersion(version))
                .add(LAST_UPDATE, setPresentDateAndTime(CLOCK, DATE_TIME_FORMATTER))
                .add(QUESTIONS, questionsArrayJson)
                .build();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(rootJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, Question> read() {

        @Cleanup Scanner scanner = null;
        @Cleanup JsonReader jsonReader = null;
        Map<Integer, Question> mapQuestions = new LinkedHashMap<>();

        //zmienić na try with resources wszędzie gdzie są strumienie
        try {
            scanner = new Scanner(new File(filePath));
            String json = scanner.nextLine();
            InputStream inputStream = new ByteArrayInputStream(json.getBytes());
            JsonReaderFactory jsonReaderFactory = Json.createReaderFactory(Collections.emptyMap());
            jsonReader = jsonReaderFactory.createReader(inputStream, UTF_8);
            JsonObject jsonObject = jsonReader.readObject();
            version = jsonObject.getString(VERSION);
            JsonArray jsonArray = jsonObject.getJsonArray(QUESTIONS);

            for (int i = 0; i < jsonArray.size(); i++) {
                mapQuestions.put(i,
                        new Question(
                                jsonArray.getJsonObject(i).getString(QUESTION),
                                jsonArray.getJsonObject(i).getString(ANSWER),
                                DifficultyLevel.valueOf(
                                        jsonArray.getJsonObject(i).getString(DIFFICULTY_LEVEL))
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        TransformerUtils.isEmptyMap(mapQuestions);

        return mapQuestions;
    }

}
