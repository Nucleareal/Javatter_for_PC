package javatter.plugin.nuclear;

import twitter4j.Status;

import com.orekyuu.javatter.logic.UserStreamLogic;
import com.orekyuu.javatter.viewobserver.UserStreamViewObserver;

public abstract class PluginModelAdapter implements UserStreamLogic
{
	@Override
	public Status getStatus()
	{
		return null;
	}

	@Override
	public void onStatus(Status status)
	{
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
