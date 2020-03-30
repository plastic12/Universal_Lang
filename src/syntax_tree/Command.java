package syntax_tree;

import java.io.PrintWriter;
import java.util.Set;

public abstract class Command implements Depends{
	public abstract int write(PrintWriter writer, Lang lang,String classname,int scope);

}
