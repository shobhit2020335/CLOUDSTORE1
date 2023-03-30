package customer;

public class responseModel {
    String message;

    public responseModel() {
    }

    public responseModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
