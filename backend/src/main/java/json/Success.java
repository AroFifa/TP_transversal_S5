package json;

public class Success {
    Object data;

    public Success(Object data) {
        setData(data);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
