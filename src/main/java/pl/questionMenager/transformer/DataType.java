package pl.questionMenager.transformer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DataType {
    DATABASE("database"),
    JSON("json");

    @Getter
    private final String type;
}
