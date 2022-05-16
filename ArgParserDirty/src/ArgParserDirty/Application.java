package ArgParserDirty;

import java.text.ParseException;

// "l,p#,d*" == "-l -p 65536 -d letsplay"

public class Application {
	public static void main(String[] args) throws Exception
	{
		try 
		{
			Args arg = new Args("l,p#,d*", args);
			if(!arg.isValid())
			{
				String error = arg.errorMessage();
				throw new Exception(error);
			}
			
			
			boolean logging = arg.getBoolean('l');
			int port = arg.getInt('p');
			String directory = arg.getString('d');
			
			Application app = new Application();
			app.execute(logging, port, directory);
		} 
		catch (ParseException e) 
		{
			System.out.printf("Argument error: %s\n", e.getMessage());
		}
		catch (Exception e)
		{
			System.out.printf(e.getMessage());
		}
		
	}
	
	private void execute(boolean logging, int port, String directory)
	{
		System.out.println(String.format("execute: logging=%b, port=%d, directory=%s", logging, port, directory));
	}
}
