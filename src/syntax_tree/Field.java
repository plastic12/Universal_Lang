package syntax_tree;

import java.io.PrintWriter;

public class Field extends Var implements Depends
{
	private AModifier modifier;
	private boolean isPrimitive;
	public Field(AModifier modifier,String type,String name,boolean isPrimitive){
		super(name,type);
		this.modifier=modifier;this.isPrimitive=isPrimitive;
	}
	public int write(PrintWriter writer, Lang lang, int scope) 
	{
		//write scope
		for(int i=0;i<scope;i++)
			writer.print("\t");
		//write content
		switch(lang)
		{
		case CPP:
			writer.println(type+" "+name+";");
			break;
		case JAVA:
			writer.println(modifier+" "+type+" "+name+";");
			break;
		case GO:
			writer.println(name+" "+type);
			break;
		}
		return 0;
	}
	public AModifier getModifier() {return modifier;}
	public String getType() {return type;}
	public String getName() {return name;}
	public String getName(Lang lang) {
		switch(lang)
		{
		case GO:
		case JAVA:
			return "this."+name;
		case CPP:
			return "this->"+name;
		}
		return "";
	}
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
