package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by mikhail on 2/3/15.
 */

public class RtfIsText {
    private static String[] failAt = {"info", "pich", "*", "fonttbl", "datastore", "themedata", "picw", "stylesheet"};

    public static boolean rtfIsPlainText(List<String> array) {

        List<String> failAtArray = new ArrayList<String>(Arrays.asList(failAt));
        for (String str : array) {
            for (String s : failAt) {

                if (str.equals(s)) {
                    return false;
                }
            }
        }

        return true;
    }
}
