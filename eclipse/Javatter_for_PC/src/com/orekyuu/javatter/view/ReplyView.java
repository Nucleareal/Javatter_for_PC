package com.orekyuu.javatter.view;

import java.awt.Component;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import twitter4j.Status;

import com.orekyuu.javatter.logic.UserStreamLogic;
import com.orekyuu.javatter.model.ReplyModel;
import com.orekyuu.javatter.plugin.TweetObjectBuilder;
import com.orekyuu.javatter.util.BackGroundColor;
import com.orekyuu.javatter.util.TweetObjectFactory;
import com.orekyuu.javatter.viewobserver.UserEventViewObserver;
import com.orekyuu.javatter.viewobserver.UserStreamViewObserver;

public class ReplyView
implements UserStreamViewObserver, IJavatterTab, AdjustmentListener
{
	private Component component;
	private JPanel panel;
	private UserEventViewObserver observer;
	private JScrollPane tp;
	private List<TweetObjectBuilder> builders;

	private Queue<Status> queue=new LinkedList<Status>();

	public ReplyView(UserEventViewObserver observer,List<TweetObjectBuilder> builders)
	{
		this.panel = new JPanel();
		this.panel.setBackground(BackGroundColor.color);
		this.panel.setLayout(new BoxLayout(this.panel, 3));
		this.tp = new JScrollPane(22, 31);
		this.tp.setViewportView(this.panel);
		this.tp.getVerticalScrollBar().setUnitIncrement(20);
		this.tp.getVerticalScrollBar().addAdjustmentListener(this);
		this.component = this.tp;
		this.observer = observer;
		this.builders=builders;
	}

	public void update(UserStreamLogic model)
	{
		if (model instanceof ReplyModel) {
			if(tp.getVerticalScrollBar().getValue()==0){
				addObject(model.getStatus());
			}else{
				queue.add(model.getStatus());
				setNumber(queue.size());
			}
		}
	}

	private void setNumber(int num){
		Pattern p=Pattern.compile("^リプライ(\\(\\d+\\))?$");
		JTabbedPane tab=(JTabbedPane) component.getParent();
		for(int i=0;i<tab.getTabCount();i++){
			if(p.matcher(tab.getTitleAt(i)).matches()){
				if(num!=0){
					tab.setTitleAt(i, "リプライ("+num+")");
				}else{
					tab.setTitleAt(i, "リプライ");
				}
			}
		}
	}

	private void addObject(Status status){
		TweetObjectFactory factory = new TweetObjectFactory(status,builders);
		if (this.panel.getComponentCount() == 1000) this.panel.remove(999);
		this.panel.add(factory.createTweetObject(this.observer), 0);
		this.panel.updateUI();
	}

	@Override
	public Component getComponent()
	{
		return this.component;
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		if(arg0.getValue()==0){
			while(!queue.isEmpty()){
				addObject(queue.poll());
			}
			JTabbedPane tab=(JTabbedPane) component.getParent();
			tab.setTitleAt(1, "リプライ");
		}
	}
}