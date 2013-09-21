package javatter.plugin.nuclear.niconico;

import javatter.plugin.nuclear.PluginAdapter;
import javatter.plugin.nuclear.UpdateChecker;
import twitter4j.Status;

import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public class PluginMain extends PluginAdapter
{
	private static NicoThread _t;
	private static SaveData _d;

	@Override
	public void init()
	{
		super.init(Model.class, Config.class);

		UpdateChecker.checkNeedToUpdate(version(), "NicoNico");
		_d = getSaveData();

		_t = new NicoThread();
		_t.start();

		setDefault("ScrollSpeed", "4");
	}

	public static void onStatus(Status status)
	{
		_t.onStatus(status);
	}

	@Override
	public String getPluginName()
	{
		return "NiconicoTL Plugin";
	}

	public String version()
	{
		return "1";
	}

	@Override
	public String getVersion()
	{
		return version()+" for β";
	}

	@Override
	protected IJavatterTab getPluginConfigViewObserver()
	{
		return new Config(getSaveData());
	}

	public static void refreshFontSpeed()
	{
		int speed = Comment.DEFAULT_DX;
		try
		{
			speed = -Integer.valueOf(_d.getString("ScrollSpeed")).intValue();
		}
		catch(Exception e)
		{
		}
		Comment.DX = speed;
	}
}
