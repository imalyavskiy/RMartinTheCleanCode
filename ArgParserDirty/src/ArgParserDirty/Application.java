package ArgParserDirty;

// "l,p#,d*" == "-l -p 65536 -d letsplay"

public class Application {
	public static void main(String[] args)
	{
		Args arg = new Args("l,p#,d*", args);
	}
}
