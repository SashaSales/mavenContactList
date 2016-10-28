package utils;

import constants.Contstants;
import logger.LoggerContainer;
import model.Contact;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by work on 11.09.2016.
 */
public class IOutilsImpl implements Serializable{


    public static boolean saveObjToFile(Object obj, String pathDB) throws IOException {

        boolean res = false;

        //String pathDB = IOutilsImpl.loadPathFromFile(Contstants.PATH_FOR_SAVEDB);

        if (obj != null) {
            File fileDB = new File(pathDB);
            if (!fileDB.exists()) {
                fileDB.createNewFile();
            }

            try (FileOutputStream fos = new FileOutputStream(fileDB);
                     ObjectOutputStream outputStream = new ObjectOutputStream(fos)) {

                    outputStream.writeObject(obj);
                    outputStream.flush();

            } catch (FileNotFoundException e) {
                    e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
            }
            res = true;

        }
        return res;
    }


    public static  void savePathToFile(String pathFile, File pathForDB) throws IOException {

        File file = new File(pathFile);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                LoggerContainer.logEvent("can't create File for save path for DB, error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(pathFile))) {

            printWriter.write(pathForDB.getAbsolutePath());
            printWriter.flush();

        } catch (IOException e) {
            LoggerContainer.logEvent("can't save path for DB to File, error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static String loadPathFromFile(String filePath) throws IOException {
        String pathDB = null;
        File file = new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){

            pathDB = br.readLine();
        } catch (IOException e) {
            LoggerContainer.logEvent("Can not load Path DB from File" + e.getMessage());
            e.printStackTrace();
        }

        return pathDB;
    }

    public static Object loadObjFromFile(String filePath) throws IOException {

        //String filePath = IOutilsImpl.loadPathFromFile(Contstants.PATH_FOR_SAVEDB);

        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream input =
                     new ObjectInputStream(new BufferedInputStream(
                             fis))) {

            return input.readObject();
        } catch (Exception e) {
            LoggerContainer.logEvent("Data was no load from File, error:" + e.getMessage());
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }

    }


    public static void saveLogToFile(String logs) throws IOException {

        File file = new File (Contstants.PATH_FOR_LOGER);
        IOutils.class.getResource(Contstants.PATH_FOR_LOGER);

        if (!file.exists()){
            file.createNewFile();
        }

        try (PrintWriter printWrite = new PrintWriter(new FileWriter(Contstants.PATH_FOR_LOGER, true), true)) {
            printWrite.println(logs);

        } catch (IOException e) {
            LoggerContainer.logEvent("Data was no save to File, error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
