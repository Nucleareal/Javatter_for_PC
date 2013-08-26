package javatter.plugin.nuclear.multiaccount;

import javatter.plugin.nuclear.PluginAdapter;

import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public class PluginMain extends PluginAdapter
{
	static SaveData _data;

	@Override
	public void init()
	{
		super.init(MultiAccountPluginModel.class, MultiAccountPluginConfig.class);
		setDefault("AuthorizedAccounts", 0);
		AccountController.getInstance().load(getSaveData());
		_data = getSaveData();
	}

	@Override
	public String getPluginName()
	{
		return "OwnerEcho";
	}

	@Override
	public String getVersion()
	{
		return "v1.0.0 for α";
	}

	@Override
	protected IJavatterTab getPluginConfigViewObserver()
	{
		return new MultiAccountPluginConfig(getSaveData());
	}
}
