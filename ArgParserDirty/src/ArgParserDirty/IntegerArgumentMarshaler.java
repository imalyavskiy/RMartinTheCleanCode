package ArgParserDirty;

import java.util.*;

class IntegerArgumentMarshaler implements ArgumentMarshaler 
{
	private int integerValue;

	public void set(Iterator<String> currentArgument) throws ArgsException 
	{
		String parameter = null;
		try 
		{
			parameter = currentArgument.next();
			integerValue = Integer.parseInt(parameter);
		}
		catch (NoSuchElementException e) 
		{
			throw new ArgsException(ArgsException.ErrorCode.MISSING_INTEGER);
		} 
		catch (NumberFormatException e) 
		{
			throw new ArgsException(ArgsException.ErrorCode.INVALID_INTEGER, parameter);
		}			
	}		
	
	public Object get() 
	{
		return integerValue;
	}
}