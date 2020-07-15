package midterm;
// Token.java
// Tokens for Expression Grammar

//import java.lang.Enum;
enum TokenType {
     Eof, Semicolon, 
     LeftParen, RightParen, 
     Plus, Minus, Multiply,
     Divide, Identifier, IntLiteral,Steof,
     And,Or,Not,Equal,Small,Large,
     LeftBrace,RightBrace,Prgmeof
     
}

public class Token {

	//� ��ū���� �ִ��� ���Ͱ��� �߰��Ѵ�. ���� enum���� ���� ������ �ɵ�.
    public static final Token eofTok = new Token(TokenType.Eof, "<<EOF>>");
    public static final Token leftParenTok = new Token(TokenType.LeftParen, "(");
    public static final Token rightParenTok = new Token(TokenType.RightParen, ")");
	public static final Token semicolonTok = new Token(TokenType.Semicolon, ";");
    public static final Token plusTok = new Token(TokenType.Plus, "+");
    public static final Token minusTok = new Token(TokenType.Minus, "-");
    public static final Token multiplyTok = new Token(TokenType.Multiply, "*");
    public static final Token divideTok = new Token(TokenType.Divide, "/");
    public static final Token PrgmeofTok = new Token(TokenType.Prgmeof, "."); // �����ݷ��� Ȯ���� ���� ��üǰ
    public static final Token AndTok = new Token(TokenType.And, "&");
    public static final Token OrTok = new Token(TokenType.Or, "|");
    public static final Token NotTok = new Token(TokenType.Not, "!");
    public static final Token EqualTok = new Token(TokenType.Equal, "=");
    public static final Token SmallTok = new Token(TokenType.Small, "<");
    public static final Token LargeTok = new Token(TokenType.Large, ">");
    public static final Token LeftBraceTok = new Token(TokenType.LeftBrace, "{");
    public static final Token RightBraceTok = new Token(TokenType.RightBrace, "}");
    public static final Token SteofTok = new Token(TokenType.Steof, ",");
    
    
    private TokenType type;
    private String value = "";

    private Token (TokenType t, String v) {
        type = t;
        value = v;
    }

    public TokenType type( ) { return type; }

    public String value( ) { return value; }

    public static Token mkIdentTok (String name) {
        return new Token(TokenType.Identifier, name);
    }

    public static Token mkIntLiteral (String name) {
        return new Token(TokenType.IntLiteral, name);
    }

    public String toString ( ) {
        return value;
    } // toString

    public static void main (String[] args) {
        System.out.println(eofTok);
        System.out.println(divideTok);
        System.out.println(multiplyTok);
        System.out.println(rightParenTok);
    }
} // Token