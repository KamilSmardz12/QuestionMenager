package pl.questionMenager.Utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//TODO zamienić na konstruktor i getter w jednym
@RequiredArgsConstructor
public enum  DataBaseOrJsonDoZaminyNazwa {
    DATABASE("database"),
    JSON("json");

    @Getter
    private final String type;


}
