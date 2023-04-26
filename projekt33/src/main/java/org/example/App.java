package org.example;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaFX App
 */
public class App extends Application {
    private FXMLLoader loader;
    private VBox mainStage;

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            mainStage = loader.load();
            stage.setTitle("GYM");
            Scene scene = new Scene(mainStage);
            setUserAgentStylesheet(STYLESHEET_MODENA);
            stage.getIcons().add(new Image(App.class.getResourceAsStream("69840.png")));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception in method start: " + e.getMessage());
        }
    }







}