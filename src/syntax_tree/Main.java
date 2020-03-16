package syntax_tree;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main 
{
	public static void main(String[] args) throws IOException
	{
		testMain();
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
		ClassForm testClass=new ClassForm("TestClass");
		testClass.addField(AModifier.PRIVATE, "int", "field1");
		testClass.addField(AModifier.PRIVATE, "double", "field2");
		testClass.addField(AModifier.PRIVATE, "String", "field3");
		Func func1=new Func(AModifier.PUBLIC,"void","toSth");
		func1.addCommand(new Comment("to something"));
		String[] args1= {"\"plastic12 is a god\""};
		func1.addCommand(new ShortCut("print",args1));
		func1.addCommand(new CBlock("int x=10;"));
		func1.addCommand(new ConditionBlock("true"));
		func1.addCommand(new EndCondition());
		testClass.addFunc(func1);
		testClass.write(Lang.CPP);
	}
	public static void addShortCut()
	{
		ShortCut.addKeyword("print", Lang.CPP, "cout<<%s<<endl");
		ShortCut.saveLib();
	}

}
