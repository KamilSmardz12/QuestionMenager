package pl.questionMenager.TransformerUtils;

import org.junit.jupiter.api.*;
import pl.questionMenager.utils.TransformerUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class VersionUpdateTest {
    private static List<String> versions;

    @BeforeAll
    public static void initData() {
        versions = initVersions();
    }

    @RepeatedTest(value = 1198, name = "Version update test: {currentRepetition}/{totalRepetitions}")
    public void checkVersionUpdatew(RepetitionInfo repInfo) {
        int currentRepetition = repInfo.getCurrentRepetition();
        String correctVersion = versions.get(currentRepetition + 1);
        String currentVersion = versions.get(currentRepetition);
        String updatedVersion = TransformerUtils.calculateVersion(currentVersion);
        assertEquals(correctVersion, updatedVersion);
    }

    private static List<String> initVersions() {
        List<String> data = new LinkedList<>();
        IntStream.rangeClosed(0, 11).forEach(first -> {
            IntStream.rangeClosed(0, 9).forEach(second -> {
                IntStream.rangeClosed(0, 9).forEach(third -> {
                    data.add(String.format("%d.%d.%d", first, second, third));
                });
            });
        });
        return data;
    }
}
