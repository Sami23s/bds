package org.example;

import javafx.beans.property.*;


public class appBasicView {
    private final LongProperty id=new SimpleLongProperty();
    private final StringProperty name=new SimpleStringProperty();
    private final StringProperty surname=new SimpleStringProperty();
    private final StringProperty birthday=new SimpleStringProperty();
    private final IntegerProperty weight=new SimpleIntegerProperty();
    private final StringProperty address=new SimpleStringProperty();
    private final StringProperty membership=new SimpleStringProperty();

    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public int getWeight() {
        return weight.get();
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getMembership() {
        return membership.get();
    }

    public StringProperty membershipProperty() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership.set(membership);
    }
}
