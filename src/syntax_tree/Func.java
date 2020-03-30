package syntax_tree;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Func implements Depends
{
	private AModifier modifier;
	private String name;
	private String type;
	private boolean isPrimitive;
	private ArrayList<Field> parameters;
	private Branch b;
	private Stack<Branch> s;
	public Func(AModifier modifier,String type,String name,boolean isPrimitive)
	{
		this.modifier=modifier;this.type=type;this.name=name;
		this.isPrimitive=isPrimitive;
		parameters=new ArrayList<Field>();
		b=new Branch();
		s=new Stack<Branch>();
		s.add(b);
	}
	public Func(AModifier modifier,String type,String name,boolean isPrimitive,ArrayList<Field> parameters)
	{
		this.modifier=modifier;
		this.type=type;
		this.name=name;
		this.isPrimitive=isPrimitive;
		this.parameters=parameters;
		b=new Branch();
		s=new Stack<Branch>();
		s.add(b);
	}
	public void addCondition(String condition)
	{
		ConditionBlock cb=new ConditionBlock(condition);
		b.addCommand(cb);
		s.push(cb.getBranch());
	}
	public void pop()
	{
		if(s.size()>1)
			s.pop();
	}
	public void addCommand(Command command)
	{
		s.peek().addCommand(command);
	}
	public String getHashName() {return name+"()";}
	public String getName() {return name;}
	public String getType() {return type;}
	public AModifier getModifier()
	{
		return modifier;
	}
	public String signature(Lang lang)
	{
		
		String output="";
		switch(lang)
		{
		case CPP:
			output+=type+" "+name+"(";
			break;
		case JAVA:
			output+=modifier+" "+type+" "+name+"(";
			break;
		case GO:
			output+=name+"(";
		}
		for(Iterator<Field> iter=parameters.iterator();iter.hasNext();)
		{
			Field field=iter.next();
			if(lang!=Lang.GO)
			{
				output+=field.getType()+" "+field.getName(lang);
			}
			else
			{
				output+=field.getName(lang)+" "+field.getType();
			}
			if(iter.hasNext())
				output+=",";
		}
		output+=")";
		if(lang==Lang.GO)
			output+=" "+type;
		return output;
	}
	public int write(PrintWriter writer, Lang lang,String classname,int scope) {
		switch(lang)
		{
		case CPP:
			//start function
			for(int i=0;i<scope;i++)
			{
				writer.write("\t");
			}
			writer.write(type+" "+classname+"::"+signature(lang)+"{\n");
			scope++;
			break;
			//start the scope
		case JAVA:
			//start function
			for(int i=0;i<scope;i++)
			{
				writer.write("\t");
			}
			writer.write(signature(lang)+"{\n");
			scope++;
			//start the scope
			break;
		case GO:
			//start function
			for(int i=0;i<scope;i++)
			{
				writer.write("\t");
			}
			writer.write("func"+"(this *"+classname+") "+signature(lang)+"{\n");
			scope++;
			//start the scope
			break;
		}
		b.write(writer, lang,classname, scope);
		//end function
		scope--;
		for(int i=0;i<scope;i++)
		{
			writer.write("\t");
		}
		writer.println("}");
		writer.println();
		return 0;
	}
	@Override
	public String depends(Lang lang) 
	{
		if(!isPrimitive)
		{
			switch(lang)
			{
			case CPP:
				return "\""+type+".hpp"+"\"";
			}
		}
		return null;
	}

}
