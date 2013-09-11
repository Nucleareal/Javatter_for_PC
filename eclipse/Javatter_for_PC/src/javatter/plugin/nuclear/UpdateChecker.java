package javatter.plugin.nuclear;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;

import javax.swing.JOptionPane;

public class UpdateChecker
{
	public static void checkNeedToUpdate(String version, String filename)
	{
		boolean result = false;
		try
		{
			File file = new File(new URI("https://dl.dropboxusercontent.com/u/39953759/"+filename+".version"));
			BufferedReader in = new BufferedReader(new FileReader(file));
			String v = in.readLine();
			result = !v.equals(version);
			in.close();
		}
		catch(Exception e)
		{
		}
		if(result)
		{
			openNeedToUpdate();
		}
	}

	private static void openNeedToUpdate()
	{
		if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "新しいバージョンがあります。\nブラウザで開きますか？", "情報", JOptionPane.YES_NO_OPTION))
		{
			try
			{
				Desktop.getDesktop().browse(new URI("http://www1221uj.sakura.ne.jp//bbs/viewtopic.php?f=4&t=9"));
			}
			catch(Exception e)
			{
			}
		}
	}
}
