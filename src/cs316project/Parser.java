package cs316project;

/*
⟨class def list⟩ → ⟨class def⟩ | ⟨class def⟩ ⟨class def list⟩
⟨class def⟩ → "class" ⟨class name⟩ [ : ⟨class name⟩ ] ⟨class body⟩   // ⟨class name⟩ after ":" is the direct superclass name.
⟨class name⟩ → ⟨id⟩
⟨class body⟩ → "{" ⟨field var list⟩ ⟨fun def list⟩ "}"
⟨field var list⟩ → ε | ⟨field var⟩ ⟨field var list⟩
⟨field var⟩ → ⟨id⟩
⟨fun def list⟩ → ε | ⟨fun def⟩ ⟨fun def list⟩
⟨fun def⟩ → "(" ⟨header⟩ ⟨exp⟩ ")"
⟨header⟩ → "(" ⟨fun name⟩ ⟨parameter list⟩ ")"
⟨fun name⟩ → ⟨id⟩
⟨parameter list⟩ → ε | ⟨parameter⟩ ⟨parameter list⟩
⟨parameter⟩ → ⟨id⟩
⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "null" | "this" | "(" ⟨fun exp⟩ ")"
⟨fun exp⟩ → ⟨fun call⟩ | ⟨binary exp⟩ | ⟨cond⟩ | ⟨not⟩
⟨fun call⟩ → ⟨fun name⟩ ⟨exp list⟩
⟨exp list⟩ → ε | ⟨exp⟩ ⟨exp list⟩
⟨binary exp⟩ → ⟨arith exp⟩ | ⟨bool exp⟩ | ⟨comp exp⟩ | ⟨dot exp⟩
⟨arith exp⟩ → ⟨arith op⟩ ⟨exp⟩ ⟨exp⟩
⟨bool exp⟩ → ⟨bool op⟩ ⟨exp⟩ ⟨exp⟩
⟨comp exp⟩ → ⟨comp op⟩ ⟨exp⟩ ⟨exp⟩
⟨dot exp⟩ → "." ⟨exp⟩ ⟨exp⟩
⟨cond⟩ → "if" ⟨exp⟩ ⟨exp⟩ ⟨exp⟩
⟨not⟩ → ! ⟨exp⟩
⟨arith op⟩ → + | − | * | /
⟨bool op⟩ → "|" | "&"
⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "="
*/
import java.util.*;

public abstract class Parser extends LexArithArray
{
	static boolean errorFound = false;
	static HashMap<String, ClassDefEntry> symbolTable = new HashMap<String, ClassDefEntry>();
	static ClassDefEntry defEntry;
	static String class_name, func_name;

	public static ClassDefList classDefList(){
	    //⟨class def list⟩ → ⟨class def⟩ | ⟨class def⟩ ⟨class def list⟩
		defEntry = new ClassDefEntry();
	    ClassDef classDef = classDef();
	    if(classDef == null) {
	    	return null;
	    }
	    if(state == State.Keyword_class){
	    	symbolTable.put(classDef.className.id, defEntry);
	        return new ClassMultiDef(classDef, classDefList());
	    }
	    else{
//	    	 System.out.println("ClassName "+ classDef.className.id);
	    	symbolTable.put(classDef.className.id, defEntry);
	        return new ClassSingleDef(classDef);
	    }
	    
	}
	public static ClassDef classDef(){
	    //⟨class def⟩ → "class" ⟨class name⟩ [ : ⟨class name⟩ ] ⟨class body⟩ 
	    if(state == State.Keyword_class){
	        getToken();
	        ClassName className = className();
	        class_name = className.id;
	        if(state == State.Colon){
	            getToken();
	            ClassName superClass = className();
	            ClassBody classBody = classBody();
	           // System.out.println("ClassName "+ className.id);
	           // System.out.println("SuperClass: "+ className2.id);
	            defEntry.setSuperClass(superClass.id);
	            return new ClassDef(className, superClass, classBody);
	        }
	        else{
	            ClassBody classBody = classBody();
//	            System.out.println("ClassName "+ className.id);
	            return new ClassDef(className, classBody);
	        }   
	    }else{
	        errorMsg(6);
	        return null;
	    }
	}
	public static ClassName className(){
	    //⟨class name⟩ → ⟨id⟩
	    if(state == State.Id){
	        String id= t;
	        getToken();
	        return new ClassName(id);
	    }else{
	        errorMsg(5);
	        return null;
	    }
	}
	public static ClassBody classBody(){
	    //⟨class body⟩ → "{" ⟨field var list⟩ ⟨fun def list⟩ "}"
	    if(state == State.LBrace){
	        getToken();
	        FieldVarList fieldVarList = fieldVarList();
	        FunDefList funDefList = funDefList();
	        if(state == State.RBrace){
	            getToken();
	            return new ClassBody(fieldVarList, funDefList);
	        }
	        else {
	            errorMsg(7);
	            return null;
	        }
	    }
	    else
	        errorMsg(8);
	    
	    return null;
	}
	//⟨field var list⟩ → ε | ⟨field var⟩ ⟨field var list⟩
	//⟨field var⟩ → ⟨id⟩

