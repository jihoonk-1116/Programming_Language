package cs316project;

class ExpKeyword extends Exp{
    String keyword;

    ExpKeyword(String k){
        keyword = k;
    }
    void printParseTree(){
        IO.displayln( keyword + " <ExpKeyword>");
       
    }
}
