package pl.questionMenager.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DataToLogin {
    private String login;
    private String password;
}
