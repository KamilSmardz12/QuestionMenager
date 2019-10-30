package pl.questionMenager.JsonFormater;

import pl.questionMenager.TimeTravelClock;
import pl.questionMenager.Utils.TransformerUtils;
import pl.questionMenager.model.Question;


import javax.json.*;
import java.io.*;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;


public class FileTransformer {

    private static final String VERSION = "version";
    private static final String LAST_UPDATE = "lastUpdate";
    private static final String QUESTION = "question";
    private static final String QUESTIONS = "questions";
    private static final String ANSWER = "answer";
    private static final String ID = "id";
    private static final String DATE_TIME_FORMATTER = "dd-MM-yyyy HH:mm";
    private static final Clock CLOCK = new TimeTravelClock(LocalDateTime.now());
    private static final String DEFAULT_FILE_PATH = "src/resources/json/questions.json";

    private final String filePath;

    public FileTransformer(String filePath) {
        this.filePath = filePath;
    }

    public FileTransformer() {
        this.filePath = DEFAULT_FILE_PATH;
    }

    public void saveToFile(Map<Integer, Question> mapOfQuestion) {
        JsonBuilderFactory jsonBuilderFactory = Json.createBuilderFactory(Collections.emptyMap());
        List<JsonObject> list = new ArrayList<>();

        for (int i = 0; i < mapOfQuestion.size(); i++) {
            list.add(i, jsonBuilderFactory.createObjectBuilder()
                    .add(QUESTION, mapOfQuestion.get(i).getQuestion())
                    .add(ANSWER, mapOfQuestion.get(i).getAnswer())
                    .build()
            );
        }

        JsonArray jsonArray = jsonBuilderFactory.createArrayBuilder(list).build();

        JsonObject json = jsonBuilderFactory.createObjectBuilder()
                .add(VERSION, "0.0.1")
                .add(LAST_UPDATE, TransformerUtils.setPresentDateAndTime(CLOCK, DATE_TIME_FORMATTER))
                .add(QUESTIONS, jsonArray)
                .build();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // argumenty takie jak w transformerFactory
    public Map<Integer, Question> readJsonFromFile() {

        Scanner scanner = null;
        JsonReader jsonReader = null;
        Map<Integer, Question> mapQuestions = new LinkedHashMap<>();

        try {
            scanner = new Scanner(new File(filePath));
            String json = scanner.nextLine();
            InputStream inputStream = new ByteArrayInputStream(json.getBytes());
            JsonReaderFactory jsonReaderFactory = Json.createReaderFactory(Collections.emptyMap());
            jsonReader = jsonReaderFactory.createReader(inputStream, UTF_8);
            JsonArray jsonArray = jsonReader.readObject().getJsonArray(QUESTIONS);

            for (int i = 0; i < jsonArray.size(); i++) {
                mapQuestions.put(i,
                        new Question(
                                jsonArray.getJsonObject(i).getInt(ID),
                                jsonArray.getJsonObject(i).getString(QUESTION),
                                jsonArray.getJsonObject(i).getString(ANSWER))
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            jsonReader.close();
        }

        return mapQuestions;
    }

}
