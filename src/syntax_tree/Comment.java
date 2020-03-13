package syntax_tree;

import java.io.PrintWriter;

public class Comment extends Command
{
	public String comment;
	public Comment(String comment)
	{this.comment=comment;}
	@Override
	public int write(PrintWriter writer, Lang lang, int scope)
	{
		// write scope
		for(int i=0;i<scope;i++)
		{
			writer.write("\t");
		}
		//write code
		writer.write("\\"+comment);
		writer.println();
		return 0;
	}

}
