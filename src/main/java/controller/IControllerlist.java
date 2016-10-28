package controller;

import exceptions.ContactNullPointerException;
import exceptions.RemoveContactException;
import exceptions.WordIsEmpty;
import appDB.IappDB;
import model.Contact;

import java.io.IOException;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by work on 10.09.2016.
 */
public interface IControllerlist {

    boolean addContact(String name, String phoneNumber, String email);
    Contact removeContact(String name) throws RemoveContactException, IOException;
    Contact findContact(String name) throws WordIsEmpty, IOException, ContactNullPointerException;
    Set<String> getContactKievstar();
    Set<Contact> getContactList();
    Set<String> getContactMTS();
    Set<String> getContactLife();
    boolean saveDB(String pathDB) throws IOException;
    IappDB loadDB(String pathDB) throws IOException;
    IappDB getIappDB() throws IOException;
    TreeSet<String> getNamesContactList();

}
