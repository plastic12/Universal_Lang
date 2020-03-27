package syntax_tree;

import java.io.PrintWriter;
import java.util.Set;

//that is if block
public class ConditionBlock extends Command
{
	private String condition;
	private Branch b;
	public ConditionBlock(String condition)
	{
		this.condition=condition;
		b=new Branch();
	}
	@Override
	public String depends(Lang lang) {
		// TODO Auto-generated method stub
		return null;
	}
	public Branch getBranch() {return b;}
	@Override
	public int write(PrintWriter writer, Lang lang, String classname, Set<String> var, int scope) {
		for(int i=0;i<scope;i++){
			writer.write("\t");
		}
		if(lang!=Lang.GO)
			writer.println("if"+"("+condition+")"+"{");
		else
			writer.println("if "+condition+" {");
		b.write(writer, lang, classname, var, scope+1);
		for(int i=0;i<scope;i++){
			writer.write("\t");
		}
		writer.println("}");
		return 0;
	}
	

}
