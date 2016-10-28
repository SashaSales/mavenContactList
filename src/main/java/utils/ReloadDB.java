package utils;

import appDB.AppDBImpl;
import appDB.IappDB;
import constants.Contstants;
import logger.LoggerContainer;

import javax.swing.*;
import java.io.*;

/**
 * Created by work on 25.10.2016.
 */
public class ReloadDB {


    public static IappDB Start() {

        IappDB iappDB = null;

        String pathDB = null;
        try {
            pathDB = IOutilsImpl.loadPathFromFile(Contstants.PATH_FOR_SAVEDB);
        } catch (IOException e) {
            LoggerContainer.logEvent("ReloadDB class proccess loadPathFromFile, IOException error: " + e.getMessage());
            e.printStackTrace();
        }

    // This module start then we used program earlier, bun something happened (File of DB is was removed or file is exist, but DB in file is null)
// Parameter, test path for DB is writed in File earlier

        int testDBForNull = 0;

        if (pathDB != null) {
            testDBForNull = parameterForTestDBForNull(pathDB);
        }

// Parameter, test data DB in File is exist or NO
        if (pathDB == null){
            choosePathForSaveDB();
            iappDB = new AppDBImpl();
        } else if (testDBForNull != -1 && new File(pathDB).exists()){
            String filePath = null;
            try {
                filePath = IOutilsImpl.loadPathFromFile(Contstants.PATH_FOR_SAVEDB);
            } catch (IOException e) {
                LoggerContainer.logEvent("ReloadDB class, loadPathFromFile, IOException error: " + e.getMessage());
                e.printStackTrace();
            }
            try {
                iappDB = (IappDB) IOutilsImpl.loadObjFromFile(filePath);
            } catch (IOException e) {
                LoggerContainer.logEvent("ReloadDB class, loadObjFromFile, IOException error: " + e.getMessage());
                e.printStackTrace();
            }
        } else if (testDBForNull == -1) {
            iappDB = new AppDBImpl();
        }

        return iappDB;
    }


    private static int parameterForTestDBForNull(String pathDB) {

        File file = new File(pathDB);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                LoggerContainer.logEvent("ReloadDB class parameterForTestDBForNull proccess createNewFile, IOException error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        BufferedInputStream bis =
                null;
        try {
            bis = new BufferedInputStream(
                    new FileInputStream(file));
        } catch (FileNotFoundException e) {
            LoggerContainer.logEvent("ReloadDB class parameterForTestDBForNull proccess FileInputStream, FileNotFoundException error: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            return bis.read();
        } catch (IOException e) {
            LoggerContainer.logEvent("ReloadDB class parameterForTestDBForNull proccess BufferedInputStream method read, IOException error: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    private static void choosePathForSaveDB() {

        JFileChooser fch = new JFileChooser();
        fch.setName("Выбрать путь сохранения базы данных");
        fch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


        switch (fch.showDialog(null, "Выбрать путь сохранения базы данных")){
            case JFileChooser.APPROVE_OPTION:

                File selectedDir = fch.getSelectedFile();
                String pathForDb = selectedDir.getAbsoluteFile() + "/myDB.txt";
                File fileDB = new File(pathForDb);
                try {
                    fileDB.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    IOutilsImpl.savePathToFile(Contstants.PATH_FOR_SAVEDB, fileDB);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case JFileChooser.CANCEL_OPTION:
                break;
            case JFileChooser.ERROR_OPTION:
                System.err.println("Error"); break;
        }


    }


}
