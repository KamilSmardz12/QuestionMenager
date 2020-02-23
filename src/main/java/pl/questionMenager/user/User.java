package pl.questionMenager.user;

public class User {

    private final UserProperties userProperties;
    public User(String login, String password) {
        //spr. w bazie i pobranie danych i ustawienie w userProperties
        new UserChecker().getUser()
        userProperties = UserProperties
                .builder()
                .login(login)
                .password(password)
                .privileges()
                .build();
    }
}
