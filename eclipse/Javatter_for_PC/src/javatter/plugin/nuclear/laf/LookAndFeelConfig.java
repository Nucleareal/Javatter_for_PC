package javatter.plugin.nuclear.laf;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javatter.plugin.nuclear.Builder_Button;
import javatter.plugin.nuclear.PluginConfigAdapter;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.orekyuu.javatter.util.SaveData;

public class LookAndFeelConfig extends PluginConfigAdapter
{
	public LookAndFeelConfig(SaveData data)
	{
		super(data);
	}

	@Override
	public Component getComponent()
	{
		JButton[] jb = new JButton[4];
		for (int i = 0; i < jb.length; i++)
		{
			jb[i] = new Builder_Button(PluginMain._data).create(lafname[i][1], "", new ActionListener[] { new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							String lafClassName = e.getActionCommand();
							try
							{
								UIManager.setLookAndFeel(lafClassName);
								SwingUtilities.updateComponentTreeUI(PluginMain._MainView);
							}
							catch (Exception ex)
							{
								System.out.println("Error L&F Setting");
							}
						}
					} });
			jb[i].setActionCommand(lafname[i][0]);
		}
		return queuing(jb);
	}

	private static String[][] lafname = new String[][] {
			{ "javax.swing.plaf.metal.MetalLookAndFeel", "Metal", },
			{ "com.sun.java.swing.plaf.motif.MotifLookAndFeel", "Motif", },
			{ "com.sun.java.swing.plaf.windows.WindowsLookAndFeel", "Windows", },
			{ "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel", "Classic", },
			};
}
