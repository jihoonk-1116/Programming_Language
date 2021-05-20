package cs316project;

class ClassMultiDef extends ClassDefList{

    ClassDef classDef;
    ClassDefList classMultiDef;

    ClassMultiDef(ClassDef def, ClassDefList multidef){
        classDef = def;
        classMultiDef = multidef;
    }
    
    void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		IO.displayln(indent + indent.length() + " <ClassMultiDef>");
        IO.displayln(indent1 + indent1.length() + " ClassDef");
		classDef.printParseTree(indent1);
        classMultiDef.printParseTree(indent1);
	}
	
}
