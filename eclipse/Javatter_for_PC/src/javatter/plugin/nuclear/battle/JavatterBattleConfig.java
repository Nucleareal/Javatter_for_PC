package javatter.plugin.nuclear.battle;

import java.awt.Component;

import javatter.plugin.nuclear.Builder_CheckBox;
import javatter.plugin.nuclear.Builder_Label;
import javatter.plugin.nuclear.PluginConfigAdapter;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

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

		JCheckBox auto = new Builder_CheckBox(_data).create("自動ガード", "isAutoGuard");

		JCheckBox hand = new Builder_CheckBox(_data).create("手が滑らない(警告を出す)", "isAlertOnBeam");

		JLabel lb = new Builder_Label(_data).create(BeamStatus.get().getHP(), "");

		return queuing(box, rep, auto, hand, lb);
	}
}
