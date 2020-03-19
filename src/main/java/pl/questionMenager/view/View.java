package pl.questionMenager.view;

import pl.questionMenager.crud.Crud;

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

    private void versionOfCreateMethod(Crud crud) {
        String question;
        String answer;
        System.out.println("W jaki sposob chcesz stworzyc pytanie? ");
        System.out.println("1) tworzac pytanie z polami answer i question");
        System.out.println("2) tworzac pytanie z polami difficulty level i question");
        System.out.println("3) tworzac pytanie z polami difficulty level, question oraz answer");
        switch (sc.nextInt()) {
            case 1:
                System.out.println("podaj tresc pytania");
                question = sc.nextLine();
                System.out.println("podaj tresc odpowiedzi");
                answer = sc.nextLine();
                crud.create(question, answer);
                break;
            case 2:
                System.out.println("to na razie nie dziala");
                /*System.out.println("podaj tresc pytania");
                question = sc.nextLine();
                System.out.println("podaj tresc poziom trudnosci");
                answer = sc.nextLine();*/
                //crud.create(difficultyLevel, question);kcja się tam dziej e

                break;
            case 3:
                break;
        }
    }

    private boolean checkIfIdIsContainedInList(int id, Crud crud) {
        return crud.idExist(id);
    }

    public void userInterface() {

    }
}
