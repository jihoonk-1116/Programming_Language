package cs316project;

class ClassSingleDef extends ClassDefList{

    ClassDef classDef;
    
    ClassSingleDef(ClassDef def){
        classDef = def;
    }
    
    void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <ClassSingleDef>");
        IO.displayln(indent1 + indent1.length() + " ClassDef");
		classDef.printParseTree(indent1);

	}
	
    
}