package pl.questionMenager.JsonFormater;

import pl.questionMenager.model.Question;


import javax.json.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


public class Transformer {

    // konstruktor i ścieżka zawsze z do pliku z projektu
    private static final String FILE_PATH = "/home/rafal/Dokumenty/PROJEKTY JAVA/QuestionMenager/src/resources/json/questions.json";
    private String filePath;

    public Transformer(String filePath) {
        this.filePath = filePath;
    }

    //TODO generowanie wersji
    public void saveToFile(Map<Integer, Question> mapOfQuestion) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        JsonBuilderFactory jsonBuilderFactory = Json.createBuilderFactory(Collections.emptyMap());

        List<JsonObject> list = new ArrayList<>();
        for (int i = 0; i < mapOfQuestion.size(); i++) {
            list.add(i, jsonBuilderFactory.createObjectBuilder()
                    .add("question", mapOfQuestion.get(i).getQuestion())
                    .add("anwer", mapOfQuestion.get(i).getAnswer())
                    .build()
            );
        }

        JsonArray jsonArray = jsonBuilderFactory.createArrayBuilder(list).build();

        JsonObject json = jsonBuilderFactory.createObjectBuilder()
                .add(KeyInJson.version.toString(), "0.0.1")
                .add(KeyInJson.lastUpdate.toString(), presentDateAndTime())
                .add(KeyInJson.questions.toString(), jsonArray)
                .build();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bufferedWriter.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // argumenty takie jak w transformerFactory
    public Map<Integer,Question> readJsonFromFile() {
        Scanner scanner = null;
        JsonReader jsonReader = null;
        Map<Integer, Question> mapQuestions = new LinkedHashMap<>();

        try {
            scanner = new Scanner(new File(FILE_PATH));
            String jsonObjectReadedFromFile = scanner.nextLine();
            InputStream inputStream = new ByteArrayInputStream(jsonObjectReadedFromFile.getBytes());
            JsonReaderFactory jsonReaderFactory = Json.createReaderFactory(Collections.emptyMap());
            jsonReader = jsonReaderFactory.createReader(inputStream, StandardCharsets.UTF_8);
            JsonArray jsonArray = jsonReader.readObject().getJsonArray("questions");

            for (int i = 0; i < jsonArray.size(); i++) {
                mapQuestions.put(i, Question.builder()
                        .question(jsonArray.getJsonObject(i).getString(KeyInJson.question.toString()))
                        .answer(jsonArray.getJsonObject(i).getString(KeyInJson.answer.toString()))
                        .build()
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


    private String presentDateAndTime(){
        Date date = new Date();
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
    }
}
