package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberDetailCont {
    private static final Logger logger = LoggerFactory.getLogger(MemberDetailView.class);

    @FXML
    private TextField idTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField givenNameTextField;

    @FXML
    private TextField familyNameTextField;

    @FXML
    private TextField telephoneTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField streetNumberTextField;

    @FXML
    private TextField streetTextField;

    // used to reference the stage and to get passed data through it
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        idTextField.setEditable(false);
        emailTextField.setEditable(false);
        givenNameTextField.setEditable(false);
        familyNameTextField.setEditable(false);
        telephoneTextField.setEditable(false);
        cityTextField.setEditable(false);
        streetNumberTextField.setEditable(false);
        streetTextField.setEditable(false);

        loadPersonsData();

        logger.info("PersonsDetailViewController initialized");
    }

    private void loadPersonsData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof MemberDetailView) {
            MemberDetailView appBasicView = (MemberDetailView) stage.getUserData();
            idTextField.setText(String.valueOf(appBasicView.getId()));
            emailTextField.setText(appBasicView.getEmail());
            givenNameTextField.setText(appBasicView.getName());
            familyNameTextField.setText(appBasicView.getSurname());
            telephoneTextField.setText(appBasicView.getTelephone());
            cityTextField.setText(appBasicView.getCity());
            streetNumberTextField.setText(appBasicView.getStreetNumber());
            streetTextField.setText(appBasicView.getStreet());
            logger.info("Loading data");
        }
    }
}
