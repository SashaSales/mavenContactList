package view.swing.frames;

import controller.IControllerlist;
import exceptions.ContactNullPointerException;
import exceptions.WordIsEmpty;
import logger.LoggerContainer;
import model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by work on 25.09.2016.
 */
public class ViewInfoContact extends JFrame {

    private String oldContactName;
    private IControllerlist iControllerlist;
    ViewMainFrame viewMainFrame;

    public ViewInfoContact(ViewMainFrame viewMainFrame, IControllerlist iControllerlist, String oldContactName) throws HeadlessException {

        this.viewMainFrame = viewMainFrame;
        this.iControllerlist = iControllerlist;
        this.oldContactName = oldContactName;
        setMinimumSize(new Dimension(350,200));
        setMaximumSize(new Dimension(500, 350));

        setVisible(true);
        setTitle("Contact info");
        setLocationRelativeTo(null);
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    private void init() {

        GridLayout gridLayout = new GridLayout(7,1);
        setLayout(gridLayout);

        Contact findContact = null;

        try {
            findContact = iControllerlist.findContact(oldContactName);
        } catch (WordIsEmpty wordIsEmpty) {
            LoggerContainer.logEvent("JFrame ViewInfoContact method init, proccess findContact, Contact name is Empty: " + wordIsEmpty.getMessage());
            wordIsEmpty.printStackTrace();
        } catch (IOException e) {
            LoggerContainer.logEvent("JFrame ViewInfoContact method init, proccess findContact, IOException error: " + e.getMessage());
            e.printStackTrace();
        } catch (ContactNullPointerException e) {
            LoggerContainer.logEvent("JFrame ViewInfoContact method init, proccess findContact, ContactNullPointerException error: " + e.getMessage());
            e.printStackTrace();
        }


        JLabel nameLabel = new JLabel("Contact name ");
        JTextField nameText = new JTextField();
        nameText.setText(findContact.getName());
        JLabel phoneLabel = new JLabel("Contact phone");
        JTextField phoneText = new JTextField();
        phoneText.setText(findContact.getPhoneNumber());
        JLabel emailLabel = new JLabel("Contact e-mail");
        JTextField emailText = new JTextField();
        emailText.setText(findContact.getEmail());

        JPanel floorButtons = new JPanel(new GridLayout(1,2));
        JButton upDateButton = new JButton("UpDate");
        JButton exitButton = new JButton("Exit");
        floorButtons.add(upDateButton);
        floorButtons.add(exitButton);


        upDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (nameText.getText().isEmpty() || phoneText.getText().isEmpty()){
                    JOptionPane.showConfirmDialog(ViewInfoContact.this, "Contact name or Contact phone is Empty", "ERROR", JOptionPane.CLOSED_OPTION);
                    dispose();

                }

                String email = emailText.getText();
                if (email.isEmpty()){
                    email = null;
                }

                if (!nameText.getText().isEmpty() && !phoneText.getText().isEmpty()) {

                    Contact findContact = null;

                    try {
                        findContact = iControllerlist.findContact(oldContactName);
                    } catch (WordIsEmpty wordIsEmpty) {
                        LoggerContainer.logEvent("JFrame ViewInfoContact upDateButton addActionListener, proccess findContact, Contact name is Empty: " + wordIsEmpty.getMessage());
                        wordIsEmpty.printStackTrace();
                    } catch (IOException e1) {
                        LoggerContainer.logEvent("JFrame ViewInfoContact upDateButton addActionListener, proccess findContact, IOException error: " + e1.getMessage());
                        e1.printStackTrace();
                    } catch (ContactNullPointerException e1) {
                        LoggerContainer.logEvent("JFrame ViewInfoContact upDateButton addActionListener, proccess findContact, ContactNullPointerException error: " + e1.getMessage());
                        e1.printStackTrace();
                    }

                    iControllerlist.getContactList().remove(findContact);
                    iControllerlist.getContactList().add(new Contact(nameText.getText(), phoneText.getText(), emailText.getText()));
                    iControllerlist.getNamesContactList().remove(oldContactName);
                    iControllerlist.getNamesContactList().add(nameText.getText());

                    viewMainFrame.refreshInfo();
                    dispose();

                }
            }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        getContentPane().add(nameLabel);
        getContentPane().add(nameText);
        getContentPane().add(phoneLabel);
        getContentPane().add(phoneText);
        getContentPane().add(emailLabel);
        getContentPane().add(emailText);
        getContentPane().add(floorButtons);


    }

}
