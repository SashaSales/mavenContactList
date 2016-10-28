package logger;

import utils.IOutilsImpl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by work on 11.09.2016.
 */
public class LoggerContainer {

    private static List<String> logs;
    private static Calendar cal;
    private static DateFormat dateFormat;
    private static String dateTime;

    static {

        logs = new ArrayList<>();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        cal = Calendar.getInstance();

    }

    public static void logEvent(String event) {

        dateTime = dateFormat.format(cal.getTime());
        logs.add(dateTime + " " + event);
        try {
            IOutilsImpl.saveLogToFile(dateTime + " " + event);
        } catch (IOException e) {
            // todo use logger
            e.printStackTrace();
        }

    }

    public static void showAllLogs() {
        if (logs.size() > 0) {

            //logs.forEach(System.out::println);
            for (String str : logs) {
                System.out.println(str);
            }
        }
    }

    public static List<String> getLogs() {
        return logs;
    }
}

