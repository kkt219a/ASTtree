package midterm;
// Lexer.java
// Lexical analyzer for Clite, as discusssed in Chapter 3
// Lexical analyzer modified for S

import java.io.*;

public class Lexer {

    private char ch = ' '; 
    private BufferedReader input;
    private String line = "";
    private int col = 0;
    private final String letters = "abcdefghijklmnopqrstuvwxyz"
        + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String digits = "0123456789";
    private final char eolnCh = '\n';

    public Lexer (String fileName) { // source filename
        try {
            input = new BufferedReader (new FileReader(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
        }
    }

	public Lexer ( ) { // from standard input 
            input = new BufferedReader (new InputStreamReader(System.in));
			System.out.print(">");
            try {
				System.out.print("    "); //���� ���ֱ�
                line = input.readLine( ); // ǥ���Է����� �ްڴ�
                System.out.println(line); // ������ �״�� ���
            } catch (IOException e) {
                System.err.println(e);
                System.exit(1);
            } // try
	}

	//�����ݷ� �Ⱥ����� ��� �Է¹޴°� ���� input.readLine �����غ���
    private char nextChar() { // Return next char
        if(col==line.length())
        	return ',';
        return line.charAt(col++); // �Է��� ������ col�� ��� �����ϴϱ� ��������ȴ�. if�� ��ġ��. 
    }
            

    public Token next( ) { // Return next token
        do {
        	//System.out.println(ch+" "+col+" "+line.length());
            if (isLetter(ch)) { // ���ܾ��
                String spelling = concat(letters + digits); // �� �̾������?
                return Token.mkIdentTok(spelling); // ��ūȭ ���Ѽ� ����� ����
            } else if (isDigit(ch)) { // ���ڸ�
                String number = concat(digits); //�� �̾������?
                return Token.mkIntLiteral(number); // ��ūȭ ���Ѽ� ���ڷ� ����
            } else
			switch (ch) {
			case '.':
				return Token.PrgmeofTok;
			case ',':
				return Token.SteofTok;
            case ' ': case '\t': case '\r': case eolnCh:
                ch = nextChar(); // �� ���� ���.���� �� �����ϱ� ����� ���߿� ���Ұ��� �����, �ٵ� �Ŀ��� ����־���
                break;
            
            case '/':  // divide 
                ch = nextChar(); // �����⸦ �ְ�
                return Token.divideTok; //����� ���� ���� ���� ��ȯ
            
            case '+': ch = nextChar();
                return Token.plusTok;
            case '-': ch = nextChar();
                return Token.minusTok;
            case '*': ch = nextChar();
                return Token.multiplyTok;
            case '(': ch = nextChar();
                return Token.leftParenTok;
            case ')': ch = nextChar();
                return Token.rightParenTok;
            case ';': ch = nextChar();
                return Token.semicolonTok;
            case '=': ch = nextChar();
            	return Token.EqualTok;
            case '!': ch = nextChar();
            	return Token.NotTok;
            case '<': ch = nextChar();
            	return Token.SmallTok;
            case '>': ch = nextChar();
            	return Token.LargeTok;
            case '|': ch = nextChar();
            	return Token.OrTok;
            case '&': ch = nextChar();
            	return Token.AndTok;
            case '{': ch = nextChar();
            	return Token.LeftBraceTok;
            case '}': ch = nextChar();
            	return Token.RightBraceTok;

            } // switch
          
        } while (true);
    } // next


    private boolean isLetter(char c) { //�� �Ҵ빮������ ����
        return (c>='a' && c<='z' || c>='A' && c<='Z');
    }
  
    private boolean isDigit(char c) { //�������� ����
        return (c>='0' && c<='9'); 
    }

    private String concat(String set) { // ���� �̰�? 
        String r = "";
        do {
            r += ch;
            ch = nextChar();
        } while (set.indexOf(ch) >= 0);
        return r;
    }

    public void error (String msg) { //�����޽��� ����� �ߴ�
        System.err.print(line);
        System.err.println("Error: column " + col + " " + msg);
        System.exit(1);
    }

    //3+4+5; �ϸ� �и��Ǽ� ����
    //3 +        4        + 5; �ص� �� �ǰ�
    // �ٵ� ���ý� ��ȴٴ� ��⸦ ����, ��ū �����⸸ �ϴϱ�
    // 3+ + * / - 4 ������ Ʋ���Ŵ� �ļ��� �Ǵ��ϰڴ�.
    // 333+444;�� �ߵ�
    static public void main ( String[] args ) {
        Lexer lexer;
		if (args.length == 0)
            lexer = new Lexer( );
		else //ǥ���Է����� �ްڴ�
            lexer = new Lexer(args[0]);

        Token tok = lexer.next( ); // �Է� ���� �Ŀ� ���� �տ������� �ѱ��� �޾ƿ���
        while (tok != Token.semicolonTok) { //�����ݷ� ���ö�����
            System.out.println(tok); //�Ѱ��Ѱ� �и��Ѱ� ����ϸ鼭, ���� �ȿ°Ŵ� ����� �ƿ� ����
             tok = lexer.next( ); // �������ڸ� ���´�. 
        } 
    } // main

	public String getLine() {
		// TODO Auto-generated method stub
		return line;
	}



}