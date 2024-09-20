package main.dao.generic;

import main.dao.generic.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDao<T> implements IGenericDao<T> {

    protected Connection getConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }

    protected abstract String getInsertQuery();
    protected abstract void setInsertParameters(PreparedStatement stmt, T entity) throws SQLException;
    protected abstract T extractEntity(ResultSet rs) throws SQLException;

    @Override
    public void save(T entity) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(getInsertQuery())) {

            setInsertParameters(stmt, entity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public abstract List<T> findAll();

}


//package main.dao.generic;
//
//import main.dao.generic.jdbc.ConnectionFactory;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//public abstract class GenericDao<T> {
//
//    protected Connection getConnection() throws SQLException {
//        return ConnectionFactory.getConnection();
//    }
//
//    protected abstract String getInsertQuery();
//    protected abstract void setInsertParameters(PreparedStatement stmt, T entity) throws SQLException;
//    protected abstract T extractEntity(ResultSet rs) throws SQLException;
//
//    public void save(T entity) {
//        try (Connection connection = getConnection();
//             PreparedStatement stmt = connection.prepareStatement(getInsertQuery())) {
//
//            setInsertParameters(stmt, entity);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public abstract List<T> findAll();
//
//
//}
