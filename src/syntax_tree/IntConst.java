package syntax_tree;

public class IntConst implements Value{
	public int v;
	
	public IntConst(int v) {
		this.v=v;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "int";
	}

	@Override
	public String getName(Lang lang) {
		// TODO Auto-generated method stub
		return Integer.toString(v);
	}

}
