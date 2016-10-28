package utils;

import model.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by work on 11.09.2016.
 */
public interface IOutils {

    void writeInto(String path, String content) throws FileNotFoundException;

    void appendTo(String path, String content) throws FileNotFoundException;

    // use File
    // create new file
    boolean createFile(String path);

    boolean deleteFile(String path) throws FileNotFoundException;

    boolean copyFile(String src, String dest);

    List<String> grep(String src, String keyWord);

    //shallow copy
    // use standard clone mechanism in java
    Object clone(Object obj);

    byte[] toByteArr(Object obj);

    Object fromByteArr(byte[] arr);

    void saveObjToFile(Contact contact, String filePath);
    Object loadObjFromFile(String filePath) throws IOException;
    String loadPathFromFile();
    void downloadFile(String url, String localPathName);

    // implement recursive search (find in curr directory, then find in child dir, then repeat...)
    List<String> find(File dir, String keyWord);

    void saveLogToFile(String logs);

}
