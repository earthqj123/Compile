package lexer;

import java.io.IOException;
import java.util.Scanner;

public class LexerMain {
    public static void main(String args[]) throws IOException{
        Lexer lexer=new Lexer();
        Tag tag;
        Token t=new Token(0);
        while (true){
            t=lexer.Scanner();
            System.out.println(lexer.getStr());
        }

    }
}
