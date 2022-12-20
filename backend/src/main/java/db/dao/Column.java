package db.dao;

import java.lang.reflect.Field;

public class Column {
    Field field;
    String column;
    Object value;
    String sequence = "";
    boolean pk = false;
    boolean fk = false;

    public Column(Field field, String column, Object value) {
        super();
        this.field = field;
        this.column = column;
        this.value = value;
    }

    public Column(Field field, String column, Object value, String sequence, boolean pk, boolean fk) {
        super();
        this.field = field;
        this.column = column;
        this.value = value;
        this.sequence = sequence;
        this.pk = pk;
        this.fk = fk;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public boolean isPk() {
        return pk;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }

    public boolean isFk() {
        return fk;
    }

    public void setFk(boolean fk) {
        this.fk = fk;
    }


}
