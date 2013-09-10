package javatter.plugin.nuclear.btob;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.orekyuu.javatter.util.SaveData;
import com.orekyuu.javatter.view.IJavatterTab;

public class BeamConfigView implements IJavatterTab, ActionListener
{
	private JCheckBox[] checkBoxs = new JCheckBox[2];
	private JButton[] buttons = new JButton[2];
	private SaveData data;

	public BeamConfigView(SaveData data)
	{
		this.data = data;
	}

	@Override
	public Component getComponent()
	{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 384, 364);
		panel.setBackground(Color.WHITE);

		this.checkBoxs[0] = new JCheckBox(this.setHtml("BeamToBeamを有効にする"));
		this.checkBoxs[0].setBackground(Color.WHITE);
		this.checkBoxs[0].setSelected(data.getBoolean("active"));
		panel.add(checkBoxs[0]).setBounds(5, 5, 500, 20);

		this.checkBoxs[1] = new JCheckBox(this.setHtml("自分に迎撃する"));
		this.checkBoxs[1].setBackground(Color.WHITE);
		this.checkBoxs[1].setSelected(data.getBoolean("myself"));
		panel.add(checkBoxs[1]).setBounds(5, 35, 500, 20);

		buttons[0] = new JButton(this.setHtml("保存"));
		buttons[0].addActionListener(this);
		panel.add(buttons[0]).setBounds(0, 315, 384, 24);

		buttons[1] = new JButton(this.setHtml("キャンセル"));
		buttons[1].addActionListener(this);
		panel.add(buttons[1]).setBounds(0, 340, 384, 24);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource().equals(this.buttons[0]))
		{
			this.data.setBoolean("active", this.checkBoxs[0].isSelected());
			this.data.setBoolean("myself", this.checkBoxs[1].isSelected());

			JDialog dialog = (JDialog)SwingUtilities.getWindowAncestor(this.checkBoxs[0]);
			dialog.setVisible(false);
		}
		else if(ev.getSource().equals(this.buttons[1]))
		{
			JDialog dialog = (JDialog)SwingUtilities.getWindowAncestor(this.checkBoxs[0]);
			dialog.setVisible(false);
		}
	}

	private String setHtml(String c)
	{
		return "<html><font face=\"Meiryo\">" + c.replace("<", "&lt;").replace(">", "&gt;") + "</font></html>";
	}
}
