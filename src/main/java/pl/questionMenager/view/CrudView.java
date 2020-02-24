package pl.questionMenager.view;

import javafx.util.Pair;
import pl.questionMenager.model.DataType;

import java.util.Scanner;

import static pl.questionMenager.model.DataType.DATABASE;
import static pl.questionMenager.model.DataType.JSON;

public class CrudView {

    private static final Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println(ClientMessage.WELLCOME);
    }

//    wydzielic do pobieraczaDanych

    public void goodBye() {
        System.out.println(ClientMessage.GOODBYE);
    }

    public DataType selectData() {
        System.out.println(ClientMessage.PODAJ_JAKIE_DANE ...);
        int data = sc.nextInt();
        DataType dataType;
        switch (data) {
            case 1:
                dataType = DATABASE;
                break;
            case 2:
                dataType = JSON;
                break;
        }
//        logger z lomboka
        return dataType;
    }

    public Pair podajDane(){
        System.out.println();
        String login = sc.next();
    }
}
