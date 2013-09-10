package javatter.plugin.nuclear.multiaccount;

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
}
