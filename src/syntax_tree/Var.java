package syntax_tree;

public class Var implements Value{
	protected String name;
	protected String type;

	public Var(String name, String type) {
		this.name=name;this.type=type;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getName(Lang lang) {
		return name;
	}

}
