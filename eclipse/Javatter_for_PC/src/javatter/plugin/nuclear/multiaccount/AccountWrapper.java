package javatter.plugin.nuclear.multiaccount;

import twitter4j.Twitter;
import twitter4j.User;

public class AccountWrapper
{
	private User _user;
	public Twitter _twitter;

	public AccountWrapper(User user, Twitter twitter)
	{
		_user = user;
		_twitter = twitter;
	}

	public boolean isSameUser(User user)
	{
		return isSameUser(user.getId());
	}

	public boolean isSameUser(long id)
	{
		return id == _user.getId();
	}

	public static boolean isSameUser(User u1, User u2)
	{
		return u1.getId() == u2.getId();
	}
}
