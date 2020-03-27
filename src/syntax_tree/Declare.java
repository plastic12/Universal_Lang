package syntax_tree;

import java.io.PrintWriter;
import java.util.Set;

public class Declare extends Command{
	private String type;
	private String name;
	private String init;
	
	
	public Declare(String type,String name,String init)
	{
		this.type=type;
		this.name=name;
		this.init=init;
	}
	@Override
	public String depends(Lang lang) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int write(PrintWriter writer, Lang lang, String classname, Set<String> var, int scope) 
	{
		for(int i=0;i<scope;i++)
			writer.print("\t");
		switch(lang)
		{
		case CPP:
		case JAVA:
			if(init!=null)
				writer.println(type+" "+name+"="+init+";");
			else
				writer.println(type+" "+name+";");
			break;
		case GO:
			if(init!=null)
				writer.println(name+":="+init);
			else
				writer.println("var "+name+" "+type);
			break;
		}
		return 0;
	}

}
