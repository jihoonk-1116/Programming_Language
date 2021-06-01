package cs316project;

import java.util.HashMap;

class ExpKeyword extends Exp{
    String keyword;

    ExpKeyword(String k){
        keyword = k;
    }
    void printParseTree(){
        IO.displayln( keyword + " <ExpKeyword>");
       
    }
    Val Eval(HashMap<String, ClassDefEntry> symbolTable) {
    	if(keyword == "this") {
    		return null;
    	}
    	if(keyword == "null") {
    		return new NullObj();
    	}
    	return null;
    };
}
