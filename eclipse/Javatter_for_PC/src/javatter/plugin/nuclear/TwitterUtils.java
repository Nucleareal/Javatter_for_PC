package javatter.plugin.nuclear;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;

import com.orekyuu.javatter.account.TwitterManager;


public class TwitterUtils
{
	public static AccessToken getAccessToken()
	{
		AccessToken tk = null;
		try
		{
			tk = TwitterManager.getInstance().getTwitter().getOAuthAccessToken();
		}
		catch(Exception e)
		{
		}
		return tk;
	}

	public static long getUserId()
	{
		long result = -1L;
		Twitter tw = TwitterManager.getInstance().getTwitter();
		try
		{
			result = tw.getId();
		}
		catch(Exception e)
		{
		}
		return result;
	}
}
