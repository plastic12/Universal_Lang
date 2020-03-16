package syntax_tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class MainFunc 
{
	
	private PrintWriter os;
	private LinkedList<Command> commands;
	public MainFunc() 
	{
		commands=new LinkedList<Command>();
	}
	public void addCommand(Command command)
	{
		commands.add(command);
	}
	public void write(Lang lang) throws IOException
	{
		//create file
		switch(lang)
		{
		case CPP:
			os=new PrintWriter(new FileWriter(new File("Main.cpp")));
			break;
		case JAVA:
			os=new PrintWriter(new FileWriter(new File("Main.java")));
			break;
		}
		//write file
		int scope=0;
		switch(lang)
		{
		case CPP:
			os.println("int main(){");
			scope=1;
			for(Command command:commands)
			{
				scope+=command.write(os, lang, scope);
			}
			os.println("}");
			os.flush();
			break;
		case JAVA:
			os.write("public class Main {");
			os.println();
			os.write("\tpublic static void main(String[] args){");
			os.println();
			scope=2;
			for(Command command:commands)
			{
				scope+=command.write(os, lang, scope);
			}
			os.write("\t}");
			os.println();
			os.write("}");
			os.flush();
			break;
		}
	}
	
	
}
