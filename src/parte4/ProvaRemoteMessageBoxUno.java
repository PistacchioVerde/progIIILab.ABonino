package parte4;

import java.rmi.*;

import parte1.*;
import parte3.*;

public class ProvaRemoteMessageBoxUno {

	public static void main(String[] args) {
		ADSL adsl;
        MessageBox msgBox2;
        MessageBox msgBox1;
        AgentID myID = new PersonalAgentID("Mario", "Rossi");
        AgentID agent = new PersonalAgentID("Pippo", "Baudo");
        try {
            adsl = (ADSL) Naming.lookup("rmi://127.0.0.1:2000/ADSL");
            msgBox1 = new MessageBox((PersonalAgentID) myID);
            msgBox2 = new MessageBox((PersonalAgentID) agent);
            System.out.println("Inserisco il box di " + msgBox1.getOwner());
            adsl.insertRemoteMessageBox(msgBox1);
            System.out.println("Inserisco il box di " + msgBox2.getOwner());
            adsl.insertRemoteMessageBox(msgBox2);
        } catch (Exception e) {
            System.out.println("Failed_rmi" + e);
        }
	}
}