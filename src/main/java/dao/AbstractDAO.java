package dao;

import connection.ConnectionFactory;
import model.Clients;

import java.awt.*;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    public AbstractDAO() {
        this.type=(Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    //SELECT ALL Query
    private String selectAllQuery(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM ");
        stringBuilder.append(type.getSimpleName().toLowerCase());
        return stringBuilder.toString();
    }

    //SELECT ALL WHERE Query
    private String selectQuery(String field){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE " + field + " =?");
        return stringBuilder.toString();
    }
    //FIND BY ID
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = selectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    //INSERT Query
    public String insertQuery(T obj){
        StringBuilder query=new StringBuilder();
        query.append("INSERT INTO ");
        query.append(type.getSimpleName().toLowerCase());
        query.append("(");
        int ok=0;
        for(Field fld: type.getDeclaredFields()){
            if(ok!=0){
                query.append(fld.getName());
                query.append(", ");
            }
            ok++;
        }
        query.delete(query.length()-2,query.length());
        query.append(")VALUES(");
        ok=0;
        for(Field fld: type.getDeclaredFields()){
            if(ok!=0){
                query.append("?, ");
            }
            ok++;
        }
        query.delete(query.length()-2,query.length());
        query.append(")");
        return query.toString();
    }
    //DELETE Query
    public String deleteQuery(String field){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("DELETE FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE " + field + " =?");
        return stringBuilder.toString();
    }
    //UPDATE Query
    private String updateQuery(int id){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("UPDATE ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" SET ");
        int ok=0;
        for(Field fld: type.getDeclaredFields()){
            if(ok!=0){
                stringBuilder.append(fld.getName());
                stringBuilder.append("=?, ");
            }
            ok++;
        }
        stringBuilder.delete(stringBuilder.length() - 2,stringBuilder.length());
        stringBuilder.append(" WHERE ID= ");
        stringBuilder.append(id);
        return stringBuilder.toString();
    }

    //insert
    public void insert(T obj){
        Connection connection=null;
        PreparedStatement statement = null;
        String query = insertQuery(obj);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int ok=0;
            for(Field fld: type.getDeclaredFields()){
                if(ok!=0){
                    fld.setAccessible(true);
                    try{
                        statement.setObject(ok,fld.get(obj));
                    }catch (IllegalAccessException e){
                        e.printStackTrace();
                    }
                }
                ok++;
            }
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    //delete
    public void delete(int id){
        Connection connection=null;
        PreparedStatement statement = null;
        String query = deleteQuery("id");
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    //update
    public void update(int id,T obj){
        Connection connection=null;
        PreparedStatement statement=null;
        String query=updateQuery(id);
        try{
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            int ok=0;
            for(Field fld: type.getDeclaredFields()){
                if(ok!=0){
                    fld.setAccessible(true);
                    try{
                        statement.setObject(ok,fld.get(obj));
                    }catch (IllegalAccessException e){
                        e.printStackTrace();
                    }
                }
                ok++;
            }
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    //createObjects from DB
    private List<T> createObjects(ResultSet resultSet){
        List<T> list=new ArrayList<T>();
        Constructor[] constructors=type.getDeclaredConstructors();
        Constructor emptyConstructor=null;
        for(int i=0;i<constructors.length;i++){
            if(constructors[i].getGenericExceptionTypes().length==0) {
                emptyConstructor=constructors[i];
                break;
            }
        }
        try{
            while(resultSet.next()){
                emptyConstructor.setAccessible(true);
                T inst=(T)emptyConstructor.newInstance();
                for(Field fld: type.getDeclaredFields()){
                    String field=fld.getName();
                    Object value=resultSet.getObject(field);
                    PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field,type);
                    Method method=propertyDescriptor.getWriteMethod();
                    method.invoke(inst,value);
                }
                list.add(inst);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    //findAll method
    public List<T> findAll(){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query=selectAllQuery().toString();
        try{
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();

            return createObjects(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
