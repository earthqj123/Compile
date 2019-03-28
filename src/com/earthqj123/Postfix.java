package com.earthqj123;
import java.io.*;
import com.earthqj123.Parser;

public class Postfix {
    public static void main(String[] args) throws IOException{
        Parser parse=new Parser();
        parse.expr();
        System.out.write('\n');
    }
}
