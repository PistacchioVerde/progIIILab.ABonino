package parte4;

import java.rmi.*;
import java.util.*;
import parte1.*;
import parte3.*;

public class ProvaRemoteMessageBoxDue {

    public static void main(String[] args) {
        ADSL adsl;
        MessageBox msgBox1;
        MessageBox msgBox2;
        AgentID myID = new PersonalAgentID("Mario", "Rossi");
        AgentID agent = new PersonalAgentID("Pippo", "Baudo");
        Message msg = new Message(agent, myID, Performative.AGREE, "MESS 1", "extra");
        try {
            Message mesg = new Message(agent, myID, Performative.AGREE, "MESS 1", "extra");
            adsl = (ADSL) Naming.lookup("rmi://192.168.1.71:2000/ADSL");
            System.out.println("Cerco il box di " + myID);
            List<RemoteMessageBox> remoteMessageBox = adsl.getRemoteMessageBox(myID);
            for (RemoteMessageBox rmb : remoteMessageBox) {
                rmb.write(msg);
                System.out.println(rmb.readMessage());
            }
            System.out.println("Rimuovo il box di " + agent);
            adsl.removeRemoteMessageBox(agent);
            System.out.println("Cerco il box di " + agent);
            adsl.getRemoteMessageBox(agent);
        } catch (Exception e) {
            System.out.println("Failed_rmi" + e);
        }
    }
}