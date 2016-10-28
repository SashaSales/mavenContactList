package logger;

import controller.IControllerlist;
import exceptions.ContactNullPointerException;
import exceptions.RemoveContactException;
import exceptions.WordIsEmpty;
import appDB.IappDB;
import model.Contact;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by work on 22.09.2016.
 */
public class LogerControllerProxy implements IControllerlist{

    private IControllerlist originalControllerlist;

    public LogerControllerProxy(IControllerlist originalControllerlist) {
        this.originalControllerlist = originalControllerlist;
    }

    @Override
    public boolean addContact(String name, String phoneNumber, String email) {

        String event= String.format("Add contact: name - %s, phone - %s, email - %s", name, phoneNumber, email);
        LoggerContainer.logEvent(event);

        return originalControllerlist.addContact(name, phoneNumber, email);
    }


    @Override
    public Contact removeContact(String name) throws RemoveContactException, IOException {

        String event = String.format("Remove contact by name: %s", name);
        LoggerContainer.logEvent(event);

        return originalControllerlist.removeContact(name);
    }


    @Override
    public Contact findContact(String name) throws WordIsEmpty, IOException, ContactNullPointerException {

        String event = String.format("Find contact by name %s", name);
        LoggerContainer.logEvent(event);
        return originalControllerlist.findContact(name);
    }


    @Override
    public Set<Contact> getContactList() {

        String event = "Get contact List ";
        LoggerContainer.logEvent(event);

        return originalControllerlist.getContactList();
    }

    @Override
    public Set<String> getContactLife() {

        String event = "Get contact List Life operator ";
        LoggerContainer.logEvent(event);

        return originalControllerlist.getContactLife();
    }

    @Override
    public Set<String> getContactKievstar() {

        String event = "Get contact List Kievstar operator ";
        LoggerContainer.logEvent(event);

        return originalControllerlist.getContactKievstar();
    }

    @Override
    public Set<String> getContactMTS() {

        String event = "Get contact List MTS operator ";
        LoggerContainer.logEvent(event);

        return originalControllerlist.getContactMTS();
    }

    @Override
    public boolean saveDB(String path) throws IOException {

        String event = "Call function save Data Base ";
        LoggerContainer.logEvent(event);
        return originalControllerlist.saveDB(path);
    }

    @Override
    public IappDB loadDB(String pathDB) throws IOException {

        String event = "Call function load Data Base ";
        LoggerContainer.logEvent(event);
        return originalControllerlist.loadDB(pathDB);
    }

    @Override
    public IappDB getIappDB() throws IOException {

        String event = "Get Date Base ";
        LoggerContainer.logEvent(event);
        return originalControllerlist.getIappDB();
    }


    @Override
    public TreeSet<String> getNamesContactList() {
        return originalControllerlist.getNamesContactList();
    }


}
