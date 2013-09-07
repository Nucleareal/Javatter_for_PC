package javatter.plugin.nuclear.brainfuck;

import javatter.plugin.nuclear.PluginAdapter;

import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public class PluginMain extends PluginAdapter
{
	protected static SaveData _data;

	@Override
	public void init()
	{
		super.init(BrainfuckModel.class, BrainfuckConfig.class);
		_data = getSaveData();
	}

	@Override
	public String getPluginName()
	{
		return "Brainfuck Plugin";
	}

	@Override
	public String getVersion()
	{
		return "1 for β";
	}

	@Override
	protected IJavatterTab getPluginConfigViewObserver()
	{
		return new BrainfuckConfig(getSaveData());
	}
}
