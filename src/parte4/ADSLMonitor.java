package parte4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.*;

public class ADSLMonitor extends JFrame{
	
	private ADSLImpl adsl;
	
	//private Observer obs;
	
	private JLabel port;
	private JTextField porttxt;
	private JTextArea log;
	private JButton startReg;
	private JButton startADSL;
	private JButton stopADSL;
	private JPanel panelTop;
	private JPanel panelBot;
	
	public ADSLMonitor(){
		port = new JLabel("PORT: ");
		porttxt = new JTextField(20);
		panelTop = new JPanel();
		panelBot = new JPanel();
		log = new JTextArea(8,25);
		
		startReg = new JButton("Start reg");
		startReg.addActionListener(new ButtonRegListener());

		startADSL = new JButton("Start ADSL");
		startADSL.addActionListener(new ButtonAdslListener()); 
		startADSL.setEnabled(false);
		
		stopADSL = new JButton("Stop ADSL");
		stopADSL.addActionListener(new ButtonStopListener());
		stopADSL.setEnabled(false);
		
		panelTop.add(port);
		panelTop.add(porttxt);
		panelTop.add(startReg);
		panelTop.add(startADSL);
		panelTop.add(stopADSL);
		
		panelTop.add(log);
		
		this.getContentPane().add(panelTop);
		//this.getContentPane().add(panelBot);
		this.setTitle("ADSL Monitor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(350,250);
		try {
			this.adsl = new ADSLImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		/*this.obs = new Observer(monitor);
		Thread t1 = new Thread(obs);*/
	}
	
	public static void main(String[] args) {
		ADSLMonitor monitor = new ADSLMonitor();
		monitor.setVisible(true);
	}

	private class ButtonRegListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if("".equals(porttxt.getText()) || !porttxt.getText().matches("-?\\d+")){
				JOptionPane.showMessageDialog(panelTop, "Inserire un numero di porta valido", "ERROR", JOptionPane.ERROR_MESSAGE);
			}else{
				adsl.setPort(Integer.valueOf(porttxt.getText()));
				adsl.startRMIRegistry();
				startReg.setEnabled(false);
				startADSL.setEnabled(true);
				log.append("Registry started on port "+porttxt.getText()+"\n");
			}
		}
	}
	 
	private class ButtonAdslListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			adsl.startADSL();
			startADSL.setEnabled(false);
			stopADSL.setEnabled(true);
			log.append("ADSL platform started\n");
		}
	}
	
	private class ButtonStopListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			adsl.stopADSL();
			stopADSL.setEnabled(false);
			startADSL.setEnabled(true);
			log.append("ADSL platform stopped\n");
		}
	}
	
}
