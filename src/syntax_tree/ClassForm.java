package syntax_tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ClassForm 
{
	private String name;
	private ArrayList<Field> fields;
	private ArrayList<Func> functions;
	private PrintWriter os;
	private PrintWriter os2;
	public ClassForm(String name)
	{
		this.name=name;
		fields=new ArrayList<Field>();
		functions=new ArrayList<Func>();
	}
	public void addField(AModifier modifier,String type,String name)
	{
		fields.add(new Field(modifier,type,name));
	}
	public void addFunc(Func function)
	{
		functions.add(function);
	}
	public void write(Lang lang) throws IOException
	{
		String filename;
		switch(lang)
		{
		case CPP:
			os=new PrintWriter(new FileWriter(new File(name+".cpp")));
			os2=new PrintWriter(new FileWriter(new File(name+".hpp")));
			break;
		case JAVA:
			os=new PrintWriter(new FileWriter(new File(name+".java")));
			break;
		}
		int scope=0;
		switch(lang)
		{
		case CPP:
			//TODO
			break;
		case JAVA:
			//write class
			os.println("public class "+name);
			os.println("{");
			scope++;
			//write variable
			for(Field field:fields)
			{
				scope+=field.write(os, lang, scope);
			}
			//write function+write function header
			for(Func function:functions)
			{
				scope+=function.write(os, lang, scope);
			}
			//end class
			os.println("}");
			
			break;
		}
		os.flush();
		if(os2!=null)
		os2.flush();
		
	}
}
