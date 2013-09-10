package javatter.plugin.nuclear;

import java.awt.Component;
import java.awt.event.ActionListener;

public abstract class Builder_Base implements IViewCreator
{
	public Component create(String msg, final String name)
	{
		return create(msg, name, new ActionListener[]{});
	}
}
