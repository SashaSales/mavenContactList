package appDB;

import exceptions.ContactNullPointerException;
import exceptions.RemoveContactException;
import exceptions.WordIsEmpty;
import model.Contact;

import java.io.IOException;
import java.io.Serializable;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by work on 10.09.2016.
 */
public interface IappDB extends Serializable {

    Contact removeContact(String name) throws RemoveContactException;
    boolean addContact(String name, String phoneNumber, String email);
    Contact findContact(String name) throws WordIsEmpty, ContactNullPointerException;
    Set<Contact> getContactList();
    Set<String> getContactLife();
    Set<String> getContactKievstar();
    Set<String> getContactMTS();
    boolean saveDB(String pathDB) throws IOException;
    IappDB loadDB(String pathDB) throws IOException;
    TreeSet<String> getNamesContactList();

}