	public static FieldVarList fieldVarList(){
	    if(state == State.Id){
	        FieldVar fieldVar = fieldVar();
	        defEntry.appendFields(fieldVar.id);
	        return new NE_FieldVarList(fieldVar, fieldVarList());
	    }
	    else if(state == State.LParen || state == State.RBrace){
	        return new E_FieldVarList();
	    }
	    errorMsg(7);
	    return null;
	}
	public static FieldVar fieldVar(){
	    if(state == State.Id){
	        String id = t;
//	        System.out.println("FieldVar: "+ id);
	        getToken();
	        return new FieldVar(id);
	    }
	    errorMsg(5);
	    return null;
	}
	public static FunDefList funDefList(){
	    //⟨fun def list⟩ → ε | ⟨fun def⟩ ⟨fun def list⟩
	    if(state == State.LParen){
	        FunDef funDef = funDef();
	        return new NE_FunDefList(funDef, funDefList());
	    }
	    else if(state == State.RBrace){
	    	
	        return new E_FunDefList();
	    }
	    else {
	    	errorMsg(9);
		    return null;
	    }
	    
	}
	public static FunDef funDef(){
	    //⟨fun def⟩ → "(" ⟨header⟩ ⟨exp⟩ ")"
	    if(state == State.LParen){
	        getToken();
	        Header header = header();
	        Exp exp = exp();
	        if(state == State.RParen){
	            getToken();
	            return new FunDef(header, exp);
	        }else {
	        	errorMsg(10);
	        	return null;
	        }
	    }else{
	        errorMsg(9);
	    }
	    return null;
	}
	public static Header header(){
	    //⟨header⟩ → "(" ⟨fun name⟩ ⟨parameter list⟩ ")"
	    if(state == State.LParen){
	        getToken();
	        FunName funName = funName();
	        defEntry.setKey(funName.id);
	        func_name = funName.id;
	        ParameterList parameterList = parameterList(funName.id);
	        if(state == State.RParen){
	            getToken();
	            return new Header(funName, parameterList);
	        }
	        else {
	        	errorMsg(10);
	        	return null;
	        }
	            
	    }
	    else
	            errorMsg(11);
	    return null;
	}
	public static FunName funName(){
	    //⟨fun name⟩ → ⟨id⟩
	    if(state == State.Id){
	    	String id = t;
//	        System.out.println("FunName: "+ id);
	        getToken();
	        return new FunName(id);
	    }
	    errorMsg(5);
	    return null;
	}
	public static ParameterList parameterList(String name){
	    //⟨parameter list⟩ → ε | ⟨parameter⟩ ⟨parameter list⟩
	    if(state == State.Id){
	        Parameter parameter = parameter();
	        defEntry.mapFunc(name, parameter.id);

	        return new MultiParameterList(parameter, parameterList(name));
	    }
	    else if(state == State.RParen){
	        return new EmptyParameterList();
	    }
	    errorMsg(12);
	    return null;
	}
//	public static ParameterList parameterList(){
//	    //⟨parameter list⟩ → ε | ⟨parameter⟩ ⟨parameter list⟩
//	    if(state == State.Id){
//	        Parameter parameter = parameter();
//	        defEntry.mapFunc(k, parameter.id);
//	        return new MultiParameterList(parameter, parameterList());
//	    }
//	    else if(state == State.RParen){
//	        return new EmptyParameterList();
//	    }
//	    errorMsg(12);
//	    return null;
//	}
	public static Parameter parameter(){
	    //⟨parameter⟩ → ⟨id⟩
	    if(state == State.Id){
	        String id = t;
	        getToken();
	        return new Parameter(id);
	    }
	    errorMsg(5);
	    return null;
	}
	//abstract class and several classes are needed
	public static Exp exp(){
	    //⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "null" | "this" | "(" ⟨fun exp⟩ ")"
	   switch ( state ){
			case Id:	
				String id = t;
				if(state == State.Id) {
//		    		System.out.println(func_name + "  " + t);
//		    		System.out.println(defEntry.fields.toString());
//		    		System.out.println(defEntry.funMap.get(func_name).toString());
		    		if(defEntry.fields.contains(t) == false && defEntry.funMap.get(func_name).contains(t) == false) {
		    			displayln("Error: varibable " + t +" in " + class_name + "." + func_name +" is not declared.");
		    		}
		    	}
				getToken();
				return new ExpId(id);
			case Int:
				int intexp = Integer.parseInt(t);
				getToken();
				return new ExpInt(intexp);
			case Float: case FloatE:
				float floatexp = Float.parseFloat(t);
				getToken();
				return new ExpFloat(floatexp);
			case Keyword_null: case Keyword_this:
				String keyword = t;
				getToken();
				return new ExpKeyword(keyword);
			case LParen:
				getToken();
				FunExp funExp = funExp();
				if ( state == State.RParen ){
					getToken();
					return new ExpFun(funExp);
				}
				else{
					errorMsg(10);
					return null;
				}
					
			default:

				errorMsg(2);
				return null;
			}
	}
	public static FunExp funExp(){
	    //⟨fun exp⟩ → ⟨fun call⟩ | ⟨binary exp⟩ | ⟨cond⟩ | ⟨not⟩
	    if(state == State.Id){
	        FunCall funCall = funCall();
	        return new FunCallExp(funCall);
	    }
	    else if(state == State.Add || state == State.Sub || state == State.Mul || state == State.Div
	    || state == State.Or || state == State.And || state == State.Lt || state == State.Le || state == State.Gt
	    || state == State.Ge || state == State.Eq || state == State.DotOp){
	        BinaryExp binaryExp = binaryExp();
	        return new FunBinaryExp(binaryExp);
	    }
	    else if(state == State.Keyword_if){
	        Cond cond = cond();
	        return new FunCondExp(cond);
	    }
	    else if(state == State.Not){
	        Not not = not();
	        return new FunNotExp(not);
	    }
	    else{
	        errorMsg(13);
	        return null;
	    }
	}
	public static FunCall funCall(){
	    //⟨fun call⟩ → ⟨fun name⟩ ⟨exp list⟩     //⟨fun name⟩ → ⟨id⟩
	    if(state == State.Id){
	        FunName funName = funName();
	        ExpList expList = expList();
	        return new FunCall(funName, expList);
	    }
	    errorMsg(5);
	    return null;
	}
	public static ExpList expList(){

	    //⟨exp list⟩ → ε | ⟨exp⟩ ⟨exp list⟩
	    //⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "null" | "this" | "(" ⟨fun exp⟩ ")"
	    
	    if(state == State.Id || state == State.Int || state == State.Float || state == State.FloatE || 
	    state == State.Keyword_null || state == State.Keyword_this || state == State.LParen ){

	        Exp exp = exp();
	        
	        return new MultiExpList(exp, expList());
	    }
	    else if(state == State.RParen) {
	        //because ⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "null" | "this" | "(" ⟨fun exp⟩ ")"
	       
	        return new EmptyExpList();
	    }
	    errorMsg(2);
	    return null;
	    
	}
	public static BinaryExp binaryExp(){
	    //⟨binary exp⟩ → ⟨arith exp⟩ | ⟨bool exp⟩ | ⟨comp exp⟩ | ⟨dot exp⟩
	    if(state == State.Add || state == State.Sub ||state == State.Mul ||state == State.Div){
	        ArithExp arithExp = arithExp();
	        return new BinaryArithExp(arithExp);
	    }
	    else if(state==State.Or || state == State.And){
	        BoolExp boolExp = boolExp();
	        return new BinaryBoolExp(boolExp);
	    }
	    else if(state==State.Lt || state==State.Le ||state==State.Ge || state==State.Gt || state==State.Eq){
	        CompExp compExp = compExp();
	        return new BinaryCompExp(compExp);
	    }
	    else if(state == State.DotOp){
	        DotExp dotExp = dotExp();
	        return new BinaryDotExp(dotExp);
	    }
	    else errorMsg(2);
	    return null;
	}
	public static ArithExp arithExp(){
	    //⟨arith exp⟩ → ⟨arith op⟩ ⟨exp⟩ ⟨exp⟩
	    if(state == State.Add || state == State.Sub ||state == State.Mul ||state == State.Div){
	    	String op = state.toString();
	        getToken();
	        Exp exp = exp();
	        Exp exp2 = exp();
	        return new ArithExp(op, exp, exp2);
	    }
	    else 
	        errorMsg(14);
	    return null;
	}
	
