package javatter.plugin.nuclear;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.orekyuu.javatter.util.SaveData;

public class Builder_CheckBox implements IViewCreator
{
	private JPanel _root;
	private JCheckBox _box;
	private SaveData _data;

	public Builder_CheckBox(SaveData data)
	{
		_data = data;
		_root = new JPanel();
		_root.setLayout(new BoxLayout(_root, BoxLayout.LINE_AXIS));
	}

	public JCheckBox create(String msg, final String name, ActionListener[] listeners)
	{
		_box = new JCheckBox();
		_root.add(_box);
		_box.setText(msg); _data.setDefaultValue(name, false);
		_box.setSelected(_data.getBoolean(name));
		_box.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				_data.setBoolean(name, _box.isSelected());
			}
		});
		for(ActionListener e : listeners)
		{
			_box.addActionListener(e);
		}
		return _box;
	}

	public JCheckBox create(String msg, final String name)
	{
		return create(msg, name, new ActionListener[]{});
	}

	public JPanel getRootPane()
	{
		return _root;
	}
}
