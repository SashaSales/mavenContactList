package testController;

import constants.OperatorModifiers;
import model.Contact;
import org.junit.Assert;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by work on 27.10.2016.
 */
public class UtilsForTestMethod {

    public static void utilForEquelsContactList(Set<Contact> contactList1, Set<Contact> contactList2, String nameMethod) {

        int sizeContactsList1 = contactList1.size();
        Contact[] massContacts1 = contactList1.toArray(new Contact[sizeContactsList1]);
        int sizeContactsList2 = contactList2.size();
        Contact[] massContacts2 = contactList2.toArray(new Contact[sizeContactsList2]);

        if (sizeContactsList1 == sizeContactsList2) {
            for (int i = 0; i < sizeContactsList2; i++) {
                if (!(massContacts1[i].getName().equals(massContacts2[i].getName()))){
                    Assert.assertEquals("Test method " + nameMethod, false, true);
                }
            }

        } else {
            Assert.assertEquals("Test method " + nameMethod, false, true);
        }

        Assert.assertEquals("Test method " + nameMethod, true, true);

    }

    public static void utilsOperatorEqual(Set<String> operatorContacts, Set<String> gettedContactOperator, String nameMethod) {

        int sizeOperatorContacts = operatorContacts.size();
        String[] massContacts1 = operatorContacts.toArray(new String[sizeOperatorContacts]);
        int sizeGettedContactOperator = gettedContactOperator.size();
        String[] massContacts2 = gettedContactOperator.toArray(new String[sizeGettedContactOperator]);

        if (sizeOperatorContacts == sizeGettedContactOperator) {
            for (int i = 0; i < sizeGettedContactOperator; i++) {
                if (!(massContacts1[i].equals(massContacts2[i]))){
                    Assert.assertEquals("Test method " + nameMethod, false, true);
                }
            }

        } else {
            Assert.assertEquals("Test method " + nameMethod, false, true);
        }

        Assert.assertEquals("Test method " + nameMethod, true, true);

    }


    public static Set<String> operatorContacts(Set<Contact> gettedAllContacts, OperatorModifiers operatorModifier) {

        Set<String> operatorContacts = new TreeSet<>();
        for (Contact contact : gettedAllContacts) {
            if(contact.getOperatorModifiers().equals(operatorModifier)){
                operatorContacts.add(contact.getName());
            }
        }

        return operatorContacts;
    }

}
