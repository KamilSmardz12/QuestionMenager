package pl.questionMenager.logger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoggerData {
    ADD("add","message"),
    DELETE("delete","message"),
    UPDATE("update","message"),
    CREATE("create","message"),
    READ("read","message"),
    CONNECT("connect","message"),
    DISCONNECT("disconnect","message");

    private  final String action;
    private  final String message;


}
