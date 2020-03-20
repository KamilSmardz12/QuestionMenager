package pl.questionMenager.view;

import pl.questionMenager.crud.Crud;
import pl.questionMenager.model.DifficultyLevel;

import java.util.Scanner;

public class View {
    private Scanner sc = new Scanner(System.in);

    //TODO
    public void login() {
        System.out.println(ClientMessage.LOGIN.getMessage());
    }

    //TODO
    public void goodBye() {
        System.out.println(ClientMessage.GOODBYE.getMessage());
    }

    //TODO
    public void selectData() {
        System.out.println(ClientMessage.SELECT_THE_CONNECTION_TYPE.getMessage());
    }

    //TODO
    public void welcome() {
        System.out.println(ClientMessage.WELCOME.getMessage());
    }

    public void adminInterface(Crud crud) {
        System.out.println("wybierz opcje");
        System.out.println("1) wyszukaj pytanie po id");
        System.out.println("2) wyciagnij wszystkie pytania");
        System.out.println("3) stworz pytanie");
        System.out.println("4) usun pytanie po id");
        switch (sc.nextInt()) {
            case 1:
                System.out.println("podaj id pytania:");
                int id = sc.nextInt();
                if (checkIfIdIsContainedInList(id, crud)) {
                    System.out.println(crud.read(id));
                } else {
                    System.out.println("nie ma pytania o takim id");
                }
                break;
            case 2:
                crud.readAll().forEach(System.out::println);
                break;
            case 3:
                versionOfCreateMethod(crud);
                break;
            case 4:
                System.out.println("Podaj id pytania ktore chcesz usunac");
                int index = sc.nextInt();
                if (checkIfIdIsContainedInList(index, crud)) {
                    crud.remove(index);
                }
                break;
        }
    }

    public void userInterface(Crud crud) {
        System.out.println("wybierz opcje");
        System.out.println("1) wyszukaj pytanie po id");
        System.out.println("2) wyciagnij wszystkie pytania");
        switch (sc.nextInt()) {
            case 1:
                System.out.println("podaj id pytania:");
                int id = sc.nextInt();
                if (checkIfIdIsContainedInList(id, crud)) {
                    System.out.println(crud.read(id));
                } else {
                    System.out.println("nie ma pytania o takim id");
                }
                break;
            case 2:
                crud.readAll().forEach(System.out::println);
                break;
        }
    }

    public void userInterfaceWithRightToAdd(Crud crud) {
        System.out.println("wybierz opcje");
        System.out.println("1) wyszukaj pytanie po id");
        System.out.println("2) wyciagnij wszystkie pytania");
        System.out.println("3) stworz pytanie");
        switch (sc.nextInt()) {
            case 1:
                System.out.println("podaj id pytania:");
                int id = sc.nextInt();
                if (checkIfIdIsContainedInList(id, crud)) {
                    System.out.println(crud.read(id));
                } else {
                    System.out.println("nie ma pytania o takim id");
                }
                break;
            case 2:
                crud.readAll().forEach(System.out::println);
                break;
            case 3:
                versionOfCreateMethod(crud);
                break;
        }
    }

    private boolean checkIfIdIsContainedInList(int id, Crud crud) {
        return crud.idExist(id);
    }

    private void versionOfCreateMethod(Crud crud) {
        String question;
        String answer;
        String difficultyLevel;
        System.out.println("W jaki sposob chcesz stworzyc pytanie? ");
        System.out.println("1) tworzac pytanie z polami answer i question");
        System.out.println("2) tworzac pytanie z polami difficulty level i question");
        System.out.println("3) tworzac pytanie z polami difficulty level, question oraz answer");
        switch (sc.nextInt()) {
            case 1:
                System.out.println("podaj tresc pytania");
                question = sc.next();
                System.out.println("podaj tresc odpowiedzi");
                answer = sc.next();
                crud.create(question, answer);
                break;
            case 2:
                System.out.println("podaj tresc pytania");
                question = sc.next();
                System.out.println("podaj tresc poziom trudnosci");
                difficultyLevel = sc.next();
                getFromUserInformationAboutDifficultyLevel(crud, question, difficultyLevel);
                break;
            case 3:
                System.out.println("podaj tresc pytania");
                question = sc.next();
                System.out.println("podaj tresc odpowiedzi");
                answer = sc.next();
                System.out.println("podaj tresc poziom trudnosci");
                difficultyLevel = sc.next();
                getFromUserInformationAboutDifficultyLevel(crud, question,answer, difficultyLevel);
                break;
        }
    }

    private void getFromUserInformationAboutDifficultyLevel(Crud crud, String question, String difficultyLevel) {
        if (difficultyLevel.toLowerCase().equals("basic")) {
            crud.create(DifficultyLevel.BASIC, question);
        } else if (difficultyLevel.toLowerCase().equals("hard")) {
            crud.create(DifficultyLevel.HARD, question);
        } else if (difficultyLevel.toLowerCase().equals("avarage")) {
            crud.create(DifficultyLevel.AVERAGE, question);
        } else {
            crud.create(DifficultyLevel.EMPTY, question);
        }
    }

    private void getFromUserInformationAboutDifficultyLevel(Crud crud, String question,String answer, String difficultyLevel) {
        if (difficultyLevel.toLowerCase().equals("basic")) {
            crud.create(DifficultyLevel.BASIC, question,answer);
        } else if (difficultyLevel.toLowerCase().equals("hard")) {
            crud.create(DifficultyLevel.HARD, question,answer);
        } else if (difficultyLevel.toLowerCase().equals("avarage")) {
            crud.create(DifficultyLevel.AVERAGE, question,answer);
        } else {
            crud.create(DifficultyLevel.EMPTY, question,answer);
        }
    }


}
