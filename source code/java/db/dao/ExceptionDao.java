package db.dao;

public class ExceptionDao extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -4360095346527420657L;
    int type = 0;
    String type_name;


    public ExceptionDao() {

    }

    public ExceptionDao(int type) {
        setType(type);
    }

    public int getType() {
        return type;
    }

    private void setType(int type) {
        this.type = type;
        load_type();
    }

    public String getType_name() {
        return type_name;
    }

    private void setType_name(String type_name) {
        this.type_name = type_name;
    }

    private void load_type() {
        if (type == 0) {
            setType_name("this object is not a table in the database, please check your annotation");
        }
    }

    @Override
    public String getMessage() {
        if (type_name != null)
            return type_name;
        return super.getMessage();
    }

}
