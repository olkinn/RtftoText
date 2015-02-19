package Main;
import Parser.Parser;
import java.io.IOException;
/**
 * Created by mikhail on 2/2/15.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser("example.rtf", "example.txt");
        parser.parse();
    }
}

