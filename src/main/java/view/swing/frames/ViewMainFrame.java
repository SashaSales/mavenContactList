package view.swing.frames;

import controller.IControllerlist;
import run.StartApp;
import view.swing.panelsMainFrame.PanelContactList;
import view.swing.panelsMainFrame.PanelFunctionButtons;
import view.swing.panelsMainFrame.PanelMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Created by work on 17.09.2016.
 */
public class ViewMainFrame extends JFrame {

    private IControllerlist iControllerlist;
    private JList<String> listOfContacts;

    public ViewMainFrame(IControllerlist iControllerlist) throws HeadlessException{

        this.iControllerlist = iControllerlist;
        setMinimumSize(new Dimension(250,200));
 //       setMaximumSize(new Dimension(500, 350));
        setTitle("Contact List");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        init();
        setVisible(true);

    }

    private void init() {

        BorderLayout bl = new BorderLayout(1,1);
        setLayout(bl);

        Color color = StartApp.themeColor;
        JPanel topPanel = new JPanel(new GridLayout(2,1));
        topPanel.setVisible(true);

        //create panel of contact List
        PanelContactList panelContactList = new PanelContactList();

        int sizeContactNames = iControllerlist.getNamesContactList().size();

        listOfContacts = panelContactList.createPanelContactList(iControllerlist.getNamesContactList().toArray(
                new String[sizeContactNames]), color);

        JScrollPane jScrollPane = new JScrollPane(listOfContacts);
        add(jScrollPane, BorderLayout.CENTER);


        //create main menu
        PanelMenu menu = new PanelMenu();
        JMenuBar menuPanel = menu.createMenuPanel(iControllerlist, listOfContacts);

        //create search Field
        JTextField searchField = new JTextField();
        searchField.setToolTipText("Введите слово для поиска");
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String str = searchField.getText();

                if (str.isEmpty()) {
                    str = "A";
                } else if (str.substring(0).toLowerCase() == str.substring(0)){
                    str = str.substring(0).toUpperCase() + str.substring(1);
                }

                SortedSet<String> subSet = iControllerlist.getNamesContactList().tailSet(str);
                String [] names = subSet.toArray(new String[subSet.size()]);
                listOfContacts.setListData(names);


            }
        });


        ///////////////////////////////////////////

        topPanel.add(menuPanel);
        topPanel.add(searchField);
        add(topPanel, BorderLayout.NORTH);

        //create panel of functional Buttons (Add, Delete of Contact)

        PanelFunctionButtons functionButtons = new PanelFunctionButtons();
        JPanel functionalButtons = functionButtons.createPanelFunctionButtons(this, iControllerlist, listOfContacts);
        add(functionalButtons, BorderLayout.SOUTH);

    }

    public void refreshInfo() {
        listOfContacts.setListData(iControllerlist.getNamesContactList().toArray(new String[iControllerlist.getNamesContactList().size()]));
    }

}
