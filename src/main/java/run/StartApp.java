package run;

import appDB.AppDBImpl;
import constants.Contstants;
import appDB.IappDB;
import controller.ControllerListImpl;
import logger.LogerControllerProxy;
import controller.IControllerlist;
import logger.LoggerContainer;
import utils.IOutilsImpl;
import utils.ReloadDB;
import view.swing.frames.ViewMainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by work on 17.09.2016.
 */
public class StartApp {

    public static Color themeColor = Color.lightGray;

    public static void main(String[] args) {

        IappDB iappDB = ReloadDB.Start();

        IControllerlist iControllerlist = new LogerControllerProxy(new ControllerListImpl(iappDB));
        new ViewMainFrame(iControllerlist);



    }


}
