package syntax_tree;

import java.io.PrintWriter;

public class CBlock extends Command
{
	public String code;
	public CBlock(String code)
	{this.code=code;}
	@Override
	public int write(PrintWriter writer, Lang lang, int scope)
	{
		// write scope
		for(int i=0;i<scope;i++)
		{
			writer.write("\t");
		}
		//write code
		writer.write(code);
		writer.println();
		return 0;
	}

}
