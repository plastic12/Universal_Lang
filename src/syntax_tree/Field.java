package syntax_tree;

import java.io.PrintWriter;

public class Field implements CodeWritable,Depends
{
	private AModifier modifier;
	private String type;
	private String name;
	private boolean isPrimitive;
	public Field(AModifier modifier,String type,String name,boolean isPrimitive)
	{this.modifier=modifier;this.type=type;this.name=name;this.isPrimitive=isPrimitive;}
	@Override
	public int write(PrintWriter writer, Lang lang, int scope) 
	{
		//write scope
		for(int i=0;i<scope;i++)
			writer.print("\t");
		//write content
		switch(lang)
		{
		case CPP:
			writer.write(type+" "+name+";");
			writer.println();
			break;
		case JAVA:
			writer.println(modifier+" "+type+" "+name+";");
			break;
		}
		return 0;
	}
	public AModifier getModifier() {return modifier;}
	public String getType() {return type;}
	public String getName() {return name;}
	public boolean isPrimitive() {return isPrimitive;}
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
