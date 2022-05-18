package ArgParserDirty;

import java.util.*;

class BooleanArgumentMarshaler implements ArgumentMarshaler 
{
	private boolean booleanValue = false;
	
	public void set(Iterator<String> currentArgument) throws ArgsException 
	{
		booleanValue = true;
	}

	public Object get()
	{
		return booleanValue;
	}
}