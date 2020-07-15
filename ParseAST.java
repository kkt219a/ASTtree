package midterm;

import java.io.IOException;
import java.util.ArrayList;

public class ParseAST {
    int started=0;
    Token token;          // current token from the input stream
    Lexer lexer;
    String funcId = "";
    Statement ee;

    public ParseAST(Lexer ts) throws IOException { // Open the Clite source program
        lexer = ts;				// as a token stream, and
        token = lexer.next(); // retrieve its first Token
    }
	private void getToken() throws IOException {
		token = lexer.next();
	}
	private String match (TokenType t) {
		String value = token.value();
		if (token.type().equals(t))
		token = lexer.next();
		else {
			System.out.println("not syntax");
			System.exit(0);
		}
		return value;
	}
	
	private void prgm() throws IOException {
		ee=stmt();
		if(token.type() == TokenType.Prgmeof)
			System.out.println("Syntax OK");
		else {
			System.out.println(".is required");
			System.exit(0);
		}
		System.out.println("Program(AST): ");
		ee.Print(1);
	}
	
	private Statement stmt() throws IOException {
		
		// ‘{’ <block> ‘}’
		if(token.type()==TokenType.LeftBrace) {
			getToken();
			Block s=null;
			s = block();
			if(token.type() == TokenType.RightBrace) {
				getToken();
			}
			else {
				System.out.println("}is required");
				System.exit(0);
			}
			return s;
		}

		
		//if '('<expr>')' then <stmt> [else <stmt>]
		if(token.value().contentEquals("if")) {
			Expression e=null;
			Statement s, s2=null, s3=null;
			getToken();
			if(token.type()==TokenType.LeftParen) {
				getToken();
				e = expr();
				if(token.type() == TokenType.RightParen) {
					getToken();
					if(token.value().contentEquals("then")) {
						getToken();
						s2 = stmt();
						if(token.value().contentEquals("else")) {
							getToken();
							s3 = stmt();
						}
					}
					else {
						System.out.println("then is required");
						System.exit(0);
					}
				}
				else {
					System.out.println(")is required");
					System.exit(0);
				}
			}
			else {
				System.out.println("(is required");
				System.exit(0);
			}
			if(s3!=null)
				s = new Conditional(e,s2,s3);
			else
				s= new Conditional(e,s2);
			return s;
		}
		
		
		//while '('<expr>')' <stmt> 
		else if(token.value().contentEquals("while")) {
			getToken();
			Statement s,s2=null;
			Expression e = null;
			if(token.type()==TokenType.LeftParen) {
				getToken();
				e = expr();
				if(token.type() == TokenType.RightParen) {
					getToken();
					s2 = stmt();
				}
				else {
					System.out.println(")is required");
					System.exit(0);
				}
			}
			else {
				System.out.println("(is required");
				System.exit(0);
			}
			s= new While(e,s2);
			return s;
		}
		
		//read id;
		if(token.value().contentEquals("read")) {
			Statement s;
			getToken();
			Identifier v = new Identifier(match(TokenType.Identifier));
			s = new Read(v);
			if(token.type() == TokenType.Semicolon)
				getToken();
			else {
				System.out.println(";is required");
				System.exit(0);
			}
			return s;
		}
		
		//print <expr>;
		if(token.value().contentEquals("print")) {
			Statement s;
			getToken();
			Expression e = expr();
			s= new Print(e);
			if(token.type() == TokenType.Semicolon) {
				getToken();
			}
			else {
				System.out.println(";is required");
				System.exit(0);
			}
			return s;
		}
		
		//<stmt> ->  let <decls> in <block> end
		if(token.value().contentEquals("let")) {
			Statement s;
			getToken();
			Declarations d = decls();
			if(!token.value().contentEquals("in")) {
				System.out.println("in is required");
				System.exit(0);
			}
			getToken();
			Block b = block();
			if(!token.value().contentEquals("end")) {
				System.out.println("end is required");
				System.exit(0);
			}
			getToken();
			s=new Let(d,b);
			return s;
		}
		
		//id=<expr>;
		else {
			Identifier v = new Identifier(match(TokenType.Identifier));
			Statement s;
			Expression e=null;
			if(token.type()==TokenType.Equal) {
				getToken();
				e= expr();
				if(token.type() == TokenType.Semicolon)
					getToken();
				else {
					System.out.println(";is required");
					System.exit(0);
				}
			}
			else {
				System.out.println("= is required");
				System.exit(0);
			}
			s = new Assignment(v,e);
			return s;
		}
	}
	
