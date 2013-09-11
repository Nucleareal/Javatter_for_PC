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
		String text = StatusUtils.getText(status);
		String st = "雄大なるJavaの歴史よ、太古よりのJava神よ、我にJavaの力を与え給え。今、Javaの力を解き放つ！Java波動七式";
		String regex = "(?=^"+st+".* )\\d*";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);

		if(!m.find()) return false;
		String tex = m.group();
		try
		{
			int i = Integer.valueOf(tex);
			return i % 13 == 0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
