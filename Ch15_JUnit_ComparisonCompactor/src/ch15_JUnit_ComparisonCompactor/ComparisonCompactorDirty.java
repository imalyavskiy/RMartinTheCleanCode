package ch15_JUnit_ComparisonCompactor;

import junit.framework.Assert;

// This class is a copy of ComparisonCompactorOriginal that after a series of changes will turn to ComparisonCompactorClean  

public class ComparisonCompactorDirty 
{
	private static final String ELLIPSIS = "...";
	private static final String DELTA_END = "]";
	private static final String DELTA_START = "[";
	private int contextLength;
	private String expected;
	private String actual;
	private int prefixLength;
	private int suffixLength;
	private String compactExpected;
	private String compactActual;


	public ComparisonCompactorDirty(int contextLength, String expected, String actual) 
	{
		this.contextLength = contextLength;
		this.expected = expected;
		this.actual = actual;
	}

	public String formatCompactedComparison(String message) 
	{
		if (shouldBeCompacted())
		{
			compactExpectedAndActual();
			return Assert.format(message, compactExpected, compactActual);
		}
		else
		{
			return Assert.format(message, expected, actual);
		}
	}
	
	private void compactExpectedAndActual() 
	{
		findCommonPrefixAndSuffix();
		compactExpected = compactString(expected);
		compactActual = compactString(actual);
	}
	
	private void findCommonPrefixAndSuffix() 
	{
		findCommonPrefix();
		suffixLength = 0;
		for (; !suffixOverlapsPrefix(suffixLength); suffixLength++) 
		{
			if (charFromEnd(expected, suffixLength) != charFromEnd(actual, suffixLength))
			{
				break;
			}
		}
	}

	private char charFromEnd(String s, int i) 
	{
		return s.charAt(s.length() - i - 1);
	}

	private boolean suffixOverlapsPrefix(int suffixLength) 
	{
		return actual.length() - suffixLength <= prefixLength || expected.length() - suffixLength <= prefixLength;
	}
	
	private boolean shouldBeCompacted() 
	{
		return expected != null && actual != null && !areStringsEqual();
	}

	private String compactString(String source) 
	{
		String result = DELTA_START + source.substring(prefixLength, source.length() - suffixLength) + DELTA_END;
		
		if (prefixLength > 0)
		{
			result = computeCommonPrefix() + result;
		}
		
		if (suffixLength > 0)
		{
			result = result + computeCommonSuffix();
		}
		
		return result;
	}

	private void findCommonPrefix() 
	{
		prefixLength = 0;
		int end = Math.min(expected.length(), actual.length());
		for (; prefixLength < end; prefixLength++) 
		{
			if (expected.charAt(prefixLength) != actual.charAt(prefixLength))
			{				
				break;
			}
		}
	}

	private int findCommonSuffix(int prefixIndex) 
	{
		int expectedSuffix = expected.length() - 1;
		int actualSuffix = actual.length() - 1;
		for (; actualSuffix >= prefixIndex && expectedSuffix >= prefixIndex; actualSuffix--, expectedSuffix--) 
		{
			if (expected.charAt(expectedSuffix) != actual.charAt(actualSuffix))
			{
				break;
			}
		}
		return expected.length() - expectedSuffix;
	}

	private String computeCommonPrefix() 
	{
		return (prefixLength > contextLength ? ELLIPSIS : "") + expected.substring(Math.max(0, prefixLength - contextLength), prefixLength);
	}

	private String computeCommonSuffix() 
	{
		int end = Math.min(expected.length() - suffixLength + contextLength, expected.length());
		return expected.substring(expected.length() - suffixLength, end) + (expected.length() - suffixLength < expected.length() - contextLength ? ELLIPSIS : "");
	}

	private boolean areStringsEqual() 
	{
		return expected.equals(actual);
	}

}