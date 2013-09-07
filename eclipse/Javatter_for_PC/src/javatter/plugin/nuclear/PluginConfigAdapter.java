package javatter.plugin.nuclear;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public abstract class PluginConfigAdapter implements IJavatterTab
{
	protected SaveData _data;

	public PluginConfigAdapter(SaveData data)
	{
		_data = data;
	}

	@Override
	public Component getComponent()
	{
		return new JPanel(new BorderLayout());
	}
}
