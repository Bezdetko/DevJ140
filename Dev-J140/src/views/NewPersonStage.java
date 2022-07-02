/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Person;
import java.sql.Date;
import controller.DbManager;

/**
 *
 * @author Александр
 */
public class NewPersonStage extends Stage {
    
    protected static Scene scene;
    private FlowPane buttonPane;
    
    public void init(){
        VBox root = new VBox();
        root.setSpacing(10);

        GridPane personPane = new GridPane();
        personPane.setHgap(10);
        personPane.setVgap(10);
        
        Label label = new Label("New person");
        root.getChildren().add(label);
        
        Label fistNameLabel = new Label("Fist name:");
        TextField fistNameField = new TextField();        
        personPane.add(fistNameLabel, 0, 0);
        personPane.add(fistNameField, 1, 0);    
        
        
        Label lastNameLabel = new Label("Last name:");
        TextField lastNameField = new TextField();
        personPane.add(lastNameLabel, 0, 1);
        personPane.add(lastNameField, 1, 1);     
        
        
        Label birthDateLabel = new Label("Birth date:");
        DatePicker dateField = new DatePicker();
        personPane.add( birthDateLabel, 0, 2);   
        personPane.add( dateField, 1, 2);
     
        
        
        Label infoLabel = new Label();
        
        
       
        buttonPane = new FlowPane();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setHgap(10);               
        
        Button addPerson = new Button("Add Person");
        addPerson.setOnAction(e -> {
            if(fistNameField.getText().trim().isEmpty() ||
               lastNameField.getText().trim().isEmpty() ||
                dateField.getValue() == null){
                infoLabel.setText("All filed must be filled");                
            }
            else {
                Person person = new Person();
                person.setFirst_name(fistNameField.getText());
                person.setLast_name(lastNameField.getText().trim());
                person.setBirth_date(Date.valueOf(dateField.getValue()));
                DbManager.addPerson(person);
                this.close();
            }
            
        });
        
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> {
            this.close();
        });
        
        buttonPane.getChildren().add(addPerson);
        buttonPane.getChildren().add(cancel);
        
        

        root.getChildren().add(personPane);
        root.getChildren().add(infoLabel);
        root.getChildren().add(buttonPane);

        
        scene = new Scene(root, 300 , 200);
        if(TableStage.isDark()){
             setCustomCSS();
        } else {
            setStandartCSS();
        }
        
        
        
        
        this.setTitle("New Person");
        this.setScene(scene);
        this.setResizable(false);
        
        //            this.initOwner(newPersonStage);
//            this.initModality(Modality.WINDOW_MODAL);
//            this.showAndWait();
        
        this.show();                        
    }
    
        private void setStandartCSS(){
        scene.getStylesheets().add("file:MyStandartCSS.css");
        buttonPane.getStyleClass().add("buttonPane");    
    }
        private void setCustomCSS(){
        scene.getStylesheets().add("file:MyCSS.css");
        buttonPane.getStyleClass().add("buttonPane");       
    }    
    
}
