package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.Optional;

public class MemberCreateController {

    @FXML
    private Button newPersonCreatePerson;

    @FXML
    private TextField newPersonEmail;

    @FXML
    private TextField newPersonFamilyName;

    @FXML
    private TextField newPersonGivenName;

    @FXML
    private TextField newPersonWeight;

    @FXML
    private TextField newPersonbirth;

    @FXML
    private appService memberService;
    private appRepository memberrepository;



    @FXML
    private static final Logger logger = LoggerFactory.getLogger(MemberCreateController.class);

    public void initialize() {
        memberrepository = new appRepository();
        memberService = new appService(memberrepository);






        logger.info("PersonCreateController initialized");
    }

    @FXML
    void handleCreateNewPerson(ActionEvent event) {


        String name = newPersonGivenName.getText();
        String surname = newPersonFamilyName.getText();
        String birthday = newPersonbirth.getText();
        String weight= newPersonWeight.getText();


        memberCreateView personCreateView = new memberCreateView();
        personCreateView.setName(name);
        personCreateView.setSurname(surname);
        personCreateView.setBirthday(Date.valueOf(birthday));
        personCreateView.setWeight(Integer.valueOf(weight));



        memberService.createPerson(personCreateView);

        personCreatedConfirmationDialog();
    }

    private void personCreatedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Person Created Confirmation");
        alert.setHeaderText("Your person was successfully created.");

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
