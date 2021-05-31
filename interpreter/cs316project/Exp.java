package cs316project;

import java.util.*;

abstract class Exp{
	
	abstract void printParseTree();
	abstract Val Eval(HashMap<String, ClassDefEntry> symbolTable);
	/*
	 The interpreter (evaluator) is to be implemented by Eval functions 
	 in suitable syntactic-category classes. 
	 Implement function-call states by HashMap<String, Val> 
	 where Val is the class of values in the domain of the project language; 
	 it maps parameter variable names to their values.
	*/
}

/*
import java.util.*;

public abstract class Interpreter extends Parser
{
	public static HashMap<String, Val> varState = new HashMap<String, Val>(); 
	              // program state, i.e., virtual memory for variables
		
	
	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );
		setLex();

		getToken();
		AssignmentList assignmentList = assignmentList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol where id expected");
		else if ( ! errorFound )
		{
			assignmentList.printParseTree("");       // print the parse tree in linearly indented form in argv[1] file
			assignmentList.M(varState);              // interpret the assignment list
			System.out.println(varState.toString()); // print the program state on the terminal
		}

		closeIO();
	}
}
*/
