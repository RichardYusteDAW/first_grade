package daw.examen.common.exception;

import daw.examen.common.tools.Color;

public class ExamException extends RuntimeException {

    public ExamException() {
        super(Color.RED + "Actor not exist" + Color.RESET);
    }

    public ExamException(String message) {
        super(Color.RED + message + Color.RESET);
    }
}
