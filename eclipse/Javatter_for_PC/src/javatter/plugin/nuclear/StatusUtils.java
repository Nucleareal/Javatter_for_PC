package javatter.plugin.nuclear;

import twitter4j.Status;
import twitter4j.User;

public class StatusUtils
{
	public static User getOwner(Status status)
	{
		if(status.isRetweet())
		{
			status = status.getRetweetedStatus();
		}
		return status.getUser();
	}

	public static String getText(Status status)
	{
		if(status.isRetweet())
		{
			status = status.getRetweetedStatus();
		}
		return status.getText();
	}

	public static boolean isnRetweet(Status status)
	{
		return !status.isRetweet();
	}

	public static String getInReplyToHeader(Status status)
	{
		return "@"+status.getUser().getScreenName()+" ";
	}
}
