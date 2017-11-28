package neshev.dimitar.project.Models;

public class LoginErrorPOJO {
    private String errorMessage;

    public LoginErrorPOJO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LoginErrorPOJO() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
