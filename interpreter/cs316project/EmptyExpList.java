package cs316project;

import java.util.HashMap;
import java.util.LinkedList;

class EmptyExpList extends ExpList{
    Exp exp;
    
    EmptyExpList(){
        exp = null;
    }
    void printParseTree(){
        IO.displayln(" <EmptyExp>");
    }
    void M(HashMap<String, ClassDefEntry> symbolTable) {
    	
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable, LinkedList<String> fieldlist, Val obj) {
    	return null;
    }
}