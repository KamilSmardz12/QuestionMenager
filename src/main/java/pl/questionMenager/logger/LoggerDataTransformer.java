package pl.questionMenager.logger;

import java.lang.reflect.Method;

public class LoggerDataTransformer {

    private final Method[] methods;

    public LoggerDataTransformer(Class<?> className) {
        this.methods = className.getMethods();
    }

    public String setExpectedMessage(LoggerData action) {
        String expectedMessage =null;
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().contains(action.getAction())) {
                expectedMessage = action.getMessage();
            }
        }
        return expectedMessage;
    }
}
