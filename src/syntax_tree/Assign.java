package syntax_tree;

import java.io.PrintWriter;
import java.util.Set;

public class Assign extends Command{
	private String ref;
	private String value;
	public Assign(String ref,String value) {this.ref=ref;this.value=value;}
	@Override
	public String depends(Lang lang) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int write(PrintWriter writer, Lang lang, String classname, Set<String> var, int scope) {
		writer.println(ref+"="+value);
		return 0;
	}

}
