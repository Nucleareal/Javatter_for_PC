package javatter.plugin.nuclear;

import java.awt.event.ActionListener;

import javax.swing.JComponent;

public class NullBuilder implements IViewCreator
{
	@Override
	public JComponent create(String msg, String name, ActionListener[] listeners)
	{
		return null;
	}

	private NullBuilder()
	{
	}
	private static final NullBuilder _instance = new NullBuilder();
	public static NullBuilder getInstance()
	{
		return _instance;
	}
}
