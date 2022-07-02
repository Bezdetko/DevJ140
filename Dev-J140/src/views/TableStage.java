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
import javafx.stage.Modality;
import model.Person;

/**
 *
 * @author Александр
 */
public class TableStage extends Stage {
        
    private static Scene scene;
    
    private static RadioMenuItem standartTheme;
    private static RadioMenuItem customTheme;
    
    private TableView <Person> tableView;
    private BorderPane root;
    private MenuBar menuBar;
    private Menu view;
    private FlowPane buttonPane;
    
    
    public void init(){
        tableView = new TableView<>();
        
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
        

        root = new BorderPane();

        menuBar = new MenuBar();

        view = new Menu("View");
        

        standartTheme = new RadioMenuItem("Standart Theme");
        customTheme = new RadioMenuItem("Dark Theme");
        
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(standartTheme);
        toggleGroup.getToggles().add(customTheme);
        
        view.getItems().add(standartTheme);
        view.getItems().add(customTheme);
        
        
        standartTheme.setSelected(true);
       
        

        
        menuBar.getMenus().add(view);
        
        
        root.setTop(menuBar);
        
        
        
        root.setCenter(tableView);
        
        buttonPane = new FlowPane();
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
        
        scene = new Scene(root, 600, 500);
    
        
        
        standartTheme.setOnAction(e->{
                            scene.getStylesheets().clear();
                            setStandartCSS();
                    
        });
        
        customTheme.setOnAction(e->{
                 scene.getStylesheets().add("file:MyCSS.css");
                 root.getStyleClass().add("mainPane");
                 buttonPane.getStyleClass().add("buttonPane");
        });
              
        

        if(standartTheme.isSelected()) {
            setStandartCSS();
        }
        
        this.setMinWidth(250);
        this.setMinHeight(250);
        this.setTitle("Person Table");
        this.setScene(scene);
        this.show();
    }
    
    
    protected static ObservableList<String> getCSS(){
        return scene.getStylesheets();        
    }
    
    private void setStandartCSS(){
        scene.getStylesheets().add("file:MyStandartCSS.css");
        root.getStyleClass().add("mainPane");
        buttonPane.getStyleClass().add("buttonPane");       
}
    
    private void setCustomCSS(){
        scene.getStylesheets().add("file:MyCSS.css");
        buttonPane.getStyleClass().add("buttonPane");       
}    
    
    protected static boolean isDark(){
        return customTheme.isSelected();
    }
    
    
}
