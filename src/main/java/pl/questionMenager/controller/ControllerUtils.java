package pl.questionMenager.controller;

import pl.questionMenager.user.Privilege;
import pl.questionMenager.user.UserProperties;


//TODO FUTURE, moze sie przyda, moze nie
public class ControllerUtils {
    //TODO fk ktora sprawdzi dostep uzytkownika
    public static boolean checkPrivilege(UserProperties userProperties){
        if (userProperties.getPrivileges().equals(Privilege.ADMIN)){
            return true;
        } else {
            return false;
        }

    }
}
