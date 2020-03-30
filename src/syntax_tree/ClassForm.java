package syntax_tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeSet;

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
	public void addField(AModifier modifier,String type,String name,boolean isPrimitive)
	{
		fields.add(new Field(modifier,type,name,isPrimitive));
	}
	public Field searchField(String name)
	{
		for(Field field:fields)
		{
			if(field.getName()==name)
				return field;
		}
		return null;
	}
	public Func searchFunc(String name)
	{
		for(Func func:functions)
		{
			if(func.getHashName()==name)
				return func;
		}
		return null;
	}
	public void addFunc(Func function)
	{
		functions.add(function);
	}
	public void write(Lang lang) throws IOException
	{
		//declare file
		switch(lang)
		{
		case CPP:
			os=new PrintWriter(new FileWriter(new File(name+".cpp")));
			os2=new PrintWriter(new FileWriter(new File(name+".hpp")));
			break;
		case JAVA:
			os=new PrintWriter(new FileWriter(new File(name+".java")));
			break;
		case GO:
			os=new PrintWriter(new FileWriter(new File(name+".go")));
		}
		int scope=0;
		int scope2=0;
		//write code
		//add preprocessor
		switch(lang)
		{
		case CPP:
			os2.println("#ifndef "+name.toUpperCase()+"_H");
			os2.println("#define "+name.toUpperCase()+"_H");
			os.println("#include \""+name+".hpp"+"\"");
			break;
		case GO:
			os.println("package main");
			break;
		}
		//add dependency
		/*
		TreeSet<String> dependencies=addDependencies(lang);
		switch(lang)
		{
		case CPP:
			for(String c:dependencies)
				os2.println("#include "+c);
			break;
		case GO:
			for(String c:dependencies)
				os.println("import "+c);
			break;
		}
		*/
		//write field
		switch(lang)
		{
		case CPP:
			os2.println("class "+name+"{");
			scope2=1;
			ArrayList<Field> publicField=new ArrayList<Field>();
			ArrayList<Field> protectedField=new ArrayList<Field>();
			ArrayList<Field> privateField=new ArrayList<Field>();
			ArrayList<Func> publicFunc=new ArrayList<Func>();
			ArrayList<Func> protectedFunc=new ArrayList<Func>();
			ArrayList<Func> privateFunc=new ArrayList<Func>();
			for(Field field:fields)
			{
				switch(field.getModifier())
				{
				case PUBLIC:
					publicField.add(field);
					break;
				case PROTECTED:
					protectedField.add(field);
					break;
				case PRIVATE:
					privateField.add(field);
					break;
				}	
			}
			for(Func function:functions)
			{
				switch(function.getModifier())
				{
				case PUBLIC:
					publicFunc.add(function);
					break;
				case PROTECTED:
					protectedFunc.add(function);
					break;
				case PRIVATE:
					privateFunc.add(function);
					break;
				}	
			}
			//write public protected private
			os2.println("\tprivate:");
			for(Field field:privateField)
			{
				field.write(os2, Lang.CPP, scope2);
			}
			for(Func function:privateFunc)
			{
				os2.write("\t");
				os2.write(function.signature(lang));
				os2.write(';');
				os2.println();
			}
			os2.println("\tprotected:");
			for(Field field:protectedField)
			{
				field.write(os2, Lang.CPP, scope2);
			}
			for(Func function:protectedFunc)
			{
				os2.write("\t");
				os2.write(function.signature(lang));
				os2.write(';');
				os2.println();
			}
			os2.println("\tpublic:");
			for(Field field:publicField)
			{
				field.write(os2, Lang.CPP, scope2);
			}
			for(Func function:publicFunc)
			{
				os2.write("\t");
				os2.write(function.signature(lang));
				os2.write(';');
				os2.println();
			}
			os2.write("};\n");
			os2.println("#endif");
			os2.flush();
			break;
		case JAVA:
			os.println("public class "+name);
			os.println("{");
			scope++;
			for(Field field:fields)
			{
				field.write(os, lang, scope);
			}
			break;
		case GO:
			os.println("type "+name+" struct {");
			scope++;
			for(Field field:fields)
			{
				scope+=field.write(os, lang, scope);
			}
			os.println("}");
			scope=0;
		}
		//write func
		for(Func function:functions)
		{
			function.write(os,lang, name, scope);
		}
		for (;scope>0;)
		{
			scope--;
			for(int i=0;i<scope;i++)
			{
				os.write("\t");
			}
			os.println("}");
		}
		os.flush();
	}
	/*
	public TreeSet<String> addDependencies(Lang lang)
	{
		TreeSet<String> output=new TreeSet<String>();
		//write field
		for(Field f:fields)
		{
			String s=f.depends(lang);
			if(s!=null)
				output.add(s);
		}
		//write function
		for(Func f:functions)
		{
			String s=f.depends(lang);
			if(s!=null)
				output.add(s);
			for(Command c:f.getCommands())
			{
				String s2=c.depends(lang);
				if(s2!=null)
					output.add(s2);
			}
		}
		return output;
	}
	*/
}
