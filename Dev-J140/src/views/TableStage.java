/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.sql.Date;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.DbManager;
import javafx.scene.layout.BorderPane;
import model.Person;

/**
 *
 * @author Александр
 */
public class TableStage extends Stage {
    
    public void init(){
        TableView <Person> tableView = new TableView<>();
        
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Person, Integer> idCol = new TableColumn<>("ID");
        idCol.setMinWidth(30);
        idCol.setMaxWidth(50);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().add(idCol);            
         
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First name");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        tableView.getColumns().add(firstNameCol);
        
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last name");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        tableView.getColumns().add(lastNameCol);
        
        TableColumn<Person, Date> birthDateCol = new TableColumn<>("Birth Date");
        birthDateCol.setMinWidth(50);
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
        tableView.getColumns().add(birthDateCol);
        
        tableView.setItems(DbManager.getPersonList());
        

//        VBox root = new VBox();
//        
//        root.getChildren().add(tableView);
//        
//        FlowPane buttonPane = new FlowPane();
//        buttonPane.setAlignment(Pos.CENTER);
//        root.getChildren().add(buttonPane);
//        
//        Button addPerson = new Button("New Person");
//        buttonPane.getChildren().add(addPerson);
//        addPerson.setOnAction(e -> {
//            NewPersonStage newPersonStage = new NewPersonStage();
//            newPersonStage.init();            
//        });
//        
//        Button updateTabel = new Button("Update Table");
//        buttonPane.getChildren().add(updateTabel);
//        addPerson.setOnAction(e -> {
//            DbManager.getPersonList();
//        });       
//        
//        Scene scene = new Scene(root, 600, 500);
//        
//        this.setTitle("Person Table");
//        this.setScene(scene);
//        this.show();

        BorderPane root = new BorderPane();
        
        root.setCenter(tableView);
        
        FlowPane buttonPane = new FlowPane();
        buttonPane.setHgap(10);
        buttonPane.setVgap(10);
        buttonPane.setAlignment(Pos.CENTER);
        root.setBottom(buttonPane);
        
        Button addPerson = new Button("New Person");
        buttonPane.getChildren().add(addPerson);
        addPerson.setOnAction(e -> {
            NewPersonStage newPersonStage = new NewPersonStage();
            newPersonStage.init();            
        });
        
        Button updateTabel = new Button("Update Table");
        buttonPane.getChildren().add(updateTabel);
        updateTabel.setOnAction(e -> {
            DbManager.getPersonList();
        });
        
        Scene scene = new Scene(root, 600, 500);
               
        this.setMinWidth(250);
        this.setMinHeight(250);
        this.setTitle("Person Table");
        this.setScene(scene);
        this.show();
    }
    
}
