package pl.questionMenager.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Privilege {
    DEFAULT("defaultUse","read"),
    ADMIN("admin","admin"), // Only admin has privilege to delete question
    USER_WITH_PRIVILEGES_TO_ADD("userWithPrivilagesToAdd","add");
    private final String name;
    private final String privilege;
}
