package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mikhail on 2/2/15.
 */
public class Splitting {

    public static List<String> splitByBackslash(String s) {
        String[] delimeters = s.split("\\\\");
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(delimeters));

        if(arrayList.size() < 2) {
            return new ArrayList<String>();
        } else {
            return new ArrayList<String>(arrayList.subList(1, arrayList.size()));
        }
    }


    public static int indexOfParanth(String string){
        int a = string.indexOf("{");
        int b = string.indexOf("}");

        if (a == -1 && b == -1) {
            return -1;
        } else if(a == -1 && b != -1) {
            return b;
        } else if(a != -1 && b == -1) {
            return a;
        } else if(a < b) {
            return a;
        } else {
            return b;
        }
    }
}
