package cs316project;

import java.util.HashMap;
import java.util.LinkedList;

abstract class ExpList{
	abstract void printParseTree();
	abstract void M(HashMap<String, ClassDefEntry> symbolTable);
	abstract Val Eval(HashMap<String, ClassDefEntry> symbolTable, LinkedList<String> fieldlist, Val object);
}
