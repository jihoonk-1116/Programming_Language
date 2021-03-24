package cs316project;

/**
Professor Keitaro Yukawa
CSCI 316 - principle of programming language.
Chihoon Kim (Student #23694627)

This class is a lexical analyzer for the tokens defined by the grammar:

<letter> → a | b | ... | z | A | B | ... | Z
<digit> → 0 | 1 | ... | 9
<id> → ⟨letter⟩ {⟨letter⟩ | ⟨digit⟩} 
<keyword_class> → 'class'
<keyword_if> → 'if'
<keyword_null> → 'null'
<keyword_this> → 'this'
<int> → [+|−] {⟨digit⟩}+
<float> → [+|−] ( {⟨digit⟩}+ "." {⟨digit⟩}  |  "." {⟨digit⟩}+  )
<floatE> → (⟨float⟩ | ⟨int⟩) (e|E) [+|−] {⟨digit⟩}+
<add> → +
<sub> → −
<mul> → *
<div> → /
<or> → "|"
<and> → &
<not> → !
<lt> → "<"
<le> → "<="
<gt> → ">"
<ge> → ">="
<eq> → "="
<dot operator> → "."
<LParen> → "("
<RParen> → ")"
<LBrace> → "{"
<RBrace> → "}"
<colon> → ":"

This class implements a DFA that will accept the above tokens.

The DFA states are represented by the Enum type "State".
The DFA has the following 26 final states represented by enum-type literals:

state     token accepted

Id        identifiers
keyword_this	this
keyword_null	null
keyword_class	class
keyword_if		if
Int       integers
Float     floats without exponentiation part
FloatE    floats with exponentiation part
Plus      +
Minus     -
Times     *
Div       /
LParen    (
RParen    )
LBrace	  {
RBrace 	  }
Lt		  <
Le        =<
Gt 		  >
Ge		  >=
Eq		  =
Not 	  !
Or		  |
Dotop	  .
And		  &
Colon	  :


The DFA also uses the following 4 non-final states:

state      string recognized

Start      the empty string
Period     float parts ending with "."
E          float parts ending with E or e
EPlusMinus float parts ending with + or - in exponentiation part

The function "driver" operates the DFA. 
The function "nextState" returns the next state given the current state and the input character.

To recognize a different token set, modify the following:

  enum type "State" and function "isFinal"
  function "nextState" 

The functions "driver", "getToken" remain the same.

**/


public abstract class LexAnalyzer extends IO
{
	public enum State 
       	{ 
	  // non-final states     ordinal number

		Start,             // 0
		Period,            // 1
		E,                 // 2
		EPlusMinus,        // 3

	  // final states

		Id,                // 4
		Int,               // 5
		Float,             // 6
		FloatE,            // 7
		Plus,              // 8
		Minus,             // 9
		Times,             // 10
		Div,               // 11
		LParen,            // 12
		RParen,            // 13
		
		//Add 4 more final states to detect class, if ,null, this 
		Keyword_class,	   // 14
		Keyword_if,		   // 15
		Keyword_null,	   // 16
		Keyword_this,	   // 17
		
		//More final states to accept
		Colon,				//18
		Not,				//19
		And,				//20	
		Or,					//21
		Lt,					//22
		Gt,					//23
		Ge,					//24
		Le,					//25
		Eq,					//26
		LBrace,				//27
		RBrace,				//28
		DotOp,				//29
		

		UNDEF;

		private boolean isFinal()
		{
			return ( this.compareTo(State.Id) >= 0 );  
		}	
	}

	// By enumerating the non-final states first and then the final states,
	// test for a final state can be done by testing if the state's ordinal number
	// is greater than or equal to that of Id.

	// The following variables of "IO" class are used:

	//static int a; //the current input character
	//static char c; //used to convert the variable "a" to the char type whenever necessary

	public static String t; // holds an extracted token
	public static State state; // the current state of the FA

	private static int driver()

