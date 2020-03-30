package syntax_tree;

import java.util.ArrayList;
import java.util.Iterator;

public class FuncInst implements Value{
	public Func function;
	public ArrayList<Value> parameters;

	public FuncInst(Func func) {
		function=func;
		
	}

	@Override
	public String getType() {
		return function.getType();
	}

	@Override
	public String getName(Lang lang) {
		String output=function.getName()+"(";
		for(Iterator<Value> iter=parameters.iterator();iter.hasNext();)
		{
			Value value=iter.next();
			output+=value.getName(lang);
			if(iter.hasNext())
				output+=",";
		}
		output+=")";
		return output;
	}

}
