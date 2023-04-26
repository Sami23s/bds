package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class InjectionController
{
    private static final Logger logger = LoggerFactory.getLogger(InjectionController.class);

    @FXML
    private Button customButton;
    @FXML
    private TextField injectionTextField;
    @FXML
    private TableView<InjectionView> injectionTable;
    @FXML
    private TableColumn<InjectionView, Long> idColumn;
    @FXML
    private TableColumn<InjectionView, String> firstNameColumn;
    @FXML
    private TableColumn<InjectionView, String> lastNameColumn;
    @FXML
    private TableColumn<InjectionView, String> nickNameColumn;
    @FXML
    private TableColumn<InjectionView, String> emailColumn;

    private appService Service;
    private appRepository appRepository;
    private Stage stage;

    public InjectionController()
    {

    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    @FXML
    private void initialize()
    {
        appRepository = new appRepository();
        Service = new appService(appRepository);

        idColumn.setCellValueFactory(new PropertyValueFactory<InjectionView, Long>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<InjectionView, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<InjectionView, String>("lastName"));
        nickNameColumn.setCellValueFactory(new PropertyValueFactory<InjectionView, String>("nickName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<InjectionView, String>("email"));

        logger.info("InjectionController initalized");
    }

    private ObservableList<InjectionView> initData(String input)
    {

        List<InjectionView> dummy_table = Service.getInjectionView(input);
        return FXCollections.observableArrayList(dummy_table);
    }

    public void handleCustomButton(ActionEvent actionEvent)
    {
        String input = injectionTextField.getText();
        ObservableList<InjectionView> observableList = initData(input);
        injectionTable.setItems(observableList);
    }

    public void handleDropTable(ActionEvent actionEvent) {
        ObservableList<InjectionView> observableList = initData("1; DROP TABLE bds.dummy");
        injectionTable.setItems(observableList);
    }

    public void handleOrButton(ActionEvent actionEvent) {
        ObservableList<InjectionView> observableList = initData("1 OR 1=1");
        injectionTable.setItems(observableList);
    }
}