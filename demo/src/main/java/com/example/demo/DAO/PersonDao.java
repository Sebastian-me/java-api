package com.example.demo.DAO;

import com.example.demo.Manager.ConnectionManager;
import com.example.demo.Model.Person;
import com.example.demo.Model.PersonList;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDao {
    private final String CLASSNAME = "org.sqlite.JDBC";
    private final String CONNECTION_STRING = "jdbc:sqlite:data/demo.db3";

    private Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public PersonDao() throws ClassNotFoundException {
        Class.forName(CLASSNAME);
    }

    public Person create(Person person) throws Exception {
        String sql = "insert into person (firstname, lastname) values (?, ?)";
        try {
            createConn(sql);
            resultSet = statement.getGeneratedKeys();

            statement.setString(1, person.getFirstname());
            statement.setString(2, person.getLastname());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("no insert");
        } finally {
            ConnectionManager.closeAll(connection, statement, resultSet);
        }

        return person;
    }

    public Person read(int id) throws Exception {
        Person person = null;
        String sql = "SELECT * FROM person where id = ?";
        try {
            createConn(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            person = buildPersonEntity(resultSet);
        } catch (SQLException e) {
            System.out.println("test");
        } finally {
            ConnectionManager.closeAll(connection, statement, resultSet);
        }

        return person;
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE from person where id = ?";
        try {
            createConn(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new Exception("Die Person mit der ID: " + id + " konnte nicht gelöscht werden");
        } finally {
            ConnectionManager.close(connection, statement);
        }
    }

    public Person updatePerson(Person person) throws Exception {
        String sql = "UPDATE person set firstname = ?, lastname = ?" + "where id = ?";
        try {
            createConn(sql);
            statement.setString(1, person.getFirstname());
            statement.setString(2, person.getLastname());
            statement.setInt(3, person.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new Exception("es konnte leider kein Update durchgeführt werden");
        } finally {
            ConnectionManager.close(connection, statement);
        }

        return person;
    }

    public PersonList readAll() throws Exception {
        PersonList personList = new PersonList(new ArrayList<>());
        Person person;
        String sql = "SELECT * FROM person";
        try {
            createConn(sql);
            resultSet = statement.executeQuery();
            resultSet.next();
            while (resultSet.next()) {
                person = buildPersonEntity(resultSet);
                personList.getPersonList().add(person);
            }
        } catch (SQLException e) {
            throw new Exception("test");
        } finally {
            ConnectionManager.closeAll(connection, statement, resultSet);
        }

        return personList;
    }

    private void createConn(String sql) throws SQLException {
        connection = DriverManager.getConnection(CONNECTION_STRING);
        statement = connection.prepareStatement(sql);
    }

    private Person buildPersonEntity(ResultSet set) throws SQLException {
        int personId = set.getInt("id");
        String firstname = set.getString("firstname");
        String lastname = set.getString("lastname");

        return new Person(personId, firstname, lastname);
    }
}
