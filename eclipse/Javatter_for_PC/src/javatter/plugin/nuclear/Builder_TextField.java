package javatter.plugin.nuclear;

import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.orekyuu.javatter.util.SaveData;

public class Builder_TextField extends JTextField
{
	private JTextField _field;
	private SaveData _data;

	public Builder_TextField(SaveData data)
	{
		_data = data;
	}

	public JTextField create(String msg, final String name, ActionListener[] listeners)
	{
		_field = new JTextField();
		_field.setText(msg);
		for(ActionListener e : listeners)
		{
			_field.addActionListener(e);
		}
		_field.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void removeUpdate(DocumentEvent e)
			{
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				_data.setString(name, _field.getText());
			}
		});
		return _field;
	}

	public JTextField create(String msg, final String name)
	{
		return create(msg, name, new ActionListener[]{});
	}
}
