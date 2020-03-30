package syntax_tree;

import java.io.PrintWriter;
import java.util.Set;

public class Assign extends Command{
	private Value ref;
	private Value value;
	public Assign(Value ref,Value value) {this.ref=ref;this.value=value;}
	@Override
	public String depends(Lang lang) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int write(PrintWriter writer, Lang lang, String classname, int scope) {
		for(int i=0;i<scope;i++)
			writer.print("\t");
		writer.println(ref.getName(lang)+"="+value.getName(lang)+";");
		return 0;
	}

}
