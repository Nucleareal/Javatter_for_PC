package javatter.plugin.nuclear;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class LayoutUtil
{

	public static JPanel newBoxLayout()
	{
		JPanel pn = new JPanel();
		pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
		return pn;
	}

}
