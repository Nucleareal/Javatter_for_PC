package javatter.plugin.nuclear;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

public interface IViewCreator
{
	public JComponent create(String msg, final String name, ActionListener[] listeners);

	public Component create(String msg, final String name);
}
