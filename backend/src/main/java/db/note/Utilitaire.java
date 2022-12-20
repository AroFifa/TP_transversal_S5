package db.note;

import db.dao.Column;
import db.dao.ConnectDb;
import db.dao.ExceptionDao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;

public class Utilitaire {

    //	get the table name in the database of an object
    public static String getTablename(Object o) {
        Table tableNote = o.getClass().getAnnotation(Table.class);
        if (tableNote != null) {
            if (tableNote.tableName().equals(""))
                return o.getClass().getSimpleName();
            return tableNote.tableName();
        } else
            return null;
    }

    // create a new instance of the object
    public static Object construct(Object o, Object... value) throws Exception {


        Object r = o.getClass().getConstructor().newInstance();
        if (value == null)
            return r;

        try {
            ArrayList<Column> fieldsDB = getDbFields(o);
            int i = 0;


            for (Column f : fieldsDB) {

                if (f.isFk()) {
                    Utilitaire.setPk(f.getValue(), value[i]);
                } else {
                    PropertyDescriptor pd = null;
                    Method m = null;

                    pd = new PropertyDescriptor(f.getField().getName(), o.getClass());
                    m = pd.getWriteMethod();


//                    System.out.println("value: "+ value[i]+" , field: "+f.getField().getName());
//                    System.out.println("class value: " +value[i].getClass().getSimpleName());
//                    System.out.println("method: "+m);
//                    System.out.println();

                    m.invoke(r, value[i]);
                }

                i++;
            }


        } catch (SecurityException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return r;

    }

    //	get the value of an object's attribute by its name
    private static Object getFieldValue(String fieldName, Object o, boolean isClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IntrospectionException, NoSuchFieldException, InstantiationException {

        PropertyDescriptor pd = null;

        Method m = null;
        Object r = null;
        try {
            pd = new PropertyDescriptor(fieldName, o.getClass());
            m = pd.getReadMethod();

            r = m.invoke(o);

            if (r == null && isClass)
                r = o.getClass().getDeclaredField(fieldName).getType().getConstructor().newInstance();


        } catch (SecurityException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw e;
        }
        return r;
    }


    //	get the columns in the database of an object into an ArrayList
    public static ArrayList<Column> getDbFields(Object o) throws Exception {
        Field[] fields = o.getClass().getDeclaredFields();
        int size = fields.length;

        ArrayList<Column> result = new ArrayList<Column>();
        for (int i = 0; i < size; i++) {
            Attr attrNote = fields[i].getAnnotation(Attr.class);
            if (attrNote != null) {
                String attr = "";
                if (attrNote.column().equals("")) {
                    attr = fields[i].getName();
                } else {
                    attr = attrNote.column();
                }
                String fk_col = attrNote.fk_column();

                boolean isclass = fk_col.equals("") && attrNote.fk();
                //  	    fk_col="id"+getTablename(getFieldValue(fields[i].getName(), o,isclass));
                Column obj;
                try {

                    obj = new Column(
                            fields[i],
                            attr,
                            getFieldValue(fields[i].getName(), o, isclass),
                            attrNote.sequence(),
                            attrNote.pk(),
                            attrNote.fk()
//							fk_col

                    );
					/*
					System.out.println(" obj: "+obj.getColumn());
					System.out.println(" field: "+obj.getField());
					System.out.println(obj.getValue());
					System.out.println();
*/
                } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException |
                         IntrospectionException e) {
                    throw e;
                }
                result.add(obj);
            }
        }
        return result;

    }


    public static Column getPk(Object o) throws Exception {
        try {
            ArrayList<Column> fields = getDbFields(o);

            for (Column c : fields) {
                if (c.isPk()) {
                    return c;
                }
            }
        } catch (Exception e) {
            throw e;

        }
        return null;

    }

    public static ArrayList<Column> getFk(Object o) throws Exception {
        ArrayList<Column> f = new ArrayList<Column>();
        ArrayList<Column> fk = new ArrayList<Column>();
        try {
            f = getDbFields(o);

//			System.out.println(fk.size());


            for (Column obj : f) {
                if (obj.isFk()) {
                    fk.add(obj);
                }
            }
        } catch (Exception e) {
            throw e;

        }
        return fk;

    }

    //	get the columns in the database of an object into a list of string format
    public static String[][] getDBAttributes(ArrayList<Object[]> fieldsDB) {

        String[] values = new String[fieldsDB.size()];
        String[] columns = new String[fieldsDB.size()];

        int i = 0;
        for (Object[] o : fieldsDB) {

            String fname = (String) o[1];
            Object value = o[2];

            columns[i] = fname;

            values[i] = value.toString();
            if (value.getClass().equals(String.class))
                values[i] = "'" + values[i] + "'";

            i++;
        }

        String[][] result = new String[2][];
        result[0] = columns;
        result[1] = values;

        return result;

    }

    public static ConnectDb getConnection(Object o) throws ExceptionDao, SQLException {
        ConnectDb c = null;
        Table tableNote = o.getClass().getAnnotation(Table.class);
        if (tableNote != null) {
            try {
                if (tableNote.database().equals("")) {
                    c = new ConnectDb(tableNote.sgbd(), tableNote.user(), tableNote.password());
                } else {
                    c = new ConnectDb(tableNote.sgbd(), tableNote.user(), tableNote.database(), tableNote.password());
                }
                return c;
            } catch (SQLException e) {
                throw e;
            }
        } else
            throw new ExceptionDao(0);
    }

    public static void setValue(String col, Object obj, Object value) throws Exception {

        try {
//            
//            System.out.println("col: "+col);
//            System.out.println("obj: "+obj.getClass());
//            System.out.println("value: "+value);

            PropertyDescriptor pd = null;
            Method m = null;

            pd = new PropertyDescriptor(col, obj.getClass());
            m = pd.getWriteMethod();

            m.invoke(obj, value);
        } catch (Exception e) {

            throw e;
        }

    }

    public static void setPk(Object o, Object value) throws Exception {
//      System.out.println(f.getField().getName()+" , v: "+value[i]);
        try {
            Column c = getPk(o);

            setValue(c.getField().getName(), o, value);
        } catch (Exception e) {

            throw e;
        }


    }

}
