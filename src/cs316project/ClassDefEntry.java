package cs316project;

import java.util.*;

class ClassDefEntry {
	String superClass;
	LinkedList<String> fields;
	HashMap<String, LinkedList<String>> funMap;
	LinkedList<String> params;
	
	ClassDefEntry(){
		superClass = "";
		fields = new LinkedList<String>();
		funMap = new HashMap<String, LinkedList<String>>();
		params = new LinkedList<String>();
	}
	public void setSuperClass(String t) {
		superClass = t;
	}
	public void appendFields(String t) {
		fields.addLast(t);
	}
	public void setKey(String funName) {
		funMap.put(funName, new LinkedList<String>());
	}
	public void mapFunc(String funName, String params) {
		if(!funMap.containsKey(funName)) {
			this.setKey(funName);
		}
		funMap.get(funName).addLast(params);
	}
	public void appendParams(String param) {
		params.add(param);
	}
	public LinkedList<String> getList() {
		return params;
	}
	public String toString()
	{
		return "\nsuperclass=" + superClass + "\nfields=" + fields.toString() + "\nfunctions=" + funMap.toString() + "\n\n";
	}
}
