package pl.questionMenager.view;

import lombok.RequiredArgsConstructor;
import pl.questionMenager.controller.PobieraczDanych;

@RequiredArgsConstructor
public enum ClientMessage {
    WELCOME("Witaj użytkowniku", ""),
    LOGIN("Zaloguj sie podajac login i haslo:", PobieraczDanych.logIn()),
    SELECT_THE_CONNECTION_TYPE("Wybierz rodzaj polaczenia: JSON(1) lub database(2)", PobieraczDanych.checkDataType());

    private final String message;
    private final Object pobieraczDanych;

}
