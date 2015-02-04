package parte6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.Observable;
import java.util.Observer;

import parte1.*;
import parte4.*;
import parte5.*;

public class JAMAgentMonitor extends JFrame implements Observer{

	private JAMAgent agent;
	
	public JTextArea activity;
	private JButton init;
	private JButton start;
	private JButton destroy;
	private JPanel panel;
	
	public JAMAgentMonitor(JAMAgent jag){
		this.agent = jag;
		
		panel = new JPanel();
		
		JLabel label = new JLabel("Agent activity:");
		activity = new JTextArea(8,16);
		init = new JButton("Init");
		init.addActionListener(new ButtonInitListener()); 

		start = new JButton("Start");
		start.addActionListener(new ButtonStartListener());
		start.setEnabled(false);
		
		destroy = new JButton("Destroy");
		destroy.addActionListener(new ButtonDestroyListener());
		destroy.setEnabled(false);
		
		panel.add(label);
		panel.add(activity);
		panel.add(init);
		panel.add(start);
		panel.add(destroy);
		
		this.getContentPane().add(panel);
		//this.getContentPane().add(panelBot);
		this.setTitle("JAM Monitor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(350,250);
	}
	
	public void initJAMAgent() throws Exception{
		agent.init();
	}

	public void startJAMAgent(){
		agent.start();
	}
	
	public void destroyJAMAgent() throws Exception{
		agent.destroy();
	}
	
	//INIT
	private class ButtonInitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				initJAMAgent();
				init.setEnabled(false);
				start.setEnabled(true);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(panel, "Errore lookup\n"+e1, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//START
	private class ButtonStartListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			startJAMAgent();
			start.setEnabled(false);
		}
	}
	
	//DESTROY
	private class ButtonDestroyListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				destroyJAMAgent();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(panel, "Unable to destroy\n"+e1, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		activity.append((String) arg1);
	}
	
}
