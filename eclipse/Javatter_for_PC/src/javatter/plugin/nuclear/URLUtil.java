package javatter.plugin.nuclear;

import java.net.URL;

public class URLUtil
{
	public static URL create(String str)
	{
		URL result = null;
		try
		{
			result = new URL(str);
		}
		catch(Exception e)
		{
		}
		return result;
	}
}
