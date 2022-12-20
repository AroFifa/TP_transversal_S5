package json;

public class Error {

    String code = "404";
    String message = "Error";

    public Error(String code, String message) {
        setCode(code);
        setMessage(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