	// This is the driver of the FA. 
	// If a valid token is found, assigns it to "t" and returns 1.
	// If an invalid token is found, assigns it to "t" and returns 0.
	// If end-of-stream is reached without finding any non-whitespace character,
	// assigns the empty string "" to "t" and returns -1.

	{
		State nextSt; // the next state of the FA

		t = "";
		state = State.Start;

		if ( Character.isWhitespace((char) a) )
			a = getChar(); // get the next non-whitespace character
		if ( a == -1 ) // end-of-stream is reached
			return -1;

		while ( a != -1 ) // do the body if "a" is not end-of-stream
		{
			c = (char) a;
			nextSt = nextState( state, c );
			if ( nextSt == State.UNDEF ) // The FA will halt.
			{
				
				if ( state.isFinal()) {
					return 1; // valid token extract
				}
				else // "c" is an unexpected character
				{
					t = t+c;
					a = getNextChar();
					return 0; // invalid token found
				}
			}
			else // The FA will go on.
			{
				state = nextSt;
				t = t+c;
				a = getNextChar();
			}
		}

		// end-of-stream is reached while a token is being extracted

		if ( state.isFinal() )
			return 1; // valid token extracted
		else
			return 0; // invalid token found
	} // end driver

	public static void getToken()

	// Extract the next token using the driver of the FA.
	// If an invalid token is found, issue an error message.

	{
		int i = driver();
		if ( i == 0 )
			displayln(t + " : Lexical Error, invalid token");
	}

	private static State nextState(State s, char c)

	// Returns the next state of the FA given the current state and input char;
	// if the next state is undefined, UNDEF is returned.

