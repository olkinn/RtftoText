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
 * Created by mikhail on 2/25/15.
 */
public class ParserHTML {
    private static Logger logger = Logger.getLogger(ParserHTML.class.getName());

    private BufferedReader fis;
    private PrintWriter fos;
    private String string = "";
    private final String START = "<!DOCTYPE html><html><head><title>Page Title</title></head><body>";
    private final String END = "</body></html>";

    private HtmlTags htmlTags = HtmlTags.getInstance();

    public ParserHTML(String fileInput, String fileOutput) throws FileNotFoundException {
        fis = new BufferedReader(new InputStreamReader(new FileInputStream(fileInput)));
        fos = new PrintWriter((fileOutput));
    }

    public void parse() throws IOException {
        //logger.info("start logging");
        fos.print(START);

        getNextLine();
        string = string.substring(1);
        getNextLine();

        boolean isSkippingText = false;
        int skippingLevel = 2;
        Stack<Union> stack = new Stack<Union>();
        Union union = new Union();
        List<String> lastKeywords;

        do {
            char c = getFirstParanth();
            lastKeywords = union.updateUnion(StringWork.string1Paranth(string));

            if(!isSkippingText && !RtfIsText.rtfIsPlainText(union.getKeywordArray())) {
                isSkippingText = true;
                skippingLevel = stack.size();
            }

            if(stack.size() < skippingLevel) {
                isSkippingText = false;
            }

            if(!isSkippingText) {
                openTags(lastKeywords);
                fos.print(union.getLastText());
            }

            System.out.println("keywordArray:" + "\n" + union.getKeywordArray());
            System.out.println("isSkipping: " + isSkippingText);

            System.out.println("skipping level: " + skippingLevel);
            System.out.println("stack size: " + stack.size());
            System.out.println("stack: " + stack);

            System.out.println();

            //logger.info("Skipping: " + isSkipping + " skipping level: " + skippingLevel);
            //logger.info(stack.size() + "\n" + stack);

            if(c == '{') {
                stack.push(union);
                union = new Union();
            } else {
                if(!isSkippingText) {
                    closeTags(union.getKeywordArray());
                }

                if(stack.size() > 0) {
                    union = stack.pop();
                }
            }

            string = StringWork.string2Paranth(string);
        } while(getNextLine());

        fos.print(END);
        fos.flush();
        fos.close();

    }

    private void openTags(List<String> keywords) {
        for(String key : keywords) {
            if(htmlTags.contains(key)) {
                fos.print(htmlTags.getStartTag(key));
            }
        }
    }

    private void closeTags(List<String> keywords) {
        for(String key : keywords) {
            if(htmlTags.contains(key)) {
                fos.print(htmlTags.getEndTag(key));
            }
        }
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
