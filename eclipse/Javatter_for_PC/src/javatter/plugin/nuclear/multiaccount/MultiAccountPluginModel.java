package javatter.plugin.nuclear.multiaccount;

import javatter.plugin.nuclear.PluginModelAdapter;
import twitter4j.Status;
import twitter4j.User;

public class MultiAccountPluginModel extends PluginModelAdapter
{
	@Override
	public void onStatus(Status status)
	{
		if(!PluginMain._data.getBoolean("with_tweet")) return;
		try
		{
			User user = AccountController.getInstance().getOwnerUser();
			final String text = StatusUtils.getText(status);

			if(AccountWrapper.isSameUser(StatusUtils.getOwner(status), user))
			{
				AccountController.getInstance().actionForAll(new IMultiAccountAction()
				{
					@Override
					public void action(AccountWrapper wrapper)
					{
						try
						{
							wrapper._twitter.updateStatus(text);
						}
						catch(Exception e)
						{
						}
					}
				});
			}
		}
		catch(Exception e)
		{
		}
	}
}
