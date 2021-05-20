package cs316project;

class ClassDef{
    ClassName className;
    ClassName className2;
    ClassBody classBody;

    ClassDef(ClassName name, ClassBody body){
        className = name;
        classBody = body;
        className2 = null;
    }
    ClassDef(ClassName name, ClassName name2, ClassBody body){
        className = name;
        className2 = name2;
        classBody = body;
    }
    void printParseTree(String indent)
	{
		String indent1 = indent + " ";
        
		IO.displayln(indent + indent.length() + " <ClassDef>");
        IO.displayln(indent1 + indent1.length() + " className");

		className.printParseTree();
        if(className2 != null){
            IO.displayln(indent1 + indent1.length() +" class");
			className2.printParseTree();
        }
        classBody.printParseTree();
	}
}
