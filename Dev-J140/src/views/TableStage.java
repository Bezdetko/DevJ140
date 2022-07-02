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
import javafx.collections.ObservableList;
//import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import model.Person;

/**
 *
 * @author Александр
 */
public class TableStage extends Stage {
    
    
//    private static void changeStylesheets (Scene mainScene){
//        if (style.equals(STYLESHEET_MODENA)) style = "file:default.css";
//        else style = STYLESHEET_MODENA;
//        mainScene.getStylesheets().clear();
//        mainScene.getStylesheets().add(style);
//    }
        
    private static Scene mainScene;
    
    private static RadioMenuItem standartTheme;
    private static RadioMenuItem customTheme;
    
    
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
//        Scene mainScene = new Scene(root, 600, 500);
//        
//        this.setTitle("Person Table");
//        this.setScene(mainScene);
//        this.show();

        BorderPane root = new BorderPane();
         // Menu
        MenuBar bar = new MenuBar();
        
        Menu view = new Menu("View");

//        RadioMenuItem standartTheme = new RadioMenuItem("Standart Theme");
//        RadioMenuItem customTheme = new RadioMenuItem("Custom Theme");

        standartTheme = new RadioMenuItem("Standart Theme");
        customTheme = new RadioMenuItem("Custom Theme");
        
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(standartTheme);
        toggleGroup.getToggles().add(customTheme);
        
        view.getItems().add(standartTheme);
        view.getItems().add(customTheme);
        
        
        standartTheme.setSelected(true);

        
        bar.getMenus().add(view);
        
        
        root.setTop(bar);
        
        
        
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
        
        mainScene = new Scene(root, 600, 500);
//                Scene scene = new Scene(root, 600, 500);
    
        
        
        standartTheme.setOnAction(e->{
                            mainScene.getStylesheets().clear();
                    
        });
        
        customTheme.setOnAction(e->{
                 root.getStyleClass().add("pane");
                 mainScene.getStylesheets().add("file:MyCSS.css");
//                 root.getStyleClass().add("pane");
        });
        
        
               
        this.setMinWidth(250);
        this.setMinHeight(250);
        this.setTitle("Person Table");
        this.setScene(mainScene);
        this.show();
    }
    
    
    protected static ObservableList<String> getCSS(){
        return mainScene.getStylesheets();        
    }
    
    protected static boolean isDark(){
        return customTheme.isSelected();
    }
}
