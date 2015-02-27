package Parser;

import Util.RtfIsText;
import Util.Splitting;
import Util.StringWork;
import Util.Union;

import java.io.*;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * Created by mikhail on 2/16/15.
 */
public class Parser {
    private static Logger logger = Logger.getLogger(Parser.class.getName());

    private BufferedReader fis;
    private PrintWriter fos;
    private String string = "";

    public Parser(String fileInput, String fileOutput) throws FileNotFoundException {
        fis = new BufferedReader(new InputStreamReader(new FileInputStream(fileInput)));
        fos = new PrintWriter((fileOutput));
    }

    public void parse() throws IOException {
        //logger.info("start logging");

        getNextLine();
        string = string.substring(1);
        getNextLine();

        boolean isSkipping = false;
        int skippingLevel = 10000000;
        Stack<Union> stack = new Stack<Union>();
        Union union = new Union();

        do {
            char c = getFirstParanth();

            union.updateUnion(StringWork.string1Paranth(string));

            if(!isSkipping && !RtfIsText.rtfIsPlainText(union.getKeywordArray())) {
                isSkipping = true;
                skippingLevel = stack.size();
            }

            if(stack.size() < skippingLevel) {
                isSkipping = false;
            }

            if(!isSkipping) {
                fos.print(union.getLastText());
            }

            //logger.info("Skipping: " + isSkipping + " skipping level: " + skippingLevel);
            //logger.info(stack.size() + "\n" + stack);

            if(c == '{') {
                stack.push(union);
                union = new Union();
            } else if(stack.size() > 0) {
                union = stack.pop();
            }

            string = StringWork.string2Paranth(string);
        } while(getNextLine());

        fos.flush();
        fos.close();

    }

    private boolean getNextLine() throws IOException {
        String s;
        while(Splitting.indexOfParanth(string) == -1 && (s = fis.readLine()) != null) {
            string += s + " ";
        }

        if(string == null || string.trim().equals("")) {
            return false;
        }

        return true;
    }

    private char getFirstParanth() {
        int index = Splitting.indexOfParanth(string);
        if(index < 0) {
            return 'e';
        }

        return string.charAt(index);
    }

}
