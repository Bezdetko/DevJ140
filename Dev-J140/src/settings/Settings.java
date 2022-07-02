/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Александр
 */
public class Settings {
    
    Properties properties;
    public static  final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "psw";

    public Settings() {
        properties = new Properties();
        File file = new File("settings.prop");
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            properties.load(new FileReader(file));
        } catch (IOException ioe) {
            System.err.println("ERROR: " + ioe.getMessage());
        }
    }
    
    public String getValue(String key){
        String value = properties.getProperty(key);
        return value;
    }
    
    
    
}
