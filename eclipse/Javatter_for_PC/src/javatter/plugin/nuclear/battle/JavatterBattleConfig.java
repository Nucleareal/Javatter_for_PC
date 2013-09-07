package javatter.plugin.nuclear.battle;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

import javatter.plugin.nuclear.Builder_CheckBox;
import javatter.plugin.nuclear.PluginConfigAdapter;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.orekyuu.javatter.util.SaveData;

public class JavatterBattleConfig extends PluginConfigAdapter
{
	public JavatterBattleConfig(SaveData data)
	{
		super(data);
	}

	@Override
	public Component getComponent()
	{
		JPanel root = new JPanel(new BorderLayout());

		JCheckBox box = new Builder_CheckBox(_data).create("応戦する", "isAvailable", new ActionListener[]{});
		root.add(box, BorderLayout.NORTH);

		return root;
	}
}
