package view.swing.panelsMainFrame;

import constants.Contstants;
import controller.IControllerlist;
import exceptions.ContactNullPointerException;
import exceptions.WordIsEmpty;
import logger.LoggerContainer;
import model.Contact;
import utils.IOutilsImpl;
import view.swing.frames.ViewAddContact;
import view.swing.frames.ViewInfoContact;
import view.swing.frames.ViewMainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by work on 08.10.2016.
 */
public class PanelFunctionButtons {

    private IControllerlist iControllerlist;
    private JList<String> listOfContacts;

    public JPanel createPanelFunctionButtons(ViewMainFrame viewMainFrame, IControllerlist iControllerlist, JList<String> listOfContacts) {


        JPanel functionalButtons = new JPanel(new GridLayout(1, 4));
        JButton addContact = new JButton("Add");
        addContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAddContact(iControllerlist, listOfContacts);
            }
        });

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int n = JOptionPane.showConfirmDialog(viewMainFrame, "Сохранить все изменения?", "Сохранение списка контактов",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                switch (n) {
                    case JOptionPane.YES_OPTION:
                        try {

                            String pathDB = IOutilsImpl.loadPathFromFile(Contstants.PATH_FOR_SAVEDB);
                            iControllerlist.saveDB(pathDB);
                            viewMainFrame.dispose();
                            break;
                        } catch (IOException e1) {
                            LoggerContainer.logEvent("exit addActionListener, proccess loadPathFromFile, error: " + e1.getMessage());
                            e1.printStackTrace();
                        }
                    case JOptionPane.NO_OPTION:
                        viewMainFrame.dispose();
                        break;
                    case JOptionPane.CANCEL_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                        break;
                }

            }
        });

        ImageIcon deleteContactIcon = new ImageIcon(Contstants.IMAGE_DELETE);
        ImageIcon infoContactIcon = new ImageIcon(Contstants.IMAGE_INFO);

        JButton deleteContactButton = new JButton();
        deleteContactButton.setIcon(deleteContactIcon);
        deleteContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (listOfContacts.getSelectedValue() != null) {


                    Contact findContact = null;
                    try {
                        findContact = iControllerlist.findContact(listOfContacts.getSelectedValue());
                    } catch (WordIsEmpty wordIsEmpty) {
                        LoggerContainer.logEvent("deleteContactButton addActionListener, proccess findContact, Contact name is Empty: " + wordIsEmpty.getMessage());
                        wordIsEmpty.printStackTrace();
                    } catch (IOException e1) {
                        LoggerContainer.logEvent("deleteContactButton addActionListener, proccess findContact, IOException error: " + e1.getMessage());
                        e1.printStackTrace();
                    } catch (ContactNullPointerException e1) {
                        LoggerContainer.logEvent("deleteContactButton addActionListener, proccess findContact, ContactNullPointerException error: " + e1.getMessage());
                        e1.printStackTrace();
                    }
                    iControllerlist.getContactList().remove(findContact);
                    iControllerlist.getNamesContactList().remove(listOfContacts.getSelectedValue());
                    LoggerContainer.logEvent("Remove contact by name: " + listOfContacts.getSelectedValue());
                    viewMainFrame.refreshInfo();


                } else {
                    JOptionPane.showConfirmDialog(viewMainFrame, "You not choose anyone Contact, please chose Contact", "ERROR", JOptionPane.CLOSED_OPTION);
                }
            }
        });

        JButton infoContactButton = new JButton();
        infoContactButton.setIcon(infoContactIcon);
        infoContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (listOfContacts.getSelectedValue() != null) {

                    new ViewInfoContact(viewMainFrame, iControllerlist, listOfContacts.getSelectedValue());

                    viewMainFrame.refreshInfo();

                } else {
                    JOptionPane.showConfirmDialog(viewMainFrame, "You not choose anyone Contact, please chose Contact", "ERROR", JOptionPane.CLOSED_OPTION);
                }

            }
        });

        functionalButtons.add(addContact);
        functionalButtons.add(infoContactButton);
        functionalButtons.add(deleteContactButton);
        functionalButtons.add(exit);

        return functionalButtons;
    }

}
