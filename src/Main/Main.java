package Main;
import Parser.Parser;
import Parser.ParserHTML;
import java.io.IOException;
/**
 * Created by mikhail on 2/2/15.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser1 = new Parser("example.rtf", "example.txt");
        parser1.parse();

        ParserHTML parser2 = new ParserHTML("example.rtf", "example.html");
        parser2.parse();
    }
}