	{
		switch( state )
		{
		case Start:
			if ( Character.isLetter(c) )
				return State.Id;
			else if ( Character.isDigit(c) )
				return State.Int;
			else if ( c == '+' )
				return State.Plus;
			else if ( c == '-' )
				return State.Minus;
			else if ( c == '*' )
				return State.Times;
			else if ( c == '/' )
				return State.Div;
			else if ( c == 'e' || c == 'E')
				return State.Id;
			else if ( c == '(' )
				return State.LParen;
			else if ( c == ')' )
				return State.RParen;
			else if ( c == '.' )
				return State.DotOp;
			else if (c == '{')			//Add initial state to cover '{' left brace
				return State.LBrace;
			else if (c == '}')			//Add initial state to cover '}' right brace
				return State.RBrace;
			else if (c == '<')			//Add initial state to cover '<' less than
				return State.Lt;
			else if (c == '>')			//Add initial state to cover '>' greater than
				return State.Gt;
			else if (c == '=')			//Add initial state to cover '=' equal to
				return State.Eq;
			else if (c == ':')			//Add initial state to cover ':' colon 
				return State.Colon;
			else if (c == '!')			//Add initial state to cover '!' not
				return State.Not;
			else if (c == '&')			//Add initial state to cover '&' and
				return State.And;
			else if (c == '|')			//Add initial state to cover '|' or
				return State.Or;
			else
				return State.UNDEF;
		case Keyword_this: 							//Keyword 'this' from Id state
			if(c =='i')								//Check the next character is the part of 'this' 
				return State.Keyword_this;
			else if(c == 's' && t.length() == 3)	//Check if the 4th character is 's', t includes previous characters 'thi'.
				return State.Keyword_this;
			else if(Character.isLetterOrDigit(c))	//Check if another character, such as digit or letter, is included 
				return State.Id;					//Then, its state is changed to Id
			else 
				return State.UNDEF;					//If c is space -> state.undef
		case Keyword_class:							//Keyword 'class' from Id state
			if(c =='a')								//Check the next character is the 'a' token of 'class' 
				return State.Keyword_class;
			else if(c == 's' && t.length() == 3)	//Check if the 4th character is 's', t includes previous characters 'cla'.
				return State.Keyword_class;
			else if(c == 's' && t.length() == 4)	//Check if the 4th character is 's', t includes previous characters 'clas'.
				return State.Keyword_class;
			else if(Character.isLetterOrDigit(c))	//Check if another character, such as digit or letter, is included 
				return State.Id;					//Then, its state is changed to Id
			else 
				return State.UNDEF;					//If c is space -> state.undef
		case Keyword_null:							//Keyword 'null' from Id state
			if(c =='l')								//Check the next character is the 'l' token of 'null' 
				return State.Keyword_null;
			else if(c == 'l' && t.length() == 3)	//Check if the 4th character is 's', t includes previous characters 'nul'.
				return State.Keyword_null;
			else if(Character.isLetterOrDigit(c))	//Check if another character, such as digit or letter, is included 
				return State.Id;					//Then, its state is changed to Id
			else 
				return State.UNDEF;					//If c is space -> state.undef
		case Keyword_if:							//Keyword 'if' from Id state
			if(Character.isLetterOrDigit(c))		//Check if another character, such as digit or letter, is included 	
				return State.Id;					//Then, its state is changed to Id
			else 
				return State.UNDEF;					//If c is space -> state.undef
		case Lt:									//Less than state
			if(Character.isDigit(c))
				return State.Int;
			else if(c == '=')						//If next token is '=', change its state to Le
				return State.Le;
			else
				return State.UNDEF;
		case Gt:									//Greater than state
			if(Character.isDigit(c))
				return State.Int;
			else if(c == '=')						//If next token is '=', change its state to Ge
				return State.Ge;
			else
				return State.UNDEF;
		case Plus:									//Plus  state
			if(Character.isDigit(c))
				return State.Int;
			else if(c=='e' || c == 'E')
				return State.Float;
			else if(c=='.')
				return State.Period;
			else
				return State.UNDEF;
		case Minus:									//Minus state
			if(Character.isDigit(c))
				return State.Int;
			else if(c=='e' || c == 'E')
				return State.Float;
			else if(c=='.')
				return State.Period;
			else
				return State.UNDEF;
		case Id:									//Id
			if(c == 'h' && t.charAt(0) == 't')		//if start with 't' and c is 'h', change state to keyword_this
				return State.Keyword_this;			
			else if(c == 'f' && t.charAt(0) == 'i') //if start with 'i' and c is 'f', change state to keyword_if
				return State.Keyword_if;
			else if(c == 'u' && t.charAt(0) == 'n')	//if start with 'n' and c is 'u', change state to keyword_null
				return State.Keyword_null;
			else if(c == 'l' && t.charAt(0) == 'c')	//if start with 'c' and c is 'l', change state to keyword_class
				return State.Keyword_class;
			else if ( Character.isLetterOrDigit(c) )
				return State.Id;
			else
				return State.UNDEF;
		case DotOp:
			if(Character.isDigit(c))	//if '.' is mixed with digit, change its state to float
				return State.Float;
			else 
				return State.UNDEF;
		case Int:
			if ( Character.isDigit(c) )
				return State.Int;
			else if ( c == '.' )
				return State.Float;
			else if (c =='e' || c== 'E')
				return State.E;
			else
				return State.UNDEF;
		case Period:
			if ( Character.isDigit(c) )
				return State.Float;
			else if(c == 'e'|| c=='E')
				return State.E;
			else
				return State.UNDEF;
		case Float:
			if ( Character.isDigit(c) )
				return State.Float;
			else if ( c == 'e' || c == 'E' )
				return State.E;
			else
				return State.UNDEF;
		case E:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else if ( c == '+' || c == '-' )
				return State.EPlusMinus;
			else
				return State.UNDEF;
		case EPlusMinus:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		case FloatE:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		default:
			return State.UNDEF;
		}
	} // end nextState

	public static void main(String argv[])

	{		
		// argv[0]: input file containing source code using tokens defined above
		// argv[1]: output file displaying a list of the tokens 

		setIO( argv[0], argv[1] );
		
		int i;

		while ( a != -1 ) // while "a" is not end-of-stream
		{
			i = driver(); // extract the next token
			if ( i == 1 )
				displayln( t+"   : "+state.toString() );
			else if ( i == 0 )
				displayln( t+" : Lexical Error, invalid token");
		} 

		closeIO();
	}
}