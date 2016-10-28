package view.swing.frames;

import exceptions.AddContactException;
import controller.IControllerlist;
import exceptions.WordIsEmpty;
import logger.LoggerContainer;
import validation.Validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by work on 25.09.2016.
 */
public class ViewAddContact extends JFrame {

    private IControllerlist iControllerlist;
    private JList<String> listOfContacts;

    public ViewAddContact(IControllerlist iControllerlist, JList<String> listOfContacts) throws HeadlessException {

        this.iControllerlist = iControllerlist;
        this.listOfContacts = listOfContacts;
        setTitle("Add contact");
        setVisible(true);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(350,200));
        setMaximumSize(new Dimension(500, 350));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        init();

    }

    private void init() {

        GridLayout gridLayout = new GridLayout(7,1);
        setLayout(gridLayout);

        JLabel nameLabel = new JLabel("Contact name* ");
        JTextField nameText = new JTextField();
        nameText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    if (Validation.checkUniqueName(iControllerlist, nameText.getText())) {
                        JOptionPane.showMessageDialog(ViewAddContact.this, "Контакт с таким именем уже существует, введие другое имя", "ERROR", JOptionPane.CLOSED_OPTION);
                    } else if (!Validation.checkName(nameText.getText())){
                            JOptionPane.showMessageDialog(ViewAddContact.this, "Не правильно введено имя, введие другое имя", "ERROR", JOptionPane.CLOSED_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(ViewAddContact.this, "Имя уникальное", "Seccuss", JOptionPane.CLOSED_OPTION);
                    }

            }
        });


        JLabel phoneLabel = new JLabel("Contact phone*");
        JTextField phoneText = new JTextField();
        phoneText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!Validation.checkPhoneNumber(phoneText.getText())){
                    JOptionPane.showMessageDialog(ViewAddContact.this, "Не правильно введен номер телефона, введите заново. Форма ввода: (0хх)-хх-хх-ххх", "ERROR", JOptionPane.CLOSED_OPTION);
                } else {
                    JOptionPane.showMessageDialog(ViewAddContact.this, "Номер телефона введен правильно", "Seccuss", JOptionPane.CLOSED_OPTION);
                }
            }
        });

        JLabel emailLabel = new JLabel("Contact e-mail");
        JTextField emailText = new JTextField();
        emailText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Validation.checkEmail(emailText.getText())){
                    JOptionPane.showMessageDialog(ViewAddContact.this, "Не правильно введен email, введите заново. Форма ввода: [A-Za-z0-9_]{3,8}\\@(gmail|ukr|mail)\\.(com|net|ru)", "ERROR", JOptionPane.CLOSED_OPTION);
                } else {
                    JOptionPane.showMessageDialog(ViewAddContact.this, "Email введен правильно", "Seccuss", JOptionPane.CLOSED_OPTION);
                }
            }
        });


        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (nameText.getText().isEmpty() || phoneText.getText().isEmpty()){
                    JOptionPane.showConfirmDialog(ViewAddContact.this, "Contact name or Contact phone is Empty", "ERROR", JOptionPane.CLOSED_OPTION);
                }


                if (!nameText.getText().isEmpty() && !phoneText.getText().isEmpty()) {


                        if (Validation.checkUniqueName(iControllerlist, nameText.getText())) {
                            JOptionPane.showMessageDialog(ViewAddContact.this, "Контакт с таким именем уже существует, введие другое имя", "ERROR", JOptionPane.CLOSED_OPTION);
                        } else if (!Validation.checkName(nameText.getText())){
                            JOptionPane.showMessageDialog(ViewAddContact.this, "Не правильно введено имя, введие другое имя", "ERROR", JOptionPane.CLOSED_OPTION);
                        } else if (!Validation.checkPhoneNumber(phoneText.getText())){
                            JOptionPane.showMessageDialog(ViewAddContact.this, "Не правильно введен номер телефона, введите заново. Форма ввода: (0хх)-хх-хх-ххх", "ERROR", JOptionPane.CLOSED_OPTION);
                        } else if (!Validation.checkEmail(emailText.getText())){
                            JOptionPane.showMessageDialog(ViewAddContact.this, "Не правильно введен email, введите заново. Форма ввода: [A-Za-z0-9_]{3,8}\\@(gmail|ukr|mail)\\.(com|net|ru)", "ERROR", JOptionPane.CLOSED_OPTION);
                        } else {

                            addContact(nameText.getText(), phoneText.getText(), emailText.getText());

                        }

                }

            }
        });

        getContentPane().add(nameLabel);
        getContentPane().add(nameText);
        getContentPane().add(phoneLabel);
        getContentPane().add(phoneText);
        getContentPane().add(emailLabel);
        getContentPane().add(emailText);
        getContentPane().add(saveButton);

    }

    private void refreshInfo() {
        listOfContacts.setListData(iControllerlist.getNamesContactList().toArray(new String[iControllerlist.getNamesContactList().size()]));
    }

    private void addContact(String name, String phone, String email) {

        boolean contactRes = iControllerlist.addContact(name, phone, email);

        if (contactRes) {
            JOptionPane.showConfirmDialog(ViewAddContact.this, "Contact has been successfully added.", "SUCCESS", JOptionPane.ERROR_MESSAGE);
            LoggerContainer.logEvent("Contact - " + name + " " + phone + " " + email + " - has been successfully added to DB");
            refreshInfo();
            dispose();
        }

    }

}
