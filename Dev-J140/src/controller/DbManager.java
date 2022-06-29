/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;
import settings.Settings;

/**
 *
 * @author Александр
 */
public class DbManager {
    
    public static ObservableList<Person> getPersonList(){
        List<Person> personList = new ArrayList<>();
        Settings settings = new Settings();
        
        try (Connection connection = DriverManager.getConnection(
                settings.getValue(Settings.URL),
                settings.getValue(Settings.USER),
                settings.getValue(Settings.PASSWORD))){
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM PERSON");
            Person person = null;
            while (rs.next()){
                person = new Person(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4));
                personList.add(person);
            }
        }
        catch (SQLException sqle){
            System.err.println("ERROR: " + sqle.getMessage());
        }
        return FXCollections.observableArrayList(personList);
    }
    
    public static void addPerson(Person person){
        Settings settings = new Settings();
        try (Connection connection = DriverManager.getConnection(
                settings.getValue(Settings.URL),
                settings.getValue(Settings.USER),
                settings.getValue(Settings.PASSWORD))){
            Statement stm = connection.createStatement();
            String sql = "INSERT INTO PERSON (first_name, last_name, birth_date) VALUES ('"+person.getFirst_name()+"', '"+person.getLast_name()+"', '"+person.getBirth_date()+"')";
            stm.executeUpdate(sql);
           
        }                                                                               
        catch (SQLException sqle){
            System.err.println("ERROR: " + sqle.getMessage());
        }
        
    }
}
