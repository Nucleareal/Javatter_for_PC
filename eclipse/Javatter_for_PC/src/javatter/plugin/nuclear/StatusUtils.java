package javatter.plugin.nuclear;

import twitter4j.Status;
import twitter4j.User;
import twitter4j.UserMentionEntity;

import com.orekyuu.javatter.account.TwitterManager;

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

	public static String removeMentions(Status status)
	{
		String s = status.getText();
		UserMentionEntity[] ue = status.getUserMentionEntities();
		for(UserMentionEntity u : ue)
		{
			s = s.replace(u.getScreenName(), "");
		}
		s = s.replace("@", "");
		s = s.replace(" ", "");
		return s;
	}

	public static boolean isReplyToMe(Status status)
	{
		if(status.getInReplyToStatusId() > 0)
		{
			try
			{
				if(status.getInReplyToUserId() == TwitterManager.getInstance().getTwitter().getId())
				{
					return true;
				}
			}
			catch(Exception e)
			{
			}
		}
		return false;
	}
}
