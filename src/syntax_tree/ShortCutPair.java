package syntax_tree;

import java.io.Serializable;

public class ShortCutPair implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String name;
	private Lang lang;
	public ShortCutPair(String name,Lang lang)
	{
		this.name=name;this.lang=lang;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ShortCutPair)
		{
			return ((ShortCutPair) obj).lang==lang&&((ShortCutPair) obj).name.equals(name);
		}
		return false;
	}
	@Override
	public int hashCode() {
		return name.hashCode()+lang.hashCode();
	}
	

}
