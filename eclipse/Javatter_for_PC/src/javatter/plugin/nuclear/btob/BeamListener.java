package javatter.plugin.nuclear.btob;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.orekyuu.javatter.account.TwitterManager;
import com.orekyuu.javatter.logic.UserStreamLogic;
import com.orekyuu.javatter.util.TwitterUtil;
import com.orekyuu.javatter.viewobserver.UserStreamViewObserver;
public class BeamListener implements UserStreamLogic
{
	Twitter twttr = TwitterManager.getInstance().getTwitter();
	TwitterUtil twttrutl = new TwitterUtil();

	private boolean isActive = BeamToBeam.getPlugin().getPluginSaveData().getBoolean("active");
	private boolean isMyself = BeamToBeam.getPlugin().getPluginSaveData().getBoolean("myself");

	@Override
	public Status getStatus()
	{
		return null;
	}

	@Override
	public void onStatus(Status status)
	{
		if(isActive)
		{
			try
			{
				if(BeamUtil.isnRetweet(status) && BeamUtil.isJavaBeam(status))
				{
					String Receiver = "@" + status.getUser().getScreenName() + " ";
					String Tweet = Receiver + status.getText();

					if(BeamUtil.checkSockPuppet(status))
					{
						if(isMyself)
						{
							twttrutl.setReplyID(status);
							twttrutl.tweet(twttr, Tweet);
						}
					}
					else
					{
						twttrutl.setReplyID(status);
						twttrutl.tweet(twttr, Tweet);
					}
				}
			}
			catch (TwitterException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onReplyTweet(Status status)
	{
	}

	@Override
	public void onRetweetTweet(Status status)
	{
	}

	@Override
	public void setView(UserStreamViewObserver view)
	{
	}
}