	//<expr> -> <bexp> {‘&’ <bexp> | ‘|’ <bexp>} | ‘!’<expr> | true | false
	private Expression expr() throws IOException {
		Expression e =null;
		
		if(token.value().equals("true")||token.value().equals("false")) {
			e=new BoolValue(token.value());
			getToken();
		}

		else if(token.type()==TokenType.Not) {
			getToken();
			e=expr();
		}
		
		else {
			e= bexp();
			while(token.type()==TokenType.And||token.type()==TokenType.Or) {
				Operator op = new Operator(match(token.type()));
				Expression e2 = bexp();
				e = new Binary(op, e, e2);
			}
		}
		return e;
	}
	
	//<aexp> [<relop> <aexp>] 
	private Expression bexp() throws IOException {
		Expression e = aexp();
		if(token.type()==TokenType.Equal||token.type()==TokenType.Not||token.type()==TokenType.Small||token.type()==TokenType.Large) {
			String b= match(token.type());
			b+=relop();
			Operator op = new Operator(b);
			Expression e2 = aexp();
			e = new Binary(op, e, e2);
		}
		return e;
	}
	
	//<relop>  '==' | '!=' | '<' | '>' | '<=' | '>='
	private String relop() throws IOException {
		String a="";
		if(token.type()==TokenType.Equal) { //다음 등호 맞다면 통과
			getToken();
			a+="=";
		}
		return a;
	}
	
	Expression aexp() throws IOException { // Aexp --> Term { AddOp Term }
		Expression e = term();
		while(token.type() == TokenType.Plus||token.type() == TokenType.Minus) {
			Operator op = new Operator(match(token.type()));
			Expression e2 = term();
			e = new Binary(op, e, e2);
		}
		return e;
	}
	
	Expression term () throws IOException {
		// Term --> Factor { MultiplyOp Factor }
		Expression e = factor();
		while (token.type() == TokenType.Multiply||token.type() == TokenType.Divide) {
			Operator op = new Operator(match(token.type()));
			Expression e2 = factor();
			e = new Binary(op, e, e2);
		}
		return e;
	}
	
	Expression factor() throws IOException {
		// Factor --> [ UnaryOp ] Primary
		if (token.type() == TokenType.Minus) {
			Operator op = new Operator(match(token.type()));
			Expression e1 = primary(); // Primary  identifer | number | ( Expr)
			return new Unary(op, e1);
		}
		else
			return primary();
	}
	
	private Expression primary () throws IOException {
		// Primary --> Identifier | (Exp) | Literal 
		Expression e = null;
		if (token.type().equals(TokenType.LeftParen)) {
			getToken();
			e = aexp();
			match(TokenType.RightParen);
		}
		else if (token.type().equals(TokenType.Identifier)) {
			Identifier v = new Identifier(match(TokenType.Identifier));
			e = v;
		}
		else if (token.type()==TokenType.IntLiteral) {
			e= new IntValue(Integer.parseInt(match(TokenType.IntLiteral)));
		}
		else {
			System.out.println("not Value");
			System.exit(0);
		}
		return e;
	}

	//<type> -> int | bool // type에 값을 넣고 반환
	private Type type() throws IOException {
		Type types=new Type();
		if(token.value().contentEquals("int")) {
			types=new Type("int");
			getToken();
		}
		else if(token.value().contentEquals("bool")){
			types=new Type("bool");
			getToken();
		}
		else {
			System.out.println("not type");
			System.exit(0);
		}
		return types;
	}
	
	// <decls> -> {<type> id;}
	private Declarations decls() throws IOException {
		Declarations decls;
		ArrayList<Declaration> declss = new ArrayList<Declaration>();
		while(!token.value().contentEquals("in")) {
			Declaration decl;
			Type t = type();
			Identifier id = new Identifier(token.value());
			getToken();
			if(token.type() == TokenType.Semicolon)
				getToken();
			else {
				System.out.println(";is required");
				System.exit(0);
			}
			decl = new Declaration(t,id);
			declss.add(decl);
		}
		decls=new Declarations(declss);
		return decls;
	}
	
	// <block> -> {<stmt>}
	private Block block() throws IOException {
		Block block;
		ArrayList<Statement> member=new ArrayList<Statement>();
		while(!token.value().contentEquals("end")&&!token.value().contentEquals("}")) {
			Statement state = stmt();
			member.add(state);
		}
		block = new Block(member);
		return block;
	}
	
    public static void main(String args[]) throws IOException {
		ParseAST parser;
		if (args.length == 0) {
            System.out.println("Begin parsing... ");
		    parser  = new ParseAST(new Lexer());
		    parser.prgm();
		}
    	else {
			System.out.println("Begin parsing... " + args[0]);
			parser  = new ParseAST(new Lexer(args[0]));
		    parser.prgm();
	    }
    } //main
}
