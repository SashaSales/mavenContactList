package model;

import constants.OperatorModifiers;
import validation.Validation;

import java.io.Serializable;

/**
 * Created by work on 09.08.2016.
 */
public class Contact implements Comparable<Contact>, Serializable {

    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private Adress adress;
    private OperatorModifiers operatorModifiers;


    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.operatorModifiers = Validation.Operator(phoneNumber);

    }

    public Contact(String name, String phoneNumber, String email, Adress adress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.adress = adress;
        this.operatorModifiers = Validation.Operator(phoneNumber);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public OperatorModifiers getOperatorModifiers() {
        return operatorModifiers;
    }

    public void setOperatorLifeKievstar(OperatorModifiers operatorModifier) {
        this.operatorModifiers = operatorModifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + "       " + phoneNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return name != null ? name.equals(contact.name) : contact.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public int compareTo(Contact o) {
        return this.getName().compareTo(o.getName());
    }
}
