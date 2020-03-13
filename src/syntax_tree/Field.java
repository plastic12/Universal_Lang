package syntax_tree;

import java.io.PrintWriter;

public class Field implements CodeWritable
{
	private AModifier modifier;
	private String type;
	private String name;
	public Field(AModifier modifier,String type,String name)
	{this.modifier=modifier;this.type=type;this.name=name;}
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
			break;
		case JAVA:
			writer.println(modifier+" "+type+" "+name+";");
			break;
		}
		return 0;
	}
	public String getType() {return type;}
	public String getName() {return name;}
	
	
	
	
	
}
