package controller;

import constants.Contstants;
import exceptions.ContactNullPointerException;
import exceptions.RemoveContactException;
import exceptions.WordIsEmpty;
import appDB.IappDB;
import model.Contact;
import utils.IOutilsImpl;

import java.io.IOException;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by work on 10.09.2016.
 */
public class ControllerListImpl implements IControllerlist{

    private IappDB iappDB;

    public ControllerListImpl(IappDB iappDB) {
        this.iappDB = iappDB;
    }

    @Override
    public boolean addContact(String name, String phoneNumber, String email){

        return iappDB.addContact(name, phoneNumber, email);
    }

    @Override
    public Contact removeContact(String name) throws RemoveContactException {

        return iappDB.removeContact(name);
    }


    @Override
    public Contact findContact(String name) throws WordIsEmpty, ContactNullPointerException {

        Contact contact = iappDB.findContact(name);

        return contact;
    }

    @Override
    public Set<Contact> getContactList() {

        return iappDB.getContactList();
    }

    @Override
    public Set<String> getContactLife() {
        return iappDB.getContactLife();
    }

    @Override
    public Set<String> getContactKievstar() {
        return iappDB.getContactKievstar();
    }

    @Override
    public Set<String> getContactMTS() {
        return iappDB.getContactMTS();
    }

    @Override
    public boolean saveDB(String pathDB) throws IOException {

        return iappDB.saveDB(pathDB);
    }

    @Override
    public IappDB loadDB(String pathDB) throws IOException {

        return iappDB.loadDB(pathDB);
    }

    @Override
    public String toString() {

        return iappDB.toString();
    }

    public IappDB getIappDB() {
        return iappDB;
    }


    public TreeSet<String> getNamesContactList(){

        return iappDB.getNamesContactList();
    }

}
