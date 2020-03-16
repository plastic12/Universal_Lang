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
		//write file
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
		//write code
		switch(lang)
		{
		case CPP:
			//write header file
			//write preprocessor
			os2.println("#ifndef "+name.toUpperCase()+"_H");
			os2.println("#define "+name.toUpperCase()+"_H");
			//TODO add dependency
			//write code
			os2.println("class "+name+"{");
			scope=1;
			//variable
			//sort all public private protected
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
				field.write(os2, Lang.CPP, scope);
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
				field.write(os2, Lang.CPP, scope);
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
				field.write(os2, Lang.CPP, scope);
			}
			for(Func function:publicFunc)
			{
				os2.write("\t");
				os2.write(function.signature(lang));
				os2.write(';');
				os2.println();
			}
			os2.write("};\n");
			//write end proprocessor
			os2.println("#endif");
			os2.flush();
			//write cpp
			scope=0;
			os.println("#include \""+name+".hpp"+"\"");
			
			for(Func function:functions)
			{
				os.println();
				os.write(name+"::");
				os.write(function.signature(lang));
				function.write(os, lang, scope);
			}
			
			
			
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
				os.write("\t"+function.signature(lang));
				function.write(os, lang, scope);
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
