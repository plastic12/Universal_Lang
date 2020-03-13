package syntax_tree;

import java.io.PrintWriter;

public interface CodeWritable 
{
	public int write(PrintWriter writer,Lang lang,int scope);

}
