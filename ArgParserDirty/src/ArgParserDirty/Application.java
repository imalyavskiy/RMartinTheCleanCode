package ArgParserDirty;

// "l,p#,d*" == "-l -p 65536 -d letsplay -t 3.141592"

public class Application {
	public static void main(String[] args) throws Exception
	{
		Args arg;

		try
		{
			arg = new Args("l,p#,d*,t##", args);
		}
		catch (ArgsException e) 
		{
			System.out.printf(e.errorMessage());
		}
		boolean logging = arg.getBoolean('l');
		int port = arg.getInt('p');
		String directory = arg.getString('d');
		double period = arg.getDouble('t');

		Application app = new Application();
		app.execute(logging, port, directory, period);
	}
	
	private void execute(boolean logging, int port, String directory, double period)
	{
		System.out.println(String.format("execute: logging=%b, port=%d, directory=%s, period=%f", logging, port, directory, period));
	}
}
