package syntax_tree;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Func implements CodeWritable
{
	private AModifier modifier;
	private String name;
	private String type;
	private ArrayList<Field> parameters;
	private LinkedList<Command> commands;
	public Func(AModifier modifier,String type,String name)
	{
		this.modifier=modifier;this.type=type;this.name=name;commands=new LinkedList<Command>();parameters=new ArrayList<Field>();
	}
	public Func(AModifier modifier,String type,String name,ArrayList<Field> parameters)
	{
		this.modifier=modifier;this.type=type;this.name=name;commands=new LinkedList<Command>();this.parameters=parameters;
	}
	public void addCommand(Command command)
	{
		commands.add(command);
	}
	@Override
	public int write(PrintWriter writer, Lang lang, int scope) {
		switch(lang)
		{
		case CPP:
			break;
		case JAVA:
			//start function
			for(int i=0;i<scope;i++)
			{
				writer.write("\t");
			}
			writer.write(modifier+" "+type+" "+name+" "+"(");
			
			for(Iterator<Field> iter=parameters.iterator();iter.hasNext();)
			{
				Field field=iter.next();
				writer.write(field.getType()+" "+field.getName());
				if(iter.hasNext())
					writer.write(",");
			}
			writer.write(")");
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
			writer.write("}");
			writer.println();
			break;
		}
		return 0;
	}

}