	public static BoolExp boolExp(){
	   // ⟨bool exp⟩ → ⟨bool op⟩ ⟨exp⟩ ⟨exp⟩
	    if(state == State.Or || state == State.And ){
	    	String bool = state.toString();
	        getToken();
	        Exp exp = exp();
	        Exp exp2 = exp();
	        return new BoolExp(bool, exp, exp2);
	    }
	    else 
	        errorMsg(15);
	    return null;
	}
	public static CompExp compExp(){
	   // ⟨comp exp⟩ → ⟨comp op⟩ ⟨exp⟩ ⟨exp⟩
	    if(state==State.Lt || state==State.Le ||state==State.Gt || state==State.Ge || state==State.Eq){
	    	String comp = state.toString();
	        getToken();
	        Exp exp = exp();
	        Exp exp2 = exp();
	        return new CompExp(comp, exp, exp2);
	    }
	    else errorMsg(16);
	    return null;
	}
	public static DotExp dotExp(){
	   // ⟨dot exp⟩ → "." ⟨exp⟩ ⟨exp⟩
	    if(state == State.DotOp){
	        getToken();
	        Exp exp = exp();
	        Exp exp2 = exp();
	        return new DotExp(exp, exp2);
	    }
	    else errorMsg(17);
	    return null;
	}
	public static Cond cond(){
	  // ⟨cond⟩ → "if" ⟨exp⟩ ⟨exp⟩ ⟨exp⟩
	    if(state == State.Keyword_if){
	        getToken();
	        Exp exp = exp();
	        Exp exp2 = exp();
	        Exp exp3 = exp();
	        return new Cond(exp, exp2, exp3);
	    }
	    else errorMsg(18); 
	    return null;
	}
	public static Not not(){
	  //⟨not⟩ → ! ⟨exp⟩
	    if(state == State.Not){
	        getToken();
	        Exp exp = exp();
	        return new Not(exp);
	    }
	    else errorMsg(19);
	    return null;
	}
    

	
	public static void errorMsg(int i){
		errorFound = true;
		
		display(t + " : Syntax Error");

		switch( i ){
		case 1:	displayln(" arith op or ) expected"); return;
		case 2: displayln(" id, int, float, ( , Keyword_null or Keyword_this expected"); return;
		case 3:	displayln(" = expected"); return;
		case 4:	displayln(" ; expected:"); return;
		case 5:	displayln(" id expected"); return;	
		case 6: displayln(" keyword class expected"); return;
        case 7: displayln(" } expected"); return;
        case 8: displayln(" { expected"); return;
        case 9: displayln(" ( or } expected"); return;
        case 10: displayln(" ) expected"); return;
        case 11: displayln(" ( expected"); return;
        case 12: displayln(" id or ) expected"); return;
        case 13: displayln(" id ,bool, arith, dot, comparison expression expected"); return;
		case 14: displayln(" arith op expected"); return;
        case 15: displayln(" or , and expected"); return;
        case 16: displayln(" comparison expression expected"); return;
        case 17: displayln(" dot operation expected"); return;
        case 18: displayln(" keyword if expected"); return;
        case 19: displayln(" not operation expected"); return;
        }
	}

	public static void main(String argv[]){
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );
		setLex();

		getToken();

		
		ClassDefList classDefList = classDefList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + ": Syntax Error, unexpected symbol");
		else if ( ! errorFound ) {
			//classDefList.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file
			displayln("\nClass names and their field variables, function names, parameters are displayed below.\n");
			displayln(symbolTable.toString());
		}
			
		closeIO();
	}
}

