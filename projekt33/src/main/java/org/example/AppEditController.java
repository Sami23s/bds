package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.Date;

import org.controlsfx.validation.ValidationSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Optional;

public class AppEditController {
    private static final Logger logger = LoggerFactory.getLogger(AppEditController.class);

    @FXML
    public Button editPersonButton;
    @FXML
    public TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField birthdayTextField;
    @FXML
    private TextField weightTextField;



    private appService Service;
    private appRepository Repository;
    private ValidationSupport validation;

    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        Repository = new appRepository();
        Service = new appService(Repository);



        loadPersonsData();

        logger.info("PersonsEditController initialized");
    }

    private void loadPersonsData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof appBasicView) {
            appBasicView BasicView = (appBasicView) stage.getUserData();
            nameTextField.setText(BasicView.getName());
            surnameTextField.setText(BasicView.getSurname());
            birthdayTextField.setText(String.valueOf(BasicView.getBirthday()));
            weightTextField.setText(String.valueOf(BasicView.getWeight()));

        }
    }

    @FXML
    public void handleEditPersonButton(ActionEvent event) {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String birthday= birthdayTextField.getText();
        Integer weight = Integer.valueOf(weightTextField.getText());


        appEditView AppEd= new appEditView();
        AppEd.setName(name);
        AppEd.setSurname(surname);
        AppEd.setBirthday(Date.valueOf(birthday));
        AppEd.setWeight(weight);


        Service.editPerson(AppEd);

        personEditedConfirmationDialog();
    }

    private void personEditedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Member edit confirmation");
        alert.setHeaderText("Member edited.");

        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();
        Optional<ButtonType> result = alert.showAndWait();
    }
}
