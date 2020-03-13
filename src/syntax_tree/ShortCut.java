package syntax_tree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

public class ShortCut extends Command
{
	public static HashMap<ShortCutPair,String> library;
	public static String libraryfile="library.dat";
	private String name;
	private String[] args;
	static
	{
		library=readLib();
	}
	public ShortCut(String name,String[] args)
	{
		this.name=name;this.args=args;
	}
	@Override
	public int write(PrintWriter writer, Lang lang, int scope) {
		// write scope
		for(int i=0;i<scope;i++)
		{
			writer.write("\t");
		}
		// write code
		String format=library.get(new ShortCutPair(name,lang));
		if(format!=null)
			writer.write(String.format(format, (Object[])args));
		else
			System.out.println("library call error");
		writer.println();
		return 0;
	}
	@SuppressWarnings("unchecked")
	public static HashMap<ShortCutPair,String> readLib()
	{
		File file=new File(libraryfile);
		if(file.exists())
		{
			//read file
			try (ObjectInputStream os=new ObjectInputStream(new FileInputStream(file));)
			{
				return (HashMap<ShortCutPair,String>) os.readObject();
			} catch (Exception e) {
				System.out.println("problem occur in reading the library");
				return new HashMap<ShortCutPair,String>();
			}
		}
		else
			return new HashMap<ShortCutPair,String>();
	}
	public static void saveLib()
	{
		File file=new File(libraryfile);
		try {
			ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream(file));
			oo.writeObject(library);
			oo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

	}
	public static void addKeyword(String name,Lang lang,String code)
	{
		library.put(new ShortCutPair(name,lang), code);
	}

}
