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
		ClassForm player=new ClassForm("Player");
		player.addField(AModifier.PRIVATE, "int", "money",true);
		player.addField(AModifier.PRIVATE, "double", "field2",true);
		player.addField(AModifier.PRIVATE, "Property", "property1",false);
		player.addField(AModifier.PRIVATE, "Property", "property2",false);
		Func func1=new Func(AModifier.PUBLIC,"void","toSth",true);
		func1.addCommand(new Comment("to something"));
		String[] args1= {"\"plastic12 is a god\""};
		func1.addCommand(new ShortCut("print",args1));
		func1.addCondition("a==1");
		func1.addCommand(new ShortCut("print",args1));
		player.addFunc(func1);
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
	public static void addShortCut()
	{
		ShortCut.addKeyword("print", Lang.CPP, "std::cout<<%s<<std::endl;");
		ShortCut.saveLib();
	}
	public static void addDepend()
	{
		ShortCut.addDepend("print", Lang.CPP, "<iostream>");
		ShortCut.saveLib();
	}
}
