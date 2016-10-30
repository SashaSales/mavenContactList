package testController;

import appDB.AppDBImpl;
import appDB.IappDB;
import controller.ControllerListImpl;
import controller.IControllerlist;
import logger.LogerControllerProxy;

import java.io.Serializable;

/**
 * Created by work on 16.10.2016.
 */
public class InitData implements Serializable{

    public static IControllerlist createTestData()  {

        IappDB iappDBTest = new AppDBImpl();

        IControllerlist iControllerlist = new LogerControllerProxy(new ControllerListImpl(iappDBTest));

        iControllerlist.addContact("Sergey", "0677777777", "Sergey@gmail.com");
        iControllerlist.addContact("Alex", "0737373737", "Alex@gmail.com");
        iControllerlist.addContact("Ivan", "0733333333", "Ivan@gmail.com");
        iControllerlist.addContact("Elena", "0505050505", "Elena@gmail.com");
        iControllerlist.addContact("Anya", "0989898989", "Anya@gmail.com");
        iControllerlist.addContact("Yulya", "0636363636", "Yulya@gmail.com");
        iControllerlist.addContact("Senya", "0939393939", "Senya@gmail.com");
        iControllerlist.addContact("Serofim", "0939111939", "Serofim@gmail.com");
        iControllerlist.addContact("Lyuda", "0677777717", "Lyuda@gmail.com");
        iControllerlist.addContact("Anna", "0677771777", "Anna@gmail.com");
        iControllerlist.addContact("Franciza", "0671777777", "Franciza@gmail.com");
        iControllerlist.addContact("Gerakl", "0677727777", "Gerakl@gmail.com");
        iControllerlist.addContact("Ksena", "0677773777", "Ksena@gmail.com");
        iControllerlist.addContact("Richard", "0677747777", "Richard@gmail.com");
        iControllerlist.addContact("Samson", "0677747177", "Samson@gmail.com");

        return iControllerlist;
    }

}
