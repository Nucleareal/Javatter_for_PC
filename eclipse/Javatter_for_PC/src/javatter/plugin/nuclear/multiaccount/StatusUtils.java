package javatter.plugin.nuclear.multiaccount;

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
}
