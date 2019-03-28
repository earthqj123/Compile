package lexer;

public class Word extends Token{
    public final String lexem;
    public Word(int t,String s){
        super(t);
        lexem=new String(s);
    }
}
