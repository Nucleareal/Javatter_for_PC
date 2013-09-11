package javatter.plugin.nuclear.laf;

import java.awt.Component;

import javatter.plugin.nuclear.PluginAdapter;

import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public class PluginMain extends PluginAdapter
{
	public static SaveData _data;
	public static Component _MainView;

	@Override
	public void init()
	{
		_data = getSaveData();
		_MainView = getMainView().getMainFrame();
	}

	@Override
	public String getPluginName()
	{
		return "ChangeLookAndFeel";
	}

	@Override
	public String getVersion()
	{
		return "1 for α";
	}

	@Override
	protected IJavatterTab getPluginConfigViewObserver()
	{
		return new LookAndFeelConfig(_data);
	}
}
