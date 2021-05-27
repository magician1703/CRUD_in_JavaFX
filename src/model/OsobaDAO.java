package model;

import connection.connectionToDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OsobaDAO {

    public static void addOsobe(String name, String surname) throws SQLException {
        String sql = "INSERT INTO data_base (name, surname) VALUES('" + name + "','" + surname + "');";
        try {
            connectionToDataBase.executeDML(sql);
            System.out.println(sql);
        }
        catch (SQLException throwables) {
            System.out.println("SQL ERROR " + sql);
            throwables.printStackTrace();
            throw throwables;
        }
    }

    public static ObservableList<Osoba> getListOsoba() throws SQLException {

        String sql = "SELECT * FROM data_base";
        ObservableList<Osoba> list;

        try {
            ResultSet resultSet = connectionToDataBase.getData(sql);
            list = makeObservableList(resultSet);
        }
        catch (SQLException throwables) {
            System.out.println("Reading users from db Error");
            throwables.printStackTrace();
            throw throwables;
        }

        return list;
    }

    private static ObservableList<Osoba> makeObservableList(ResultSet resultSet) {
        ObservableList<Osoba> list = FXCollections.observableArrayList();

        try {
            while (resultSet.next()){
                Osoba osoba = new Osoba();
                osoba.setOsobaid(resultSet.getInt("id"));
                osoba.setOsobaName(resultSet.getString("name"));
                osoba.setOsobaSur(resultSet.getString("surname"));
                list.add(osoba);
            }
        }
        catch (SQLException e) {
            System.out.println("Creating list of users Error...");
            e.printStackTrace();
        }
        return list;
    }

    public static void deleteOsoba(int id) throws SQLException {
        String sql = "DELETE FROM data_base WHERE id = " + id + ";";
        try {
            connectionToDataBase.executeDML(sql);
            System.out.println(sql);
        }
        catch (SQLException throwables) {
            System.out.println("SQL ERROR " + sql);
            throwables.printStackTrace();
            throw throwables;
        }
    }

    public static void updateOsoba(int id, String name, String surname) throws SQLException {

        String sql = "UPDATE data_base SET name = '" + name
                + "', surname = '" + surname + "' WHERE id = " + id + ";";

        try {
            connectionToDataBase.executeDML(sql);
            System.out.println(sql);
        }
        catch (SQLException throwables) {
            System.out.println("SQL ERROR " + sql);
            throwables.printStackTrace();
            throw throwables;
        }
    }
}
