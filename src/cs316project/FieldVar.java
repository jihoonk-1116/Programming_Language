package cs316project;

class FieldVar{
    String id;

    FieldVar(String t){
        id = t;
    }
    void printParseTree(){
        IO.displayln(id+" <FieldVar>");
       
    }
}