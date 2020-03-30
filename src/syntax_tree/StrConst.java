package syntax_tree;

public class StrConst implements Value{
	private String s;

	@Override
	public String getType() {
		return "String";
	}

	@Override
	public String getName(Lang lang) {
		return "\""+s+"\"";
	}

}
