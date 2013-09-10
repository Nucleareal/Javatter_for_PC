package javatter.plugin.nuclear.btob;

import twitter4j.Status;
import twitter4j.TwitterException;

import com.orekyuu.javatter.account.TwitterManager;
import com.orekyuu.javatter.util.JavaBeamUtil;

public class BeamUtil
{
	public static boolean isReply(Status status) throws IllegalStateException, TwitterException
	{
		String user = TwitterManager.getInstance().getTwitter().getScreenName();
		return user.equals(status.getInReplyToScreenName());
	}

	public static boolean checkSockPuppet(Status status) throws IllegalStateException, TwitterException
	{
		String user = TwitterManager.getInstance().getTwitter().getScreenName();
		return user.equals(status.getUser().getScreenName());
	}

	public static boolean isnRetweet(Status status)
	{
		return !status.isRetweet();
	}

	public static String getText(Status status)
	{
		if(status.isRetweet())
		{
			status = status.getRetweetedStatus();
		}
		return status.getText();
	}

	public static boolean isJavaBeam(Status status)
	{
		return JavaBeamUtil.isJavaBeam(getText(status));
	}
}