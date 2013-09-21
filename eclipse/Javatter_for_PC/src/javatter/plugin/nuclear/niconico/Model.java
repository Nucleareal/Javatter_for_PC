package javatter.plugin.nuclear.niconico;

import javatter.plugin.nuclear.ModelAdapter;
import twitter4j.Status;

public class Model extends ModelAdapter
{
	@Override
	public void onStatus(Status status)
	{
		PluginMain.onStatus(status);
	}
}
