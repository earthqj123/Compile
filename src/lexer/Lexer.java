package lexer;
import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.*;
public class Lexer {
    private char peek=' ';
    private int line = 1;
    private Hashtable words=new Hashtable();
    private String str="";
    private void reserve(Word t){
        words.put(t.lexem,t);
    }
    public Lexer(){
        reserve(new Word(Tag.TRUE,"true"));
        reserve(new Word(Tag.FALSE,"false"));
    }
    public void TravserTable() {
       for(Iterator<String> iterator = words.keySet().iterator();iterator.hasNext();){
           String key=iterator.next();
           System.out.println(key);
       }
    }
    public String getStr() {
        return this.str;
    }
    public Token Scanner() throws IOException{
        for(;;peek = (char)System.in.read()){
            if(peek == ' '|| peek == '\t'){
                continue;
            }
            else if(peek == '\n'){
                line+=1;
            }
            else {break;}
        }
        /* Deal with comment */
        if(peek =='/') {
            peek=(char)System.in.read();
            if(peek=='/') {
                do{
                    peek=(char)System.in.read();
                }while(peek!='\n');
            }
            else if(peek=='*'){
                do{
                    peek=(char)System.in.read();
                    if(peek=='*'){
                        peek=(char)System.in.read();
                        if(peek=='/'){
                            break;
                        }
                        else
                            continue;
                    }
                }while(true);
            }
        }
        /* Deal with num */
        if(Character.isDigit(peek)){
            int v=0;
            do {
                v=10*v+Character.digit(peek,10);
                peek=(char)System.in.read();
            }while(Character.isDigit(peek));
            str+=String.valueOf(v);
            str+=" ";
            return new Num(v);
        }
        /* Deal with letter */
        if(Character.isLetter(peek)) {
            StringBuffer b=new StringBuffer();
            do{
                b.append(peek);
                peek=(char)System.in.read();
            }while(Character.isLetterOrDigit(peek));

            String s = b.toString();
            Word w=(Word) words.get(s);
            if(w!=null){
                return w;
            }
            w = new Word(Tag.ID,s);
            words.put(s,w);
            str+=s;
            str+=" ";
            return w;
        }
        Token t = new Token(peek);
        peek = ' ';
        return t;
    }
}
