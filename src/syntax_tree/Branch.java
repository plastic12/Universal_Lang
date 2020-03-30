package syntax_tree;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Set;

public class Branch extends Command{
	LinkedList<Command> b;
	public Branch()
	{
		b=new LinkedList<Command>();
	}
	@Override
	public String depends(Lang lang) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int write(PrintWriter writer, Lang lang, String classname, int scope) {
		for(Command c:b)
			c.write(writer, lang, classname, scope);
		return 0;
	}
	public void addCommand(Command c) {b.add(c);}

}
