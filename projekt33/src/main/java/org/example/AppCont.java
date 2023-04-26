package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AppCont  {
    private static final Logger logger = LoggerFactory.getLogger(AppCont.class);
    @FXML
    private TableView <appBasicView> tableMenu;


    @FXML
    private TextField SearchBar;

    @FXML
    private TableColumn<appBasicView,Long> idCol;
    @FXML
    private TableColumn<appBasicView,String> nameCol;
    @FXML
    private TableColumn<appBasicView,String> surnameCol;
    @FXML
    private TableColumn<appBasicView,String> birCol;
    @FXML
    private TableColumn<appBasicView,Integer> weiCol;
    @FXML
    private TableColumn<appBasicView,String> addCol;
    @FXML
    private TableColumn<appBasicView,String> memCol;
    @FXML
    private Button refresh;
    @FXML
    private Button findButton;

    @FXML
    private Button addMember;
    @FXML
    private Button removeMember;

    private org.example.appRepository appRepository;
    private org.example.appService appService;
    public AppCont() {

    }
    @FXML
    private void initialize(){
        appRepository= new appRepository();
        appService=new appService(appRepository);
        idCol.setCellValueFactory(new PropertyValueFactory<appBasicView,Long>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<appBasicView,String>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<appBasicView,String>("surname"));
        birCol.setCellValueFactory(new PropertyValueFactory<appBasicView,String>("birthday"));
        weiCol.setCellValueFactory(new PropertyValueFactory<appBasicView,Integer>("weight"));
        addCol.setCellValueFactory(new PropertyValueFactory<appBasicView,String>("address"));
        memCol.setCellValueFactory(new PropertyValueFactory<appBasicView,String>("membership"));
        ObservableList<appBasicView> ugabuga= FXCollections.observableArrayList(appService.getMembers());

        tableMenu.getSortOrder().add(idCol);
        ObservableList<appBasicView> observablePersonsList = initializePersonsData();
        tableMenu.setItems(observablePersonsList);

        tableMenu.getSortOrder().add(idCol);

        initializeTableViewSelection();






        logger.info("PersonsController initialized");





    }



    private void initializeTableViewSelection() {
        MenuItem detailedView = new MenuItem("Detailed member view");
        MenuItem edit = new MenuItem("Edit member");
        MenuItem delete = new MenuItem ("Delete member");
        detailedView.setOnAction((ActionEvent event) -> {
            appBasicView personView = tableMenu.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("Detail.fxml"));
                Stage stage = new Stage();

                Long id_member = personView.getId();
                MemberDetailView memberDetail = appService.getPersonDetailView(id_member);

                stage.setUserData(memberDetail);
                stage.setTitle("Detail VIEW");

                MemberDetailCont controller = new MemberDetailCont();
                controller.setStage(stage);
                fxmlLoader.setController(controller);
                stage.getIcons().add(new Image(App.class.getResourceAsStream("69840.png")));
                Scene scene = new Scene(fxmlLoader.load(), 600, 500);

                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                ExceptionHandler.handleException(ex);
            }

        });
        tableMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Long id = tableMenu.getSelectionModel().getSelectedItem().getId();



            }
        });
        edit.setOnAction((ActionEvent event) -> {
            appBasicView personView = tableMenu.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("Edit.fxml"));
                Stage stage = new Stage();
                Long id=personView.getId();
                MemberDetailView detailView=appService.getPersonDetailView(id);



                stage.setUserData(detailView);
                stage.setTitle("EDIT VIEW");

                AppEditController controller = new AppEditController();
                controller.setStage(stage);
                fxmlLoader.setController(controller);

                Scene scene = new Scene(fxmlLoader.load(), 600, 500);

                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                ExceptionHandler.handleException(ex);
            }
                });
            delete.setOnAction((ActionEvent event)-> {
            appBasicView personView = tableMenu.getSelectionModel().getSelectedItem();
            Long id = personView.getId();
            appRepository.remove(id);
            });


        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll(detailedView);
        menu.getItems().add(edit);
        menu.getItems().add(delete);
        tableMenu.setContextMenu(menu);
    }
    private ObservableList<appBasicView> initializePersonsData() {
        List<appBasicView> persons = appService.getPersonsBasicView();
        return FXCollections.observableArrayList(persons);
    }

    public void handleExitMenuItem(ActionEvent event) {
        System.exit(0);
    }
    public void handleAddPersonButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("addPerson.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("69840.png")));
            stage.setTitle("ADD MEMBER");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(String.format("FXML loading error\nMessage: %s", e.getMessage()));
        }
    }
    public void handleHelpButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("help.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("69840.png")));
            stage.setTitle("HEEEELP");
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(String.format("FXML loading error\nMessage: %s", e.getMessage()));
        }
    }
    public void handleInjection(ActionEvent actionEvent)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("Injection.fxml"));
            Stage stage = new Stage();
            InjectionController controller = new InjectionController();
            controller.setStage(stage);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            stage.setTitle("SQL Injection");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
            logger.error("Failed to open Injection\nMessage: " + e.getMessage());
        }
    }



    public void handleFilterButton(ActionEvent actionEvent){
        try {
            Long id=Long.valueOf(SearchBar.getText());
            ObservableList<appBasicView>observableList=FXCollections.observableArrayList(appService.getFilteredView(id));
            tableMenu.setItems(observableList);
            tableMenu.refresh();
            tableMenu.sort();
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            logger.info("Error",e.getMessage());
        }




    }
    public void handleRefreshButton(ActionEvent actionEvent) {
        ObservableList<appBasicView> observablePersonsList = initializePersonsData();
        tableMenu.setItems(observablePersonsList);
        tableMenu.refresh();
        tableMenu.sort();
    }
}






