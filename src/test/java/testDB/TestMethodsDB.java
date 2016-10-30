package testDB;

import appDB.IappDB;
import constants.OperatorModifiers;
import controller.IControllerlist;
import exceptions.ContactNullPointerException;
import exceptions.WordIsEmpty;
import model.Contact;
import org.junit.Assert;
import org.junit.Test;
import testController.InitData;
import testController.UtilsForTestMethod;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;


/**
 * Created by work on 09.10.2016.
 */
public class TestMethodsDB {


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


}
