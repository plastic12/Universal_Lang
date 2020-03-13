package syntax_tree;

public enum AModifier 
{
	PUBLIC
	{
		public String toString()
		{
			return "public";
		}
	}
	,PROTECTED
	{
		public String toString()
		{
			return "protected";
		}
	},
	PRIVATE
	{
		public String toString()
		{
			return "private";
		}
	}

}
