package org.example;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.example.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    public boolean login(String username, String password) {
        LoginView loginView = loginRepository.getLoginView(username);

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id); //instance of the Argon2 interface
        String hash = argon2.hash(4, 1024 * 1024, 8, password); // encoded password hash
        System.out.println("hashed password is: " + hash);

        if (loginView != null) {
            System.out.println(password);
            System.out.println(loginView.getHashed_password());
            return argon2.verify(hash, loginView.getHashed_password());

        } else {
            return false;
        }
    }
}
