package ArgsParserClean;

public class Application {

	public static void main(String[] args)
	{
		try 
		{
			Args arg = new Args("l,p#,d*", args);
			
			boolean logging = arg.getBoolean('l');
			int port = arg.getInt('p');
			String directory = arg.getString('d');
			
			Application app = new Application();
			app.execute(logging, port, directory);
		} 
		catch (ArgsException e) 
		{
			System.out.printf("Argument error: %s\n", e.errorMessage());
		}
	}
	
	private void execute(boolean logging, int port, String directory)
	{
		System.out.println(String.format("execute: logging=%b, port=%d, directory=%s", logging, port, directory));
	}
}
