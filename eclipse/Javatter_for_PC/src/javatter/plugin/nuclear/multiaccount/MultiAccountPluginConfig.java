package javatter.plugin.nuclear.multiaccount;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javatter.plugin.nuclear.Builder_Button;
import javatter.plugin.nuclear.Builder_CheckBox;
import javatter.plugin.nuclear.PluginConfigAdapter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.orekyuu.javatter.util.SaveData;

public class MultiAccountPluginConfig extends PluginConfigAdapter
{
	public MultiAccountPluginConfig(SaveData data)
	{
		super(data);
	}

	@Override
	public Component getComponent()
	{
		JPanel root = new JPanel(new BorderLayout());
		JPanel flow = new JPanel(); flow.setLayout(new BoxLayout(flow, BoxLayout.Y_AXIS));

		JButton auth = new Builder_Button(_data).create("アカウントを追加", "", new ActionListener[]{ new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					AuthorizedData auth = TwitterAuthorizer.auth();
					AccountController.getInstance().add(_data, auth);
				}
			}
		});
		JCheckBox withFavorite = new Builder_CheckBox(_data).create("一緒にツイートする", "with_tweet", new ActionListener[]{});

		flow.add(auth);
		flow.add(withFavorite);

		root.add(flow, BorderLayout.CENTER);

		return root;
	}
}
