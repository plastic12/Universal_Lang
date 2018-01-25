package syntax_tree;

import java.util.ArrayList;

public class FuncField extends Field
{
	public FuncField(String type, String name) {
		super(type, name);
		// TODO Auto-generated constructor stub
	}
	public void addPara(Field x)
	{
		para.add(x);
	}
	private ArrayList<Field> para;
}
