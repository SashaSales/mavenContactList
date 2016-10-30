package testController;

import appDB.IappDB;
import constants.OperatorModifiers;
import controller.IControllerlist;
import exceptions.ContactNullPointerException;
import exceptions.WordIsEmpty;
import model.Contact;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;


/**
 * Created by work on 09.10.2016.
 */
public class TestMethodsController {

    @Test
    public void addContact() {

        IControllerlist iControllerlist = InitData.createTestData();
        boolean addContactResult = iControllerlist.addContact("Smit", "0937721749", "wieurw@gmail.com");
        Assert.assertEquals("Test method AddContact", true, addContactResult);
    }

    @Test
    public void deleteContact() {
        IControllerlist iControllerlist = InitData.createTestData();
        Contact contact = new Contact("Timur", "0937721749", "wieurw@gmail.com");
        boolean addContactResult = iControllerlist.getContactList().add(contact);
        boolean deleteContactResult = iControllerlist.getContactList().remove(contact);

        Assert.assertEquals("Test method deleteContact ", true, addContactResult && deleteContactResult);
    }

    @Test
    public void saveDB() {
        System.out.println();

        IControllerlist iControllerlist = InitData.createTestData();
        String pathDB = "src/test/testDBForSave.txt";

        int testDBForNull = 0;
        File fileDB = new File(pathDB);
        try {
            iControllerlist.saveDB(pathDB);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(
                    new FileInputStream(fileDB));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            testDBForNull = bis.read();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Assert.assertNotEquals("Test method saveDB", testDBForNull, -1);

        fileDB.delete();

    }

    @Test
    public void loadDB() {

        System.out.println();

        IControllerlist iControllerlist = InitData.createTestData();
        String pathDB = "src/test/testDBForLoad.txt";
        File fileDB = new File(pathDB);

        try {
            iControllerlist.saveDB(pathDB);
        } catch (IOException e) {
            e.printStackTrace();
        }

        IappDB iappDB = null;
        try {
            iappDB = iControllerlist.loadDB(pathDB);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Contact> beforeLoadContactList = iControllerlist.getContactList();
        Set<Contact> afterLoadContactList = iappDB.getContactList();

        UtilsForTestMethod.utilForEquelsContactList(beforeLoadContactList, afterLoadContactList, "loadDB");

        fileDB.delete();

    }

    @Test
    public void getNamesContactList() {

        IControllerlist iControllerlist = InitData.createTestData();
        Set<String> namesContactList = iControllerlist.getNamesContactList();
        Set<Contact> gettedAllContacts = iControllerlist.getContactList();

        Set<String> nameContacts = new TreeSet<>();
        for (Contact contact : gettedAllContacts) {
            nameContacts.add(contact.getName());
        }

        UtilsForTestMethod.utilsOperatorEqual(nameContacts, namesContactList, "getNamesContactList");
    }

    @Test
    public void getContactMTS() {
        System.out.println();
        IControllerlist iControllerlist = InitData.createTestData();
        Set<String>  gettedContactMTS = iControllerlist.getContactMTS();
        Set<Contact> gettedAllContacts = iControllerlist.getContactList();

        Set<String> operatorContacts = UtilsForTestMethod.operatorContacts(gettedAllContacts, OperatorModifiers.OPERATOR_MTS);

        UtilsForTestMethod.utilsOperatorEqual(operatorContacts, gettedContactMTS, "getContactMTS");
    }


    @Test
    public void getContactKievstar() {
        System.out.println();
        IControllerlist iControllerlist = InitData.createTestData();
        Set<String>  gettedContactKievstar = iControllerlist.getContactKievstar();
        Set<Contact> gettedAllContacts = iControllerlist.getContactList();

        Set<String> operatorContacts = UtilsForTestMethod.operatorContacts(gettedAllContacts, OperatorModifiers.OPERATOR_KIEVSTAR);

        UtilsForTestMethod.utilsOperatorEqual(operatorContacts, gettedContactKievstar, "getContactKievstar");
    }

    @Test
    public void getContactLife() {
        System.out.println();
        IControllerlist iControllerlist = InitData.createTestData();
        Set<String>  gettedContactLife = iControllerlist.getContactLife();
        Set<Contact> gettedAllContacts = iControllerlist.getContactList();
        Set<String> operatorContacts = UtilsForTestMethod.operatorContacts(gettedAllContacts, OperatorModifiers.OPERATOR_LIFE);

        UtilsForTestMethod.utilsOperatorEqual(operatorContacts, gettedContactLife, "getContactLife");

    }


    @Test
    public void getContactList() {

        System.out.println();
        IControllerlist iControllerlist = InitData.createTestData();
        Set<Contact>  gettedContactList = iControllerlist.getContactList();

        IappDB iappDB = null;
        try {
            iappDB = iControllerlist.getIappDB();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Contact> contactList = iappDB.getContactList();
        UtilsForTestMethod.utilForEquelsContactList(gettedContactList, contactList, "getContactList");

    }

    @Test
    public void findContactNegativeTest() {

        IControllerlist iControllerlist = InitData.createTestData();
        iControllerlist.addContact("Timur", "0937721749", "wieurw@gmail.com");

        Contact findContact1 = null;
        Contact findContact2 = null;

        boolean WordIsEmptyResult = false;
        boolean NotFoundContactResult = false;


        try {
            findContact1 = iControllerlist.findContact("");
        } catch (WordIsEmpty wordIsEmpty) {
            WordIsEmptyResult = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ContactNullPointerException e) {
            e.printStackTrace();
        }


        try {
            findContact2 = iControllerlist.findContact("Tim");
        } catch (WordIsEmpty wordIsEmpty) {
            wordIsEmpty.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ContactNullPointerException e) {
            NotFoundContactResult = true;
        }

        Assert.assertEquals("Test method findContactNegativeTest ", true, WordIsEmptyResult && NotFoundContactResult);
    }

    @Test
    public void findContactPositiveTest() {

        IControllerlist iControllerlist = InitData.createTestData();
        Contact newContact = new Contact("Timur", "0937721749", "wieurw@gmail.com");
        boolean AddContactResult = iControllerlist.getContactList().add(newContact);
        Contact findContact = null;

        try {
            findContact = iControllerlist.findContact("Timur");
        } catch (WordIsEmpty wordIsEmpty) {
            wordIsEmpty.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ContactNullPointerException e) {
            e.printStackTrace();
        }

        boolean equelsContactsResult = findContact.getName().equals(newContact.getName()) &&
                findContact.getPhoneNumber().equals(newContact.getPhoneNumber()) &&
                findContact.getEmail().equals(newContact.getEmail());

        Assert.assertEquals("Test method findContactPositiveTest ", true, AddContactResult && equelsContactsResult);

    }

}

// Test method from public static void main.............................................................................
   /* public static void main(String[] args) {

        addContact();
        deleteContact();
        findContactPositiveTest();
        findContactNegativeTest();
        saveDB();
        loadDB();

        getContactList();
        getContactLife();
        getContactKievstar();
        getContactMTS();
        getNamesContactList();

    }



    private static void getNamesContactList() {
        System.out.println();
        IControllerlist iControllerlist = InitData.createTestData();
        Set<String> namesContactList = iControllerlist.getNamesContactList();
        Set<Contact> gettedAllContacts = iControllerlist.getContactList();

        Set<String> nameContacts = new TreeSet<>();
        for (Contact contact : gettedAllContacts) {
            nameContacts.add(contact.getName());

        }

        utilsOperatorEqual(nameContacts, namesContactList, "getNamesContactList");

    }



    private static void getContactMTS() {
        System.out.println();
        IControllerlist iControllerlist = InitData.createTestData();
        Set<String>  gettedContactMTS = iControllerlist.getContactMTS();
        Set<Contact> gettedAllContacts = iControllerlist.getContactList();

        Set<String> operatorContacts = operatorContacts(gettedAllContacts, OperatorModifiers.OPERATOR_MTS);

        utilsOperatorEqual(operatorContacts, gettedContactMTS, "getContactMTS");
    }



    private static void getContactKievstar() {
        System.out.println();
        IControllerlist iControllerlist = InitData.createTestData();
        Set<String>  gettedContactKievstar = iControllerlist.getContactKievstar();
        Set<Contact> gettedAllContacts = iControllerlist.getContactList();

        Set<String> operatorContacts = operatorContacts(gettedAllContacts, OperatorModifiers.OPERATOR_KIEVSTAR);

        utilsOperatorEqual(operatorContacts, gettedContactKievstar, "getContactKievstar");
    }

    private static void getContactLife() {
        System.out.println();
        IControllerlist iControllerlist = InitData.createTestData();
        Set<String>  gettedContactLife = iControllerlist.getContactLife();
        Set<Contact> gettedAllContacts = iControllerlist.getContactList();
        Set<String> operatorContacts = operatorContacts(gettedAllContacts, OperatorModifiers.OPERATOR_LIFE);

        utilsOperatorEqual(operatorContacts, gettedContactLife, "getContactLife");

    }



    private static void getContactList() {

        System.out.println();
        IControllerlist iControllerlist = InitData.createTestData();
        Set<Contact>  gettedContactList = iControllerlist.getContactList();

        IappDB iappDB = null;
        try {
            iappDB = iControllerlist.getIappDB();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Contact> contactList = iappDB.getContactList();
        utilForEquelsContactList(gettedContactList, contactList, "getContactList");

    }



    private static void loadDB() {

        System.out.println();

        IControllerlist iControllerlist = InitData.createTestData();
        String pathDB = "src/test/testDBForLoad.txt";
        File fileDB = new File(pathDB);

        try {
            iControllerlist.saveDB(pathDB);
        } catch (IOException e) {
            e.printStackTrace();
        }

        IappDB iappDB = null;
        try {
            iappDB = iControllerlist.loadDB(pathDB);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Contact> beforeLoadContactList = iControllerlist.getContactList();
        Set<Contact> afterLoadContactList = iappDB.getContactList();

        utilForEquelsContactList(beforeLoadContactList, afterLoadContactList, "loadDB");

        fileDB.delete();

    }



    private static void saveDB() {
        System.out.println();

        IControllerlist iControllerlist = InitData.createTestData();
        String pathDB = "src/test/testDBForSave.txt";

        int testDBForNull = 0;
        File fileDB = new File(pathDB);
        try {
            iControllerlist.saveDB(pathDB);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(
                        new FileInputStream(fileDB));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            testDBForNull = bis.read();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (testDBForNull != -1){
            System.out.printf("Test method saveDB: %b. Expected true", true, true);
        } else {
            System.out.printf("Test method saveDB: %b. Expected true", false, true);
        }

        fileDB.delete();

    }


    private static void findContactNegativeTest() {

        IControllerlist iControllerlist = InitData.createTestData();
        iControllerlist.addContact("Timur", "0937721749", "wieurw@gmail.com");

        Contact findContact1 = null;
        Contact findContact2 = null;

        boolean WordIsEmptyResult = false;
        boolean NotFoundContactResult = false;


        try {
            findContact1 = iControllerlist.findContact("");
        } catch (WordIsEmpty wordIsEmpty) {
            WordIsEmptyResult = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ContactNullPointerException e) {
            e.printStackTrace();
        }


        try {
            findContact2 = iControllerlist.findContact("Tim");
        } catch (WordIsEmpty wordIsEmpty) {
            wordIsEmpty.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ContactNullPointerException e) {
            NotFoundContactResult = true;
        }


        System.out.println();
        System.out.printf("Test method findContactNegativeTest: %b. Expected true", WordIsEmptyResult && NotFoundContactResult, true);
    }

    private static void findContactPositiveTest() {

        IControllerlist iControllerlist = InitData.createTestData();
        Contact newContact = new Contact("Timur", "0937721749", "wieurw@gmail.com");
        boolean AddContactResult = iControllerlist.getContactList().add(newContact);
        Contact findContact = null;


        try {
            findContact = iControllerlist.findContact("Timur");
        } catch (WordIsEmpty wordIsEmpty) {
            wordIsEmpty.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ContactNullPointerException e) {
            e.printStackTrace();
        }


        boolean equelsContactsResult = findContact.getName().equals(newContact.getName()) &&
                            findContact.getPhoneNumber().equals(newContact.getPhoneNumber()) &&
                                findContact.getEmail().equals(newContact.getEmail());



        System.out.println();
        System.out.printf("Test method findContactPositiveTest: %b. Expected true", AddContactResult && equelsContactsResult, true);
    }

    private static void addContact() {
        IControllerlist iControllerlist = InitData.createTestData();
        boolean addContactResult = iControllerlist.addContact("Smit", "0937721749", "wieurw@gmail.com");
        System.out.printf("Test method AddContact: %b. Expected true", addContactResult);
    }

    private static void deleteContact() {
        IControllerlist iControllerlist = InitData.createTestData();
        Contact contact = new Contact("Timur", "0937721749", "wieurw@gmail.com");
        boolean addContactResult = iControllerlist.getContactList().add(contact);
        boolean deleteContactResult = iControllerlist.getContactList().remove(contact);

        System.out.println();
        System.out.printf("Test method deleteContact: %b. Expected true", addContactResult && deleteContactResult, true);
    }




    private static void utilForEquelsContactList(Set<Contact> contactList1, Set<Contact> contactList2, String nameMethod) {

        int sizeContactsList1 = contactList1.size();
        Contact[] massContacts1 = contactList1.toArray(new Contact[sizeContactsList1]);
        int sizeContactsList2 = contactList2.size();
        Contact[] massContacts2 = contactList2.toArray(new Contact[sizeContactsList2]);

        if (sizeContactsList1 == sizeContactsList2) {
            for (int i = 0; i < sizeContactsList2; i++) {
                if (!(massContacts1[i].getName().equals(massContacts2[i].getName()))){
                    System.out.printf("Test method %s: %b. Expected true", nameMethod, false, true);
                }
            }

        } else {
            System.out.printf("Test method %s: %b. Expected true", nameMethod, false, true);
        }

        System.out.printf("Test method %s: %b. Expected true", nameMethod, true, true);

    }

    private static void utilsOperatorEqual(Set<String> operatorContacts, Set<String> gettedContactOperator, String nameMethod) {

        int sizeOperatorContacts = operatorContacts.size();
        String[] massContacts1 = operatorContacts.toArray(new String[sizeOperatorContacts]);
        int sizeGettedContactOperator = gettedContactOperator.size();
        String[] massContacts2 = gettedContactOperator.toArray(new String[sizeGettedContactOperator]);

        if (sizeOperatorContacts == sizeGettedContactOperator) {
            for (int i = 0; i < sizeGettedContactOperator; i++) {
                if (!(massContacts1[i].equals(massContacts2[i]))){
                    System.out.printf("Test method %s: %b. Expected true", nameMethod, false, true);
                }
            }

        } else {
            System.out.printf("Test method %s: %b. Expected true", nameMethod, false, true);
        }

        System.out.printf("Test method %s: %b. Expected true", nameMethod, true, true);

    }


    private static Set<String> operatorContacts(Set<Contact> gettedAllContacts, OperatorModifiers operatorModifier) {

        Set<String> operatorContacts = new TreeSet<>();
        for (Contact contact : gettedAllContacts) {
            if(contact.getOperatorModifiers().equals(operatorModifier)){
                operatorContacts.add(contact.getName());
            }
        }

        return operatorContacts;
    }
*/
