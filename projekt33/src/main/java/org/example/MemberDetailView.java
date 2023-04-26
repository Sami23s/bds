package org.example;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MemberDetailView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty telephone = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private StringProperty streetNumber = new SimpleStringProperty();

    public long getId() {
        return idProperty().get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.idProperty().set(id);
    }

    public String getEmail() {
        return emailProperty().get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.emailProperty().set(email);
    }

    public String getName() {
        return nameProperty().get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.nameProperty().set(name);
    }

    public String getSurname() {
        return surnameProperty().get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surnameProperty().set(surname);
    }

    public String getTelephone() {
        return telephoneProperty().get();
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephoneProperty().set(telephone);
    }

    public String getCity() {
        return cityProperty().get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.cityProperty().set(city);
    }

    public String getStreet() {
        return streetProperty().get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.streetProperty().set(street);
    }

    public String getStreetNumber() {
        return streetNumberProperty().get();
    }

    public StringProperty streetNumberProperty() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumberProperty().set(streetNumber);
    }
}
