/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Александр
 */
public class Person {
    private int id;
    private String first_name;
    private String Last_name;
    private Date birth_date;

    public Person() {
    }
    

    public Person(int id, String first_name, String Last_name, Date birth_date) {
        this.id = id;
        this.first_name = first_name;
        this.Last_name = Last_name;
        this.birth_date = birth_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String Last_name) {
        this.Last_name = Last_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", first_name=" + first_name + ", Last_name=" + Last_name + ", birth_date=" + birth_date + '}';
    }
    
    

    
}


