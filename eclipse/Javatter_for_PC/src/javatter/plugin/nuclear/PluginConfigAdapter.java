package javatter.plugin.nuclear;

import java.awt.Component;

import javax.swing.JComponent;
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
		return LayoutUtil.newBoxLayout();
	}

	protected JPanel queuing(JComponent ... arr)
	{
		JPanel root = LayoutUtil.newBoxLayout();
		for(JComponent p : arr)
		{
			root.add(p);
		}
		return root;
	}
}
