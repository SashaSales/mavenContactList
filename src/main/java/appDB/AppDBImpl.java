package appDB;

import constants.OperatorModifiers;
import exceptions.ContactNullPointerException;
import exceptions.RemoveContactException;
import exceptions.WordIsEmpty;
import model.Contact;
import utils.IOutilsImpl;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by work on 10.09.2016.
 */
public class AppDBImpl implements IappDB, Serializable {

    private Set<Contact> contactList = new TreeSet<>();
    private TreeSet<String> namesContactList = new TreeSet<>();
    private IOutilsImpl ioUtils = new IOutilsImpl();
    private int freeIndexContact;

    public AppDBImpl() {
    }

    @Override
    public boolean addContact(String name, String phoneNumber, String email) {

        Contact contact = new Contact(name, phoneNumber, email);
        contact.setId(freeIndexContact++);
        namesContactList.add(name);

        return contactList.add(contact);
    }


    @Override
    public Contact removeContact(String name) throws RemoveContactException {

        Contact contactForDelete = null;
        boolean removeRes1 = false;
        boolean removeRes2 = false;

        for (Contact contact : contactList) {
            if (contact.getName().equals(name)) {
                contactForDelete = contact;
                removeRes1 = contactList.remove(contactForDelete);
                removeRes2 = namesContactList.remove(contactForDelete.getName());
            }
        }

        if (removeRes1 && removeRes2) {
            return contactForDelete;
        } else {
            throw new RemoveContactException("Contact don`t remove from DB");
        }

    }


    @Override
    public Contact findContact(String name) throws WordIsEmpty, ContactNullPointerException {

        Contact findContact = null;

        if (name.isEmpty()) {
            throw new WordIsEmpty("WordIsEmpty, please write correct Name");
        }

        for (Contact contact : contactList) {
            if (contact.getName().equals(name)) {
                findContact = contact;
            }
        }

        if (findContact == null) {
            throw new ContactNullPointerException("Contact doesn't exist in DB");
        }

        return findContact;
    }


    @Override
    public Set<Contact> getContactList() {
        return contactList;
    }


    @Override
    public Set<String> getContactLife() {

        Set<String> lifeContacts = new TreeSet<>();

        for (Contact contact : contactList) {
            if (contact.getOperatorModifiers().equals(OperatorModifiers.OPERATOR_LIFE)) {
                lifeContacts.add(contact.getName());
            }
        }

        return lifeContacts;
    }

    @Override
    public Set<String> getContactKievstar() {

        Set<String> kievstarContacts = new TreeSet<>();

        for (Contact contact : contactList) {
            if (contact.getOperatorModifiers().equals(OperatorModifiers.OPERATOR_KIEVSTAR)) {
                kievstarContacts.add(contact.getName());
            }
        }
        return kievstarContacts;
    }


    @Override
    public Set<String> getContactMTS() {

        Set<String> mtsContacts = new TreeSet<>();

        for (Contact contact : contactList) {
            if (contact.getOperatorModifiers().equals(OperatorModifiers.OPERATOR_MTS)) {
                mtsContacts.add(contact.getName());
            }
        }

        return mtsContacts;
    }


    @Override
    public boolean saveDB(String pathDB) throws IOException {

        return IOutilsImpl.saveObjToFile(this, pathDB);
    }

    public IappDB loadDB(String pathDB) throws IOException {

        return (IappDB) IOutilsImpl.loadObjFromFile(pathDB);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Contact contact : contactList) {
            sb.append(contact.toString() + "\n");
        }

        return sb.toString();
    }


    @Override
    public TreeSet<String> getNamesContactList() {
        return namesContactList;
    }
}
