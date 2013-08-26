package javatter.plugin.nuclear.multiaccount;

import java.util.Random;

public class CryptCoder
{
	public static String encode(Random random, String str)
	{
		String result = "";
		for(int i = 0; i < str.length(); i++)
		{
			result += (char)( (int)(str.charAt(i) + ( random.nextInt() % 256 ) ) );
		}
		return result;
	}

	public static String decode(Random random, String str)
	{
		String result = "";
		for(int i = 0; i < str.length(); i++)
		{
			result += (char)( (int)(str.charAt(i) - ( random.nextInt() % 256 ) ) );
		}
		return result;
	}
}
