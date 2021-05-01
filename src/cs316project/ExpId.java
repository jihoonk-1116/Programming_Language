package cs316project;

class ExpId extends Exp{
    String id;

    ExpId(String t){
        id = t;
    }
    void printParseTree(){
        IO.displayln( id + " <ExpId>");
        
    }
}