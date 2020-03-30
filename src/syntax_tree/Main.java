package syntax_tree;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.TreeSet;

public class Main 
{
	public static Stack<String> nameSpace=new Stack<String>();
	public static Stack<Integer> stackInt=new Stack<Integer>();
	public static HashMap<String,Var> varStorage=new HashMap<String,Var>();
	public static ArrayList<ClassForm> classes=new ArrayList<ClassForm>();
	
	
	public static void main(String[] args) throws IOException
	{
		//addDepend();
		testClass();
		//addShortCut();
	}
	public static void testMain() throws IOException
	{
		MainFunc m=new MainFunc();
		String[] args1= {"\"plastic12 is a god\""};
		m.addCommand(new ShortCut("print",args1));
		m.write(Lang.CPP);
	}
	public static void testClass() throws IOException
	{
		//1. class hierarchy
		ClassForm player=new ClassForm("Player");
		classes.add(player);
		player.addField(AModifier.PRIVATE, "int", "money",true);
		player.addField(AModifier.PRIVATE, "double", "field2",true);
		player.addField(AModifier.PRIVATE, "Property", "property1",false);
		player.addField(AModifier.PRIVATE, "Property", "property2",false);
		Func func1=new Func(AModifier.PUBLIC,"void","toSth",true);
		player.addFunc(func1);
		//2. declare function
		push();
		addDeclare("x", "int", new IntConst(1), func1);
		addDeclare("y","int",new IntConst(3),func1);
		addDeclare("temp","int",null,func1);
		func1.addCommand(new Assign(varStorage.get("temp"),varStorage.get("x")));
		func1.addCommand(new Assign(varStorage.get("x"),varStorage.get("y")));
		func1.addCommand(new Assign(varStorage.get("y"),varStorage.get("temp")));
		
		//func1.addCommand(new Comment("to something"));
		//String[] args1= {"\"plastic12 is a god\""};
		//func1.addCommand(new ShortCut("print",args1));
		//func1.addCondition("a==1");
		//func1.addCommand(new ShortCut("print",args1));
		//3. write
		
		player.write(Lang.JAVA);
		/*
		ClassForm property =new ClassForm("Property");
		property.addField(AModifier.PRIVATE, "int", "price",true);
		property.addField(AModifier.PRIVATE, "int", "rent",true);
		property.addField(AModifier.PRIVATE, "int", "pos",true);
		Func func2=new Func(AModifier.PUBLIC,"void","pay",true);
		func1.addCommand(new Comment("to something"));
		String[] args2= {"\"pay rent\""};
		func1.addCommand(new ShortCut("print",args2));
		property.addFunc(func2);
		property.write(Lang.CPP);
		*/
		//test player has 2 include
	}
	public static void push() {
		stackInt.push(nameSpace.size());
	}
	public static void addDeclare(String name,String type,Value init,Func func){
		Var var=new Var(name,type);
		nameSpace.add(name);
		varStorage.put(name, var);
		func.addCommand(new Declare(var,init));
	}
	public static void pop() {
		for(;nameSpace.size()>stackInt.peek();) {
			varStorage.remove(nameSpace.pop());
		}
		stackInt.pop();
	}
	public static void popAll(){
		for(;!stackInt.isEmpty();)
			pop();
	}
	public static void addShortCut(){
		ShortCut.addKeyword("print", Lang.CPP, "std::cout<<%s<<std::endl;");
		ShortCut.saveLib();
	}
	public static void addDepend()
	{
		ShortCut.addDepend("print", Lang.CPP, "<iostream>");
		ShortCut.saveLib();
	}
}
