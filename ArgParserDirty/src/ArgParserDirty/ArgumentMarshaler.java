package ArgParserDirty;

import java.util.*;

interface ArgumentMarshaler 
{
	public abstract void set(Iterator<String> currentArgument) throws ArgsException;
	
	public abstract Object get();
}
