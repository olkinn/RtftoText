package Util;
/**
 * Created by mikhail on 2/3/15.
 */
public class StringWork {
    
    public static String string1Paranth(String s){
        if (s!=null) {
            return (s.substring(0, Splitting.indexOfParanth(s)));
        } else {
            return null;
        }
    }

    public static String string2Paranth(String s){
        if (s!=null) {
            return (s.substring(Splitting.indexOfParanth(s) + 1));
        } else {
            return null;
        }
    }

    public static String string1Space(String s){
        s = s +' ';
        if (s!=null && s.indexOf(' ') != -1){
            return (s.substring(0, s.indexOf(' ')));
        } else {
            return null;
        }
    }

    public static String string2Space(String s){
        s = s + ' ';
        return (s.substring(s.indexOf(' ') + 1));
    }

}
