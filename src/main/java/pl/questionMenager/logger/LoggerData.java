package pl.questionMenager.logger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoggerData {
    ADD("message"),
    DELETE("message"),
    UPDATE("message"),
    CREATE("message"),
    READ("message"),
    CONNECT("message"),
    DISCONNECT("message");

    private  final String message;
}
