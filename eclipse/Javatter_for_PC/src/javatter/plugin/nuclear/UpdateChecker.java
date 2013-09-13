package javatter.plugin.nuclear;

import java.awt.Desktop;
import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class UpdateChecker
{
	public static void checkNeedToUpdate(String version, String filename)
	{
		boolean result = false;
		String msg = "";
		String ver = version;
		try
		{
			URL url = new URL("https://dl.dropboxusercontent.com/u/39953759/"+filename+".version");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.connect();

			int formDataLength = con.getContentLength();
			byte dataBytes[] = new byte[formDataLength];
			int byteRead = 0;
			int totalBytesRead = 0;
			DataInputStream in = new DataInputStream(con.getInputStream());
			while (totalBytesRead < formDataLength)
			{
				byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
				totalBytesRead += byteRead;
			}
			String notes = new String(dataBytes, "UTF-8");
			Scanner sc = new Scanner(notes);

			ver = sc.nextLine();
			ver = ver.replaceAll("[^0-9.]", "");

			result = !ver.equals(version);
			String sz;
			while(sc.hasNextLine())
			{
				sz = sc.nextLine();
				msg += sz;
			}
			in.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(result)
		{
			openNeedToUpdate(ver, msg);
		}
	}

	private static void openNeedToUpdate(String ver, String msg)
	{
		if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "JavatterBattle v"+ver+"\n"+msg, "New Version", JOptionPane.YES_NO_OPTION))
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
