package json;

import org.springframework.http.ResponseEntity;

public class Error_obj {
    Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Error_obj(Error error) {
        setError(error);
    }


    public Error_obj(String code ,String message) {
        setError(new Error(code,message));
    }
}
