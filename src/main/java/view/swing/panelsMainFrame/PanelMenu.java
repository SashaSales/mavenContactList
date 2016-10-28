package view.swing.panelsMainFrame;

import controller.IControllerlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by work on 28.09.2016.
 */
public class PanelMenu {


    public JMenuBar createMenuPanel(IControllerlist iControllerlist, JList<String> listOfContacts){

        JMenuBar menu = new JMenuBar();
        menu.setBackground(Color.cyan);

        JMenu function = new JMenu("File");
        function.setBackground(Color.cyan);

        JMenu reductTheme = new JMenu("Choose color of theme");
        JMenuItem whiteItem = new JMenuItem("White");
        JMenuItem magentaItem = new JMenuItem("Magenta");

        JMenu show = new JMenu("Show");
        show.setBackground(Color.cyan);
        JMenuItem showLifeContact = new JMenuItem("Show Life Contact");
        JMenuItem showMTSContact = new JMenuItem("Show MTS Contact");
        JMenuItem showAllContacts = new JMenuItem("Show All Contacts");
        JMenuItem showKyivstarContact = new JMenuItem("Show Kyivstar Contact");


        reductTheme.add(whiteItem);
        reductTheme.add(magentaItem);
        function.add(reductTheme);

        show.add(showKyivstarContact);
        show.add(showLifeContact);
        show.add(showMTSContact);
        show.add(showAllContacts);

        menu.add(function);
        menu.add(show);


        whiteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listOfContacts.setBackground(Color.white);

            }
        });

        magentaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listOfContacts.setBackground(Color.magenta);

            }
        });



        showLifeContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] contactsName = new String[iControllerlist.getContactLife().size()];

                listOfContacts.setListData(iControllerlist.getContactLife().toArray(contactsName));
            }
        });


        showAllContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] contactsName = new String[iControllerlist.getNamesContactList().size()];

                listOfContacts.setListData(iControllerlist.getNamesContactList().toArray(contactsName));
            }
        });


        showKyivstarContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] contactsName = new String[iControllerlist.getContactKievstar().size()];

                listOfContacts.setListData(iControllerlist.getContactKievstar().toArray(contactsName));
            }
        });


        showMTSContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] contactsName = new String[iControllerlist.getContactMTS().size()];

                listOfContacts.setListData(iControllerlist.getContactMTS().toArray(contactsName));
            }
        });

        return menu;
    }


}
