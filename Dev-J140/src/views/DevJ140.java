/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.UserData;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import settings.Settings;
import views.TableStage;

/**
 *
 * @author denis
 */
public class DevJ140 extends Application {
    
    Scene scene;
    Label infoLabel;
    
    Settings settings = new Settings();
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        
        FlowPane row1 = new FlowPane();
        Label auth = new Label("Authorization");
        row1.getChildren().add(auth);
        row1.setAlignment(Pos.BOTTOM_CENTER);
        root.add(row1, 0, 0);
        
        GridPane row2 = new GridPane();
        row2.setHgap(10);
        row2.setVgap(10);
        Label nameLable = new Label("name ");
        row2.add(nameLable, 0, 0);
        TextField nameField = new TextField();
        row2.add(nameField, 1, 0);
        Label passLable = new Label("pass");
        row2.add(passLable, 0, 1);
        PasswordField passField = new PasswordField();
        row2.add(passField, 1, 1);
        root.add(row2, 0, 1);
        
        FlowPane row3 = new FlowPane();
        Button singInButton = new Button("Sing in");
        singInButton.setOnAction((e) -> {
            String name = nameField.getText();
            String pass = passField.getText();
            UserData user = new UserData(name, pass);
            if (!user.checkPassword())infoLabel.setText("Password is not valid");
//            else infoLabel.setText("Password is valid");
            else {
            TableStage tableStage = new TableStage();
            tableStage.init();
            primaryStage.close();           
            }
        });
        row3.getChildren().add(singInButton);
        row3.setAlignment(Pos.TOP_RIGHT);
        root.add(row3, 0, 2);
        
        FlowPane row4 = new FlowPane();
        infoLabel = new Label();
        row4.getChildren().add(infoLabel);
        row4.setAlignment(Pos.TOP_CENTER);
        root.add(row4, 0, 3);
        
        scene = new Scene(root, 300, 250);
        
        scene.getStylesheets().add("file:MyCSS.css");
        root.getStyleClass().add("mainPane");
//        scene.getStylesheets().add("file:MyCSS.css");
//        scene.getStylesheets();
        

//        root.getStyleClass().addAll("pane","grid");
        
        
        primaryStage.setTitle("Authorization");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
