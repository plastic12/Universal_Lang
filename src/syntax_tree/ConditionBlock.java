package syntax_tree;

import java.io.PrintWriter;

//that is if block
public class ConditionBlock extends Command
{
	private String condition;
	public ConditionBlock(String condition)
	{
		this.condition=condition;
	}
	@Override
	public int write(PrintWriter writer, Lang lang, int scope) {
		// write scope
		for(int i=0;i<scope;i++)
		{
			writer.write("\t");
		}
		writer.write("if"+"("+condition+")"+"{");
		writer.println();
		return 1;
	}
	@Override
	public String depends(Lang lang) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
