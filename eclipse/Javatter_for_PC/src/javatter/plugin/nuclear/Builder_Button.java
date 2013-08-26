package javatter.plugin.nuclear;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.orekyuu.javatter.util.SaveData;

public class Builder_Button implements IViewCreator
{
	private JPanel _root;
	private JButton _button;
	private SaveData _data;

	public Builder_Button(SaveData data)
	{
		_data = data;
		_root = new JPanel();
		_root.setLayout(new BoxLayout(_root, BoxLayout.LINE_AXIS));
	}

	public JButton create(String msg, final String name, ActionListener[] listeners)
	{
		_button = new JButton();
		_root.add(_button);
		_button.setText(msg);
		for(ActionListener e : listeners)
		{
			_button.addActionListener(e);
		}
		return _button;
	}

	public JPanel getRootPane()
	{
		return _root;
	}
}
