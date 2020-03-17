package syntax_tree;

import java.io.PrintWriter;

public class EndCondition extends Command
{

	@Override
	public int write(PrintWriter writer, Lang lang, int scope) {
		scope--;
		for(int i=0;i<scope;i++)
		{writer.write("\t");}
		writer.write("}");
		writer.println();
		return -1;
	}

	@Override
	public String depends(Lang lang) {
		// TODO Auto-generated method stub
		return null;
	}

}
