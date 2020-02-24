package pl.questionMenager.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProperties {
    private final String name;
    private final String login;
    private final String password;
    private final Privilege privileges;
}
