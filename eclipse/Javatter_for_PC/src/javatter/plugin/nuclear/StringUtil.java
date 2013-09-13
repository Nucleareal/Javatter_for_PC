package javatter.plugin.nuclear;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.Status;

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

	public static boolean isJavaLaser(Status status)
	{
		if(!StatusUtils.isFromJavatter(status)) return false;

		String text = StatusUtils.removeMentions(status);
		if(!text.contains("Java")) return false;
		String regex = "\\d+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);

		if(!m.find()) return false;
		String tex = m.group();
		try
		{
			int i = Integer.valueOf(tex);
			return i % 17 == 0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
