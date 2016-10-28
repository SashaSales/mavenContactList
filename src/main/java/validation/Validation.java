
package validation;

import constants.OperatorModifiers;
import exceptions.WordIsEmpty;
import controller.IControllerlist;

import java.io.IOException;
import java.io.Serializable;
import java.util.NavigableSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private IControllerlist iControllerlist;

    public Validation(IControllerlist iControllerlist) {
        this.iControllerlist = iControllerlist;
    }


    public static boolean checkUniqueName(IControllerlist iControllerlist, String name) {

        return iControllerlist.getNamesContactList().contains(name);
    }

    public static OperatorModifiers Operator(String phoneNumber) {

        OperatorModifiers operatorModifiers = OperatorModifiers.OPERATOR_NONE;

        String operatorStr = phoneNumber.substring(0, 3);

        if (operatorStr.equals("063") ||
                operatorStr.equals("093") ||
                operatorStr.equals("073")) {
            operatorModifiers = OperatorModifiers.OPERATOR_LIFE;
        } else if (operatorStr.equals("097") ||
                operatorStr.equals("067") ||
                operatorStr.equals("098")) {
            operatorModifiers = OperatorModifiers.OPERATOR_KIEVSTAR;
        } else if (operatorStr.equals("050") ||
                operatorStr.equals("095") ||
                operatorStr.equals("099")) {
            operatorModifiers = OperatorModifiers.OPERATOR_MTS;
        }

        return operatorModifiers;
    }

    public static boolean checkName(String name) {
        Pattern p = Pattern.compile("^[A-Za-z0-9_]{3,15}$");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public static boolean checkPhoneNumber(String phoneNumber) {

        Pattern p = Pattern.compile("^[0-9]{10}$");
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    public static boolean checkEmail(String email) {
        Pattern p = Pattern.compile("^[A-Za-z0-9_]{3,8}\\@(gmail|ukr|mail)\\.(com|net|ru)$");
        Matcher m = p.matcher(email);
        return m.matches();
    }


}

