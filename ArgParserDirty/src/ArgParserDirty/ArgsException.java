package ArgParserDirty;

public class ArgsException extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	private char errorArgumentId = '\0';
	private String errorParameter = "TILT";
	private ErrorCode errorCode = ErrorCode.OK;
	
	public ArgsException() 
	{
		//
	}
	
	public ArgsException(String message) 
	{
		super(message);
	}

	enum ErrorCode {OK, MISSING_STRING, MISSING_INTEGER, INVALID_INTEGER, UNEXPECTED_ARGUMENT, MISSING_DOUBLE, INVALID_DOUBLE};
}
