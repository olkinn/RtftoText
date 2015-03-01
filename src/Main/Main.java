package Main;
import Parser.ParserHTML;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by mikhail on 2/2/15.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Boolean txt = false;
        Boolean html = false;
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Convert to what format? \n 1 - to txt \n 2 - to html\n 3 - to txt and to html");
       do {

           choice = scanner.nextInt();

           switch (choice){
               case 1: {
                   txt = true;
                   html = false;
                   break;
               }

               case 2: {
                   txt = false;
                   html = true;
                   break;
               }
               case 3: {
                   txt = true;
                   html = true;
                   break;
               }
               default:{
                   System.out.println("Please make your choice: ");
                   break;
               }
           }
        } while (0 >= choice || choice > 3);

        ParserHTML parser2 = new ParserHTML("example.rtf", "example.txt", txt, "example.html", html);
        parser2.parse();
    }
}
