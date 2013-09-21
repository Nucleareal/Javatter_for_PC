package javatter.plugin.nuclear;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCheckListener implements ActionListener
{
	private CheckData _cd;

	public UpdateCheckListener(String name, String version)
	{
		_cd = new CheckData();
		_cd.name = name;
		_cd.version = version;
	}

	public UpdateCheckListener(CheckData cd)
	{
		_cd = cd;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		UpdateChecker.checkNeedToUpdate(_cd);
	}
}
