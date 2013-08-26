package javatter.plugin.nuclear;

import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public abstract class PluginConfigAdapter implements IJavatterTab
{
	protected SaveData _data;

	public PluginConfigAdapter(SaveData data)
	{
		_data = data;
	}
}
