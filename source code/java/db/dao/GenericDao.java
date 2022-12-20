package db.dao;

import db.note.Utilitaire;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenericDao {

    public String getTableName(Object obj) {
        return Utilitaire.getTablename(obj);
    }

    public void show(Object obj) throws Exception {
        System.out.println(getTableName(obj));
        System.out.println(" Fields component: ");
        try {
            for (Column o : getDbFields(obj)) {
                Field f = o.getField();
                String fname = o.getColumn();
                Object value = o.getValue();
                System.out.println(f.toString() + " // " + fname + " : " + value);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Column> getDbFields(Object obj) throws Exception {
        try {
            return Utilitaire.getDbFields(obj);
        } catch (Exception e) {
            throw e;
        }
    }

    private String script_getByid(Column pk, Object o) throws Exception {
        try {
//            System.out.println(pk.getColumn());
            return ScriptDao.id_selection(pk.getColumn(), getTableName(o), getDbFields(o));
        } catch (Exception e) {
            throw e;
        }
    }

    private ResultSet execute_uniqueQuery(ConnectDb con, PreparedStatement stmt, Column pk, Object o) throws Exception {

        try {
            stmt = con.prepareStmt(script_getByid(pk, o));
            stmt.setObject(1, pk.getValue());

            return stmt.executeQuery();
        } catch (Exception e) {
            throw e;

        }
    }

    private Object construct_unique(ResultSet rs, Object o) throws Exception {

        Object[] value = null;
        Object obj = null;
        try {
            ArrayList<Column> fk = Utilitaire.getFk(o);
            if (rs.next()) {
                value = getValues(rs, o);
                obj = Utilitaire.construct(o, value);
                load_FK(obj, fk, rs);

            }
            return obj;
        } catch (Exception e) {
            throw e;
        }
    }

    public Object getBy_id(Object o, ConnectDb con) throws Exception {
        PreparedStatement stmt = null;

        ResultSet rs = null;
        try {

            Column pk = Utilitaire.getPk(o);


            rs = execute_uniqueQuery(con, stmt, pk, o);


            return construct_unique(rs, o);
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw e;
            }

        }

    }


    public Object getUnique(Object o,String query, ConnectDb con,Object... values) throws Exception {
        PreparedStatement stmt = null;

        ResultSet rs = null;
        try {


            stmt = con.prepareStmt(query);

            int i=1;
            for (Object v :
                    values) {
                stmt.setObject(i,v);
            }

            rs= stmt.executeQuery();

            return construct_unique(rs, o);
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw e;
            }

        }

    }


    public ArrayList<Object> get(Object obj, String condition) throws Exception {
        ConnectDb con=null;
        try {
            con=Utilitaire.getConnection(obj);
            return get(obj, condition, con);
        } catch (Exception e) {
            throw e;
        }finally {
            con.close();
        }
    }

    public ArrayList<Object> getAll(Object obj) throws Exception {
        ConnectDb con=null;
        try {
            con=Utilitaire.getConnection(obj);
            return getAll(obj, con);
        } catch (Exception e) {
            throw e;
        }finally {
            con.close();
        }
    }

    private ArrayList<Object> construct_list(Object o, ResultSet rs) throws Exception {

        try {
            ArrayList<Object> liste = new ArrayList<Object>();
            Object[] value = null;
            ArrayList<Column> fk = Utilitaire.getFk(o);

            while (rs.next()) {
                value = getValues(rs, o);

                Object obj = Utilitaire.construct(o, value);
                load_FK(obj, fk, rs);

                liste.add(obj);
            }
            return liste;
        } catch (Exception e) {

            throw e;
        }
    }

    //
    public ArrayList<Object> get(Object o, String condition, ConnectDb con) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = con.prepareStmt(ScriptDao.select(getTableName(o), getDbFields(o), condition));
            setStmt_values(stmt, getDbFields(o), "select");

            rs = stmt.executeQuery();

//            System.out.println(stmt);
            ArrayList<Object> liste = construct_list(o, rs);
            return liste;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException e) {
                throw e;
            }

        }

    }

    public ArrayList<Object> get(Object o, String query, ConnectDb con,Object... values) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = con.prepareStmt(query);

            int i=1;
            for (Object v :
                    values) {
                stmt.setObject(i,v);
            }

            rs = stmt.executeQuery();

            ArrayList<Object> liste = construct_list(o, rs);
            return liste;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if(stmt!=null)
                    stmt.close();
            } catch (SQLException e) {
                throw e;
            }

        }

    }

    private Object[] getValues(ResultSet rs, Object o) throws Exception {
        try {
            ArrayList<Column> fields = getDbFields(o);

            Object[] values = new Object[fields.size()];
            int i = 0;
            for (Column c : fields) {
//                if(c.fk)
//                    values[i]=rs.getObject(c.getFk_column());
//                else
                values[i] = rs.getObject(c.getColumn());

                i++;
            }
            return values;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

    }

    private int exist(Object o, ConnectDb con) throws Exception {
        try {
            Column pk = Utilitaire.getPk(o);
            if (pk.getValue() == null)
                return -1;

            Object obj;
            obj = getBy_id(o, con);


            if (obj != null) {
                System.out.println("true");
                return 0;
            }

            System.out.println("false");
            return 1;
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Object> getAll(Object o, ConnectDb con) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = con.prepareStmt(ScriptDao.selectAll(getTableName(o), getDbFields(o)));
            rs = stmt.executeQuery();

            ArrayList<Object> liste = construct_list(o, rs);

            return liste;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                throw e;
            }

        }

    }

    public Object getBy_id(Object o) throws Exception {
        ConnectDb con=null;
        try {
            con=Utilitaire.getConnection(o);
            return getBy_id(o, con);
        } catch (Exception e) {
            throw e;
        }finally {
            con.close();
        }
    }

    private int setStmt_values(PreparedStatement stmt, ArrayList<Column> fieldsDB, String type) throws Exception {
        int i = 1;
        try {
            for (Column o : fieldsDB) {

                if (type.equals("insert")) {

                    String seq = o.getSequence();
                    if (!seq.equals("")) {

//	                  si la base de donn�e est POSTGRESQL
                        if (o.getValue() == null)
                            o.setValue(seq);

                    }
                } else if (type.equals("select") || type.equals("update")) {
                    if (o.getValue() == null)
                        continue;
                }

                if (o.isFk()) {
                    stmt.setObject(i, Utilitaire.getPk(o.getValue()).getValue());
                } else {
                    stmt.setObject(i, o.getValue());
                }
                i++;
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return i;
    }

    private void load_FK(Object obj, ArrayList<Column> fk, ResultSet rs) throws Exception {

        try {
            for (Column c : fk) {
//                System.out.println(c.getColumn()+" , "+rs.getObject(c.getColumn()));
                Utilitaire.setPk(c.getValue(), rs.getObject(c.getColumn()));
                Utilitaire.setValue(c.getField().getName(), obj, getBy_id(c.getValue()));

            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void save(Object obj) throws Exception {
        ConnectDb con=null;
        try {
            con=Utilitaire.getConnection(obj);
            save(obj, con);
        } catch (Exception e) {
            throw e;
        }finally {
            con.close();
        }
    }

    public void save(Object obj, ConnectDb connectdb) throws Exception {
        String req = null;
        PreparedStatement stmt = null;

        try {
            ArrayList<Column> fk = Utilitaire.getFk(obj);
//		    System.out.println(fk.size());
            for (Column c : fk) {
                if (exist(c.getValue(), Utilitaire.getConnection(obj)) == -1) {
                    new GenericDao().generate_pk(c);
                    save(c.getValue());
                }

            }


            req = ScriptDao.insert(getTableName(obj), getDbFields(obj));

            stmt = connectdb.prepareStmt(req);
            setStmt_values(stmt, getDbFields(obj), "insert");


            stmt.execute();
            connectdb.commit();
        } catch (SQLException e) {
            try {
                connectdb.rollback();
            } catch (SQLException e1) {
                throw e1;
            }
            throw e;
        } catch (Exception e) {

            try {
                connectdb.rollback();
            } catch (SQLException e1) {
                throw e1;
            }
            throw e;
        }
    }

    public void update(Object obj) throws Exception {
        ConnectDb con=null;
        try {
            con=Utilitaire.getConnection(obj);
            update(obj, con);
        } catch (Exception e) {
            throw e;
        }finally {
            con.close();
        }
    }

    public void delete(Object obj) throws Exception {
        ConnectDb con=null;
        try {
            con=Utilitaire.getConnection(obj);
            delete(obj, con);
        } catch (Exception e) {
            throw e;
        }finally {
            con.close();
        }
    }

    public void update(Object obj, ConnectDb connectdb) throws Exception {
        String req = null;
        PreparedStatement stmt = null;

        try {

            Column pk = Utilitaire.getPk(obj);

            req = ScriptDao.update(pk.getColumn(), getTableName(obj), getDbFields(obj));
            stmt = connectdb.prepareStmt(req);
            int f = setStmt_values(stmt, getDbFields(obj), "update");

            stmt.setObject(f, pk.getValue());

            stmt.execute();
            connectdb.commit();
        } catch (SQLException e) {
//            System.out.println(stmt);
            try {
                connectdb.rollback();
            } catch (SQLException e1) {
                throw e1;
            }
            throw e;
        } catch (Exception e) {
            try {
                connectdb.rollback();
            } catch (SQLException e1) {
                throw e1;
            }
            throw e;
        }
    }

    public void delete(Object obj, ConnectDb connectdb) throws Exception {
        String req = null;
        PreparedStatement stmt = null;

        try {

            Column pk = Utilitaire.getPk(obj);

            req = ScriptDao.delete(pk.getColumn(), getTableName(obj));
            stmt = connectdb.prepareStmt(req);
            stmt.setObject(1, pk.getValue());

            stmt.execute();
            connectdb.commit();
        } catch (SQLException e) {
            try {
                connectdb.rollback();
            } catch (SQLException e1) {
                throw e1;
            }
            throw e;
        } catch (Exception e) {
            try {
                connectdb.rollback();
            } catch (SQLException e1) {
                throw e1;
            }
            throw e;
        }
    }

    public void generate_pk(Object o) throws Exception {
        ConnectDb con=null;
        try {
            con=Utilitaire.getConnection(o);
            generate_pk(o, con);
        } catch (Exception e) {
            throw e;
        }finally {
            con.close();
        }
    }

    public void generate_pk(Object o, ConnectDb con) throws Exception {

        PreparedStatement stmt = null;
        String sequence = null;

        ResultSet rs = null;
        try {

            Column pk = Utilitaire.getPk(o);
            sequence = pk.getSequence();

            stmt = con.prepareStmt("SELECT nextval(?)");
            stmt.setString(1, sequence);

            rs = stmt.executeQuery();
            rs.next();
            String pk_value = rs.getString("nextval");

            Utilitaire.setPk(o, pk_value);
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                throw e;
            }

        }

    }

}
