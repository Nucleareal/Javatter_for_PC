package javatter.plugin.nuclear.battle;

import java.awt.Component;

import javatter.plugin.nuclear.Builder_CheckBox;
import javatter.plugin.nuclear.PluginConfigAdapter;

import javax.swing.JCheckBox;

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
		JCheckBox box = new Builder_CheckBox(_data).create("応戦する", "isAvailable");

		JCheckBox rep = new Builder_CheckBox(_data).create("リプライのビームにも応戦する", "isReplyBeam");

		return queuing(box, rep);
	}
}
