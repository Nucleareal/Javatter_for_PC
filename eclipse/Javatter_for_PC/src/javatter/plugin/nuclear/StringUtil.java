package javatter.plugin.nuclear;


public class StringUtil
{
	public static String repeat(String p, int n)
	{
		String s = "";
		for(int i = 0; i < n; i++)
		{
			s += p;
		}
		return s;
	}
}
