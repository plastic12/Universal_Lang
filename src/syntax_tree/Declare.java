package syntax_tree;

import java.io.PrintWriter;
import java.util.Set;

public class Declare extends Command{
	private Var var;
	private Value init;
	
	
	public Declare(Var var,Value init)
	{
		this.var=var;
		this.init=init;
	}
	@Override
	public String depends(Lang lang) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int write(PrintWriter writer, Lang lang, String classname, int scope) 
	{
		for(int i=0;i<scope;i++)
			writer.print("\t");
		switch(lang)
		{
		case CPP:
		case JAVA:
			if(init!=null)
				writer.println(this.var.getType()+" "+this.var.getName(lang)+"="+init.getName(lang)+";");
			else
				writer.println(this.var.getType()+" "+this.var.getName(lang)+";");
			break;
		case GO:
			if(init!=null)
				writer.println(this.var.getName(lang)+":="+init.getName(lang));
			else
				writer.println("var "+this.var.getName(lang)+" "+this.var.getType());
			break;
		}
		return 0;
	}

}
