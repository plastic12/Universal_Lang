package syntax_tree;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class Func implements CodeWritable,Depends
{
	private AModifier modifier;
	private String name;
	private String type;
	private boolean isPrimitive;
	private ArrayList<Field> parameters;
	private LinkedList<Command> commands;
	public Func(AModifier modifier,String type,String name,boolean isPrimitive)
	{
		this.modifier=modifier;this.type=type;this.name=name;
		this.isPrimitive=isPrimitive;
		commands=new LinkedList<Command>();parameters=new ArrayList<Field>();
	}
	public Func(AModifier modifier,String type,String name,boolean isPrimitive,ArrayList<Field> parameters)
	{
		this.modifier=modifier;
		this.type=type;
		this.name=name;
		this.isPrimitive=isPrimitive;
		commands=new LinkedList<Command>();
		this.parameters=parameters;
	}
	public void addCommand(Command command)
	{
		commands.add(command);
	}
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
		}
		for(Iterator<Field> iter=parameters.iterator();iter.hasNext();)
		{
			Field field=iter.next();
			output+=field.getType()+" "+field.getName();
			if(iter.hasNext())
				output+=",";
		}
		output+=")";
		return output;
	}
	public LinkedList<Command> getCommands(){return commands;}
	@Override
	public int write(PrintWriter writer, Lang lang, int scope) {
		switch(lang)
		{
		case CPP:
			//start function
			writer.write("{");
			writer.println();
			scope++;
			//start the scope
			for(Command command:commands)
			{
				scope+=command.write(writer, lang, scope);
			}
			//end function
			scope--;
			for(int i=0;i<scope;i++)
			{
				writer.write("\t");
			}
			writer.println("}");
			writer.println();
			break;
		case JAVA:
			//start function
			writer.write("{");
			writer.println();
			scope++;
			//start the scope
			for(Command command:commands)
			{
				scope+=command.write(writer, lang, scope);
			}
			//end function
			scope--;
			for(int i=0;i<scope;i++)
			{
				writer.write("\t");
			}
			writer.println("}");
			writer.println();
			break;
		}
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
