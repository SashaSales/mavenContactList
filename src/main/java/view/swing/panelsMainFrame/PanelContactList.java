package view.swing.panelsMainFrame;

import controller.IControllerlist;
import model.Contact;

import javax.swing.*;
import java.awt.*;
import java.util.NavigableSet;

/**
 * Created by work on 28.09.2016.
 */
public class PanelContactList extends Component {


    public JList<String> createPanelContactList(String[] contactName, Color color) {

        JList<String> listOfContacts = new JList<>();
        listOfContacts.setVisible(true);
        listOfContacts.setListData(contactName);
        listOfContacts.setBackground(color);
        return listOfContacts;
    }

}